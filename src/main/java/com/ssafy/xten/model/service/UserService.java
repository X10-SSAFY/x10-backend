package com.ssafy.xten.model.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.xten.model.dto.Image;
import com.ssafy.xten.model.dto.User;

public interface UserService {
	
	public User getUser(String id);
	
	public int idCheck(String id);

	public int emailCheck(String email);

	public int signup(User user);

	public List<User> getUserList();
	
	// 비밀번호 변경
	public void changePassword(int userSeq, String newPassword);
	
	//비밀번호 확인
	public int verifyPassword(int userSeq, String password);
	
	//이미지 업로드
	public void addProfileImage(int userSeq, MultipartFile file) throws IOException;
	
	//이미지 다운로드
	public Image getProfileImage(int userSeq);
	
	//이미지 삭제
	public void removeProfileImage(int userSeq);

	public void editUser(int userSeq, User user);

	public User getUserBySeq(int userSeq);

	public User getUserByEmail(String email);
	
}
