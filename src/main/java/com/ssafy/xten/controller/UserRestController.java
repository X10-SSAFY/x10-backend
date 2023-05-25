package com.ssafy.xten.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.xten.model.dto.Image;
import com.ssafy.xten.model.dto.User;
import com.ssafy.xten.model.service.LoginService;
import com.ssafy.xten.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api-user")
@Api(tags = "User 컨트롤러")
public class UserRestController {

	@Autowired
	private UserService userService;

	@Autowired
	private LoginService loginService;

	// 회원가입(form data 형식으로 넘어옴)
	@ApiOperation(value = "회원가입", notes = "form data로 전달")
	@PostMapping(value = "/signup", consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> signup(@RequestPart(value = "file", required = false) MultipartFile file, User user)
			throws IOException {
		userService.signup(user);
		if (file != null) {
			System.out.println(user.getId());
			int userSeq = userService.getUser(user.getId()).getUserSeq();
			userService.addProfileImage(userSeq, file);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} else {
			System.out.println("파일 업로드 작동 안하는중");
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}

	// user 객체 찾기
	@ApiOperation(value = "user 객체 찾기", notes = "user 일련번호 입력받아서 user 객체 반환")
	@PostMapping("/{userSeq}")
	public ResponseEntity<?> getUser(@PathVariable int userSeq) {
		User user = userService.getUserBySeq(userSeq);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// 일반 로그인
	@ApiOperation(value = "일반 로그인", notes = "로그인 성공하면 유저 일련번호 반환")
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user, HttpSession session) {
		User tmp = loginService.normalLogin(user.getId(), user.getPassword());
		if (tmp == null) {
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
		session.setAttribute("loginUser", tmp);
		User u = userService.getUser(user.getId());
		return new ResponseEntity<Integer>(u.getUserSeq(), HttpStatus.OK);
	}

	// 소셜 로그인
	@ApiOperation(value = "소셜 로그인", notes = "DB에 없는 email이면 가입시키고 로그인, 있으면 바로 로그인. 로그인 성공하면 유저 일련번호 반환. registrationId = google or kakao or naver")
	@GetMapping("/login/oauth2")
    public int socialLogin(@RequestParam("code") String code, @RequestParam("registrationId") String registrationId,
            HttpSession session) {
		User tmp = loginService.socialLogin(code, registrationId);
		// 소셜로그인 처음하는 회원이면 가입시키고 로그인
        if (emailCheck(tmp.getEmail()) == 0) {
            userService.signup(tmp);
            User user = userService.getUserByEmail(tmp.getEmail());
            session.setAttribute("loginUser", user);
            return user.getUserSeq();
        }
       // 처음 아니면 바로 로그인
        else {
            User user = userService.getUserByEmail(tmp.getEmail());
            session.setAttribute("loginUser", user);
            return user.getUserSeq();
        }
	}

	// 로그아웃
	@ApiOperation(value = "로그아웃", notes = "")
	@GetMapping("/logout")
	public ResponseEntity<Void> logout(HttpSession session) {
		session.invalidate();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// 프로필 이미지 업로드
	@ApiOperation(value = "사용자 프로필 이미지 업로드", notes = "user 일련번호, 이미지 파일 입력받음")
	@PostMapping(value = "/upload/{userSeq}", consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> imageUpload(@RequestPart("file") MultipartFile file, @PathVariable int userSeq)
			throws IOException {
		userService.addProfileImage(userSeq, file);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

	// 프로필 이미지 다운로드
	@ApiOperation(value = "사용자 프로필 이미지 다운로드", notes = "user 일련번호 입력받아서 DB에서 찾음")
	@GetMapping(value = "/download/{userSeq}")
	public ResponseEntity<?> imageDownload(@PathVariable int userSeq) {
		Image image = userService.getProfileImage(userSeq);
		if (image == null)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", image.getImageType());
		headers.add("Content-Length", String.valueOf(image.getImageData().length));
		return new ResponseEntity<byte[]>(image.getImageData(), headers, HttpStatus.OK);
	}

	// 프로필 이미지 변경
	@ApiOperation(value = "사용자 프로필 이미지 변경", notes = "유저 일련번호 받아서 삭제하고 다시 업로드")
	@PostMapping(value = "/change/{userSeq}", consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> imageChange(@RequestPart("file") MultipartFile file, @PathVariable int userSeq)
			throws IOException {
		userService.removeProfileImage(userSeq);
		userService.addProfileImage(userSeq, file);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

	// 회원정보 수정(form data 형식으로 넘어옴)
	@ApiOperation(value = "회원정보 수정", notes = "비밀번호, 이메일, 닉네임을 form data 형태로 전달")
	@PutMapping(value = "/edit/{userSeq}")
	public ResponseEntity<?> edit(@PathVariable int userSeq, User user) {
		userService.editUser(userSeq, user);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// 모든 사용자 조회
	@ApiOperation(value = "가입된 모든 유저 조회", notes = "")
	@GetMapping("/users")
	public List<User> userList() {
		return userService.getUserList();
	}

	// 아이디 중복체크
	@ApiOperation(value = "아이디 중복체크", notes = "중복없으면 0, 중복이면 1 반환")
	@GetMapping("/idcheck/{id}")
	public int idCheck(@PathVariable String id) {
		int result = userService.idCheck(id);
		return result;
	}

	// 이메일 중복체크
	@ApiOperation(value = "이메일 중복체크", notes = "중복없으면 0, 중복이면 1 반환")
	@GetMapping("/emailcheck/{email}")
	public int emailCheck(@PathVariable String email) {
		int result = userService.emailCheck(email);
		return result;
	}

	// 비밀번호 확인
	@ApiOperation(value = "비밀번호 확인", notes = "비밀번호가 틀리면 0, 맞으면 1 반환")
	@GetMapping("/password/{userSeq}/{password}")
	public int verifyPassword(@PathVariable int userSeq, @PathVariable String password) {
		return userService.verifyPassword(userSeq, password);
	}

	// 비밀번호 변경
	@ApiOperation(value = "비밀번호 변경", notes = "유저 일련번호, 새 비밀번호 입력 받음")
	@PutMapping("/password/{userSeq}/{newPassword}")
	public ResponseEntity<Void> changePassword(@PathVariable int userSeq, @PathVariable String newPassword) {
		userService.changePassword(userSeq, newPassword);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
