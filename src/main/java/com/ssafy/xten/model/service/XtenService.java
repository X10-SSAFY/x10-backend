package com.ssafy.xten.model.service;

import java.util.List;

import com.ssafy.xten.model.dto.Exercise;
import com.ssafy.xten.model.dto.FavoriteExercises;
import com.ssafy.xten.model.dto.FavoriteVideos;
import com.ssafy.xten.model.dto.TestResult;

//사용자 친화적으로
public interface XtenService {
	// 둘러보기 : 전체 운동 가져오기
	public List<Exercise> getAllExercises();

	// 사용자의 모든 테스트 결과 가져오기
	public List<TestResult> getTestResults(int seq);

	// 찜한영상 가져오기
	public List<FavoriteVideos> getFavoriteVideos(int seq);

	// 찜한운동 가져오기
	public List<FavoriteExercises> getFavoriteExercises(int seq);

	// 찜한영상에 추가
	public void addFavoriteVideos(int userSeq, String videoUrl, String videoTitle);

	// 찜한영상에서 삭제
	public void removeFavoriteVideos(int userSeq, String videoUrl);

	// 찜한운동에 추가
	public void addFavoriteExercises(int userSeq, int exerciseSeq);

	// 찜한운동에서 삭제
	public void removeFavoriteExercises(int userSeq, int exerciseSeq);

	// 테스트 결과 저장
	public void addTestResult(int userSeq, int exerciseSeq);

	// 테스트 결과 삭제
	public void removeTestResult(int userSeq, int testResultSeq);
	
}
