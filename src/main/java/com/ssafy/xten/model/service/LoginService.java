package com.ssafy.xten.model.service;

import com.ssafy.xten.model.dto.User;

public interface LoginService {

	public User socialLogin(String code, String registrationId);
	
	public User normalLogin(String id, String password);
}
