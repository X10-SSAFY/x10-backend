package com.ssafy.xten.model.dto;

public class FavoriteVideos {
	private int favoriteVideosSeq;
	private int userSeq;

	public FavoriteVideos() {
	}

	public FavoriteVideos(int favoriteVideosSeq, int userSeq) {
		super();
		this.favoriteVideosSeq = favoriteVideosSeq;
		this.userSeq = userSeq;
	}

	public int getFavoriteVideosSeq() {
		return favoriteVideosSeq;
	}

	public void setFavoriteVideosSeq(int favoriteVideosSeq) {
		this.favoriteVideosSeq = favoriteVideosSeq;
	}

	public int getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}

}
