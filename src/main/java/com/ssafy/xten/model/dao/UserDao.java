package com.ssafy.xten.model.dao;

import com.ssafy.xten.model.dto.User;

public interface UserDao {
	public int insertUser(User user);

	public User selectOne(String id); //로그인을 위해
}