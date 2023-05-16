package com.ssafy.xten.model.dto;

public class FavoriteExercises {
	private int favoriteExercisesSeq;
	private int exerciseSeq;
	private int userSeq;

	public FavoriteExercises() {
	}

	public FavoriteExercises(int favoriteExercisesSeq, int exerciseSeq, int userSeq) {
		super();
		this.favoriteExercisesSeq = favoriteExercisesSeq;
		this.exerciseSeq = exerciseSeq;
		this.userSeq = userSeq;
	}

	public int getFavoriteExercisesSeq() {
		return favoriteExercisesSeq;
	}

	public void setFavoriteExercisesSeq(int favoriteExercisesSeq) {
		this.favoriteExercisesSeq = favoriteExercisesSeq;
	}

	public int getExerciseSeq() {
		return exerciseSeq;
	}

	public void setExerciseSeq(int exerciseSeq) {
		this.exerciseSeq = exerciseSeq;
	}

	public int getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}

	
}
