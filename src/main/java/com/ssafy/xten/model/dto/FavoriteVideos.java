package com.ssafy.xten.model.dto;

public class FavoriteVideos {
	private int favoriteVideosSeq;
	private int userSeq;
	private String videoUrl;

	public FavoriteVideos(int favoriteVideosSeq, int userSeq, String videoUrl) {
		super();
		this.favoriteVideosSeq = favoriteVideosSeq;
		this.userSeq = userSeq;
		this.videoUrl = videoUrl;
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

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public FavoriteVideos() {
	}

	@Override
	public String toString() {
		return "FavoriteVideos [favoriteVideosSeq=" + favoriteVideosSeq + ", userSeq=" + userSeq + ", videoUrl="
				+ videoUrl + "]";
	}

}
