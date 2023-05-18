package com.ssafy.xten.model.service;

import java.util.List;

import com.ssafy.xten.model.dto.User;

public interface UserService {
	public int idCheck(String id);

	public int emailCheck(String email);

	public int signup(User user);

	public User login(String id, String password);

	public List<User> getUserList();
	
	// 비밀번호 변경
	public void changePassword(int userSeq, String newPassword);
	
	//비밀번호 확인
	public int verifyPassword(int userSeq, String password);
}
