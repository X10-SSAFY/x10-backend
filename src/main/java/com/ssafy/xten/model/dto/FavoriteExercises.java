package com.ssafy.xten.model.dto;

public class FavoriteExercises {
	private int favoriteExercisesSeq;
	private int userSeq;

	public FavoriteExercises() {
	}
	public FavoriteExercises(int favoriteExercisesSeq, int userSeq) {
		super();
		this.favoriteExercisesSeq = favoriteExercisesSeq;
		this.userSeq = userSeq;
	}
	public int getFavoriteExercisesSeq() {
		return favoriteExercisesSeq;
	}
	public void setFavoriteExercisesSeq(int favoriteExercisesSeq) {
		this.favoriteExercisesSeq = favoriteExercisesSeq;
	}
	public int getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}
	
}
