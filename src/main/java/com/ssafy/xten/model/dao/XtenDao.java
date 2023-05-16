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
	public List<TestResult> selectTestResults(int userSeq);

	// 찜한영상 가져오기
	public List<FavoriteVideos> selectFavoriteVideos(int userSeq);

	// 찜한운동 가져오기
	public List<FavoriteExercises> selectFavoriteExercises(int userSeq);

	// 찜한영상에 추가
	public void insertFavoriteVideos(int userSeq, String videoUrl);

	// 찜한영상에서 삭제
	public void deleteFavoriteVideos(int userSeq, int favoriteVideoSeq);

	// 찜한운동에 추가
	public void insertFavoriteExercises(int userSeq, int exerciseSeq);

	// 찜한운동에서 삭제
	public void deleteFavoriteExercises(int userSeq, int favoriteExerciseSeq);

	// 테스트 결과 저장
	public void insertTestResult(int userSeq, int exerciseSeq);

	// 테스트 결과 삭제
	public void deleteTestResult(int userSeq, int testResultSeq);
}
