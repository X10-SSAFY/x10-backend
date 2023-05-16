package com.ssafy.xten.model.dao;

import java.util.List;

import com.ssafy.xten.model.dto.Exercise;
import com.ssafy.xten.model.dto.FavoriteExercises;
import com.ssafy.xten.model.dto.FavoriteVideos;
import com.ssafy.xten.model.dto.TestResult;

public interface XtenDao {
	// 둘러보기 : 전체 운동 가져오기
	public List<Exercise> selectAllExercises();

	// 사용자의 모든 테스트 결과 가져오기
	public List<TestResult> getTestResultByUserSeq(int seq);

	// 찜한영상 가져오기
	public List<FavoriteVideos> getFavoriteVideosByUserSeq(int seq);

	// 찜한운동 가져오기
	public List<FavoriteExercises> getFavoriteExercisesByUserSeq(int seq);

	// 운동 상세정보 가져오기
	public Exercise getExercise(int seq);

	// 현재 영상을 찜한영상에 추가
	public void addFavoriteVideos(String videoUrl);

	// 현재 영상을 찜한영상에서 삭제
	public void removeFavoriteVideos(int seq);

	// 현재 운동을 찜한운동에 추가
	public void addFavoriteExercises(int seq);

	// 현재 운동을 찜한운동에서 삭제
	public void removeFavoriteExercises(int seq);
}
