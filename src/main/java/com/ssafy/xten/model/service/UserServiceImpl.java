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
		return userDao.insertUser(user);
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
	public void addProfileImage(int userSeq, MultipartFile file) throws IOException {
		Image img = new Image();
		img.setImageType(file.getContentType());
		img.setImageName(file.getOriginalFilename());
		img.setImageData(file.getBytes());
		Map map = new HashMap();
		map.put("userSeq", userSeq);
		map.put("Image", img);
		userDao.insertImage(map);
	}

	@Override
	public User getUser(String id) {
		return userDao.selectOne(id);
	}

	@Override
	public Image getProfileImage(int userSeq) {
		return userDao.selectImage(userSeq);
	}

	@Override
	public void removeProfileImage(int userSeq) {
		userDao.deleteImage(userSeq);
	}

	@Override
	public void editUser(int userSeq, User user) {
		Map map = new HashMap();
		map.put("userSeq", userSeq);
		map.put("User", user);
		userDao.updateUser(map);
	}

	@Override
	public User getUserBySeq(int userSeq) {
		return userDao.selectOneBySeq(userSeq);
	}

	@Override
	public User getUserByEmail(String email) {
		return userDao.selectOneByEmail(email);
	}

}
