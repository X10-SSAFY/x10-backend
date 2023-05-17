package com.ssafy.xten.model.dto;

public class User {
	private int userSeq;
	private String id;
	private String password;
	private String email;
	private String nickname; // 사용자 닉네임
	private String img; // 사용자 프로필 이미지

	public User() {
	}

	public User(int userSeq, String id, String password, String email, String name, String img) {
		super();
		this.userSeq = userSeq;
		this.id = id;
		this.password = password;
		this.email = email;
		this.nickname = nickname;
		this.img = img;
	}

	public int getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickame(String nickname) {
		this.nickname = nickname;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

}
