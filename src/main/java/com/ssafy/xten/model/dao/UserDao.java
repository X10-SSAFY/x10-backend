package com.ssafy.xten.model.dao;

import java.util.List;
import java.util.Map;

import com.ssafy.xten.model.dto.Image;
import com.ssafy.xten.model.dto.User;

public interface UserDao {
	

	public int idCheck(String id); 	//아이디 중복체크
	
	public int emailCheck(String email); //이메일 중복체크
	
	public int insertUser(User user);

	public User selectOne(String id); // 로그인을 위해
	
	public User selectOneBySeq(int userSeq); // 로그인을 위해
	
	public User selectOneByEmail(String email); // 소셜로그인을 위해
	
	public List<User> selectAllUser();

	public void updatePassword(Map map);

	public int passwordCheck(Map map);
	
	public void insertImage(Map map);
	
	public Image selectImage(int userSeq);
	
	public void deleteImage(int userSeq);

	public void updateUser(Map map);

}