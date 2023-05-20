package com.ssafy.xten.model.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.xten.model.dao.UserDao;
import com.ssafy.xten.model.dto.Image;
import com.ssafy.xten.model.dto.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public int signup(User user) {
		MultipartFile file = user.getFile();
		if (file != null && !file.isEmpty()) {
			String changeName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
			user.setImg(changeName);
			
		}

		return userDao.insertUser(user);
	}

	@Override
	public User login(String id, String password) {
		User tmp = userDao.selectOne(id);
		if (tmp != null && tmp.getPassword().equals(password))
			return tmp;
		return null;
	}

	@Override
	public List<User> getUserList() {
		return userDao.selectAllUser();
	}

	@Override
	public int idCheck(String id) {
		return userDao.idCheck(id);
	}

	@Override
	public int emailCheck(String email) {
		return userDao.emailCheck(email);
	}

	@Override
	public void changePassword(int userSeq, String newPassword) {
		Map map = new HashMap();
		map.put("userSeq", userSeq);
		map.put("password", newPassword);
		userDao.updatePassword(map);
	}

	@Override
	public int verifyPassword(int userSeq, String password) {
		Map map = new HashMap();
		map.put("userSeq", userSeq);
		map.put("password", password);
		return userDao.passwordCheck(map);
	}

	@Override
	public int addProfileImage(MultipartFile file) throws IOException {
		Image img = new Image();
		img.setMimetype(file.getContentType());
		img.setOriginal_name(file.getOriginalFilename());
		img.setData(file.getBytes());
		userDao.insertImage(img);
		return img.getImageSeq();
	}
}
