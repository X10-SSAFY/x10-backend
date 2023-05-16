package com.ssafy.xten.model.dto;

public class Video {
	private int videoSeq;
	private int favoriteVideosSeq;
	private int exerciseSeq;
	private String videoUrl;

	public Video() {
	}

	public Video(int videoSeq, int favoriteVideosSeq, int exerciseSeq, String videoUrl) {
		super();
		this.videoSeq = videoSeq;
		this.favoriteVideosSeq = favoriteVideosSeq;
		this.exerciseSeq = exerciseSeq;
		this.videoUrl = videoUrl;
	}

	public int getVideoSeq() {
		return videoSeq;
	}

	public void setVideoSeq(int videoSeq) {
		this.videoSeq = videoSeq; 
	}

	public int getFavoriteVideosSeq() {
		return favoriteVideosSeq;
	}

	public void setFavoriteVideosSeq(int favoriteVideosSeq) {
		this.favoriteVideosSeq = favoriteVideosSeq;
	}

	public int getExerciseSeq() {
		return exerciseSeq;
	}

	public void setExerciseSeq(int exerciseSeq) {
		this.exerciseSeq = exerciseSeq;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

}
