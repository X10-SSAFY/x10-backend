package com.ssafy.xten.model.service;

import java.nio.charset.CodingErrorAction;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.xten.model.dao.XtenDao;
import com.ssafy.xten.model.dto.Exercise;
import com.ssafy.xten.model.dto.FavoriteExercises;
import com.ssafy.xten.model.dto.FavoriteVideos;
import com.ssafy.xten.model.dto.TestResult;

@Service
public class XtenServiceImpl implements XtenService {

	private XtenDao xtenDao;

	// Dao 인스턴스 주입
	@Autowired
	public void setXtenDao(XtenDao xtenDao) {
		this.xtenDao = xtenDao;
	}

	@Override
	public List<Exercise> getAllExercises() {
		return xtenDao.selectAllExercises();
	}

	@Override
	public List<TestResult> getTestResults(int userSeq) {
		return xtenDao.selectTestResults(userSeq);
	}

	@Override
	public List<FavoriteVideos> getFavoriteVideos(int userSeq) {
		return xtenDao.selectFavoriteVideos(userSeq);
	}

	@Override
	public List<FavoriteExercises> getFavoriteExercises(int userSeq) {
		return xtenDao.selectFavoriteExercises(userSeq);
	}

	@Override
	public void addFavoriteVideos(int userSeq, String videoUrl, String videoTitle) {
		FavoriteVideos favoriteVideo = new FavoriteVideos();
		favoriteVideo.setUserSeq(userSeq);
		favoriteVideo.setVideoUrl(videoUrl);
		favoriteVideo.setVideoTitle(videoTitle);
		xtenDao.insertFavoriteVideos(favoriteVideo);
	}

	@Override
	public void removeFavoriteVideos(int userSeq, String videoUrl) {
		Map map = new HashMap();
		map.put("userSeq",userSeq);
		map.put("videoUrl",videoUrl);
		xtenDao.deleteFavoriteVideos(map);
	}

	@Override
	public void addFavoriteExercises(int userSeq, int exerciseSeq) {
		FavoriteExercises favoriteExercise = new FavoriteExercises();
		favoriteExercise.setUserSeq(userSeq);
		favoriteExercise.setExerciseSeq(exerciseSeq);
		xtenDao.insertFavoriteExercises(favoriteExercise);
	}

	@Override
	public void removeFavoriteExercises(int userSeq, int exerciseSeq) {
		xtenDao.deleteFavoriteExercises(userSeq, exerciseSeq);
	}

	@Override
	public void addTestResult(int userSeq, int exerciseSeq) {
		TestResult testResult = new TestResult();
		testResult.setUserSeq(userSeq);
		testResult.setExerciseSeq(exerciseSeq);
		xtenDao.insertTestResult(testResult);
	}

	@Override
	public void removeTestResult(int userSeq, int testResultSeq) {
		xtenDao.deleteTestResult(userSeq, testResultSeq);
	}


}
