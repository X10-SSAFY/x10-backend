package com.ssafy.xten.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.xten.model.dao.UserDao;
import com.ssafy.xten.model.dto.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public int signup(User user) {
		return userDao.insertUser(user);
	}

	@Override
	public User login(String id, String password) {
		User tmp = userDao.selectOne(id);
		if (tmp != null && tmp.getPassword().equals(password))
			return tmp;
		return null;
	}

}
