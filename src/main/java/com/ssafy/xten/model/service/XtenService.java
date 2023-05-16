package com.ssafy.xten.model.service;

import java.util.List;

import com.ssafy.xten.model.dto.Exercise;

import com.ssafy.xten.model.dto.Exercise;

//사용자 친화적으로
public interface XtenService {
	//전체 운동 조회
	public List<Exercise> getExerciseList();
	
	//운동 상세조회 
	public Exercise readExercise(int seq); 
	
	//찜한영상 추가 
	public void addFavoriteVideos(String videoUrl);
	
	//찜한영상 삭제 
	public void removeFavoriteVideos(int seq);
	
	//찜한운동 추가 
	public void addFavoriteExercises(int seq);
	
	//찜한운동 삭제 
	public void removeFavoriteExercises(int seq);
	
	
	
}
