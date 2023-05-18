package com.ssafy.xten.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.xten.model.dto.User;
import com.ssafy.xten.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api-user")
@Api(tags = "User 컨트롤러")
public class UserRestController {

	@Autowired
	private UserService userService;

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

	// 회원가입(form data 형식으로 넘어옴)
	@ApiOperation(value = "회원가입", notes = "form data로 전달")
	@PostMapping("/signup")
	public ResponseEntity<Integer> signup(User user) {
		userService.signup(user);
		return new ResponseEntity<Integer>(HttpStatus.CREATED);
	}

	// 로그인
	@ApiOperation(value = "로그인", notes = "로그인 성공하면 닉네임 반환")
	@PostMapping("/login")
	public ResponseEntity<?> login(User user, HttpSession session) {
		User tmp = userService.login(user.getId(), user.getPassword());
		if (tmp == null) {
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
		session.setAttribute("loginUser", tmp);
		return new ResponseEntity<String>(tmp.getNickname(), HttpStatus.OK);
	}

	// 로그아웃
	@ApiOperation(value = "로그아웃", notes = "")
	@GetMapping("/logout")
	public ResponseEntity<Void> logout(HttpSession session) {
//		session.removeAttribute("loginUser");
		session.invalidate();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// 비밀번호 확인
	@ApiOperation(value = "비밀번호 확인", notes = "비밀번호가 틀리면 0, 맞으면 1 반환")
	@GetMapping("/password/{userSeq}/{password}")
	public int verifyPassword(@PathVariable int userSeq, @PathVariable String password) {
		return userService.verifyPassword(userSeq,password);
	}

	// 비밀번호 변경
	@ApiOperation(value = "비밀번호 변경", notes= "유저 일련번호, 새 비밀번호 입력 받음")
	@PutMapping("/password/{userSeq}/{newPassword}")
	public ResponseEntity<Void> changePassword(@PathVariable int userSeq, @PathVariable String newPassword) {
		userService.changePassword(userSeq, newPassword);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
