package com.ssafy.xten.model.service;

import java.nio.charset.CodingErrorAction;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.xten.model.dao.XtenDao;
import com.ssafy.xten.model.dto.Exercise;

@Service
public class XtenServiceImpl implements XtenService {

	private XtenDao xtenDao;

	// Dao 인스턴스 주입
	@Autowired
	public void setXtenDao(XtenDao xtenDao) {
		this.xtenDao = xtenDao;
	}

	@Override
	public List<Exercise> getExerciseList() {
		return xtenDao.selectAllExercises();
	}

	@Override
	public Exercise readExercise(int seq) {
		return xtenDao.getExercise(seq);
	}

	@Transactional
	@Override
	public void addFavoriteVideos(String videoUrl) {
		xtenDao.addFavoriteVideos(videoUrl);
	}

	@Transactional
	@Override
	public void removeFavoriteExercises(int seq) {
		xtenDao.removeFavoriteExercises(seq);
	}

	@Override
	public void addFavoriteExercises(int seq) {
		xtenDao.addFavoriteExercises(seq);
	}

	@Override
	public void removeFavoriteVideos(int seq) {
		xtenDao.removeFavoriteExercises(seq);
	}
}
