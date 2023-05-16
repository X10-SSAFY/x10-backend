package com.ssafy.xten.model.service;

import java.util.List;

import com.ssafy.xten.model.dto.User;

public interface UserService {
	public int signup(User user);
	public User login(String id, String password);
}
