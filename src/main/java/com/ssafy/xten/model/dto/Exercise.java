package com.ssafy.xten.model.dto;

public class Exercise {
	private int exerciseSeq; // 운동 일련번호
	private String exerciseName; // 운동 이름
	private String exerciseImage; // 운동 대표 이미지

	public Exercise() {
	}

	public Exercise(int exerciseSeq, String exerciseName, String exerciseImage) {
		super();
		this.exerciseSeq = exerciseSeq;
		this.exerciseName = exerciseName;
		this.exerciseImage = exerciseImage;
	}

	public int getExerciseSeq() {
		return exerciseSeq;
	}

	public void setExerciseSeq(int exerciseSeq) {
		this.exerciseSeq = exerciseSeq;
	}

	public String getExerciseName() {
		return exerciseName;
	}


	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}

	public String getExerciseImage() {
		return exerciseImage;
	}

	public void setExerciseImage(String exerciseImage) {
		this.exerciseImage = exerciseImage;
	}
	
	@Override
	public String toString() {
		return "Exercise [exerciseSeq=" + exerciseSeq + ", exerciseName=" + exerciseName + ", exerciseImage="
				+ exerciseImage + "]";
	}

}
