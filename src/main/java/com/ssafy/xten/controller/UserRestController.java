package com.ssafy.xten.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.xten.model.dto.User;
import com.ssafy.xten.model.service.UserService;

@RestController
@RequestMapping("/api-user")
public class UserRestController {

	@Autowired
	private UserService userService;

	// 전체 유저목록 가져와~~
//	@GetMapping("/users")
//	public List<User> userList() {
//		return userService.getUserList();
//	}

	// 회원가입을 해보자 (form data 형식으로 넘어왔다.
	@PostMapping("/signup")
	public ResponseEntity<Integer> signup(User user) {
		int result = userService.signup(user);
		//result 가 0이면 등록 x, 1이면 등록 된거야~~
		//등록한 유저정보를 반환하겠다. Integer 대시 User 요런것을 반환하면 좋겠다.
		return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
	}

	// 로그인 
	@PostMapping("/login")
	public ResponseEntity<?> login(User user, HttpSession session) {
		User tmp = userService.login(user.getId(), user.getPassword());
		if(tmp == null) {
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
		session.setAttribute("loginUser", tmp);
		return new ResponseEntity<String>(tmp.getName(), HttpStatus.OK);
	}

	// 로그아웃
	@GetMapping("logout")
	public ResponseEntity<Void> logout(HttpSession session) {
//		session.removeAttribute("loginUser");
		session.invalidate();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
