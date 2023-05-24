package com.ssafy.xten.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.xten.model.dto.Exercise;
import com.ssafy.xten.model.dto.FavoriteExercises;
import com.ssafy.xten.model.dto.FavoriteVideos;
import com.ssafy.xten.model.dto.SearchCondition;
import com.ssafy.xten.model.dto.TestResult;
import com.ssafy.xten.model.service.XtenService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api")
@Api(tags = "X10 컨트롤러")
public class XtenRestController {

	@Autowired
	private XtenService xtenService;

	// 전체 운동 가져오기
	@ApiOperation(value = "등록된 모든 운동 조회", notes = "전부 가져와")
	@GetMapping("/xten/exercises")
	public ResponseEntity<?> getExercises() {
		List<Exercise> list = xtenService.getAllExercises();
		return new ResponseEntity<List<Exercise>>(list, HttpStatus.OK);
	}

	// 사용자의 모든 테스트 결과 가져오기
	@ApiOperation(value = "사용자의 모든 테스트 결과 조회", notes = "유저 일련번호로 조회")
	@GetMapping("/xten/testresults/{userSeq}")
	public ResponseEntity<?> getTestResults(@PathVariable int userSeq) {
		List<TestResult> list = xtenService.getTestResults(userSeq);
		if (list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<TestResult>>(list, HttpStatus.OK);
	}

	// 찜한영상 가져오기
	@ApiOperation(value = "사용자가 찜한 영상 조회", notes = "유저 일련번호로 조회")
	@GetMapping("/xten/favoritevideos/{userSeq}")
	public ResponseEntity<?> getFavoriteVideos(@PathVariable int userSeq) {
		List<FavoriteVideos> list = xtenService.getFavoriteVideos(userSeq);
		if (list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<FavoriteVideos>>(list, HttpStatus.OK);
	}

	// 찜한운동 가져오기
	@ApiOperation(value = "사용자가 찜한 운동 조회", notes = "유저 일련번호로 조회")
	@GetMapping("/xten/favoriteexercises/{userSeq}")
	public ResponseEntity<?> getFavoriteExercises(@PathVariable int userSeq) {
		List<FavoriteExercises> list = xtenService.getFavoriteExercises(userSeq);
		if (list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<FavoriteExercises>>(list, HttpStatus.OK);
	}

	// 찜한운동에 추가
	@ApiOperation(value = "찜한 운동 목록에 추가 ", notes = "유저 일련번호, 운동 일련번호 입력해서 추가")
	@PostMapping("/xten/favoriteexercises/{userSeq}/{exerciseSeq}")
	public ResponseEntity<?> addFavoriteExercises(@PathVariable int userSeq, @PathVariable int exerciseSeq) {
		xtenService.addFavoriteExercises(userSeq, exerciseSeq);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// 찜한운동에서 삭제
	@ApiOperation(value = "찜한 운동 목록에서 삭제 ", notes = "유저 일련번호, 찜한 운동 일련번호 입력해서 삭제")
	@DeleteMapping("/xten/favoriteexercises/{userSeq}/{exerciseSeq}")
	public ResponseEntity<?> removeFavoriteExercises(@PathVariable int userSeq, @PathVariable int exerciseSeq) {
		xtenService.removeFavoriteExercises(userSeq, exerciseSeq);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	// 찜한영상에 추가
	@ApiOperation(value = "찜한 영상 목록에 추가", notes = "유저 일련번호, 비디오url 입력해서 추가")
	@PostMapping("/xten/favoritevideos")
	public ResponseEntity<?> addFavoriteVideos(FavoriteVideos fv) {
		int userSeq = fv.getUserSeq();
		String videoUrl = fv.getVideoUrl();
		String videoTitle= fv.getVideoTitle();
		xtenService.addFavoriteVideos(userSeq, videoUrl, videoTitle);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// 찜한영상에서 삭제
	@ApiOperation(value = "찜한 영상 목록에서 삭제 ", notes = "유저 일련번호, 찜한 영상 일련번호 입력해서 삭제")
	@DeleteMapping("/xten/favoritevideos")
	public ResponseEntity<?> removeFavoriteVideos(@RequestBody FavoriteVideos fv) {
		int userSeq = fv.getUserSeq();
		String videoUrl = fv.getVideoUrl();
		xtenService.removeFavoriteVideos(userSeq, videoUrl);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// 테스트 결과 저장
	@ApiOperation(value = "테스트 결과 저장 ", notes = "유저 일련번호, 운동 일련번호 입력해서 추가")
	@PostMapping("/xten/testresults/{userSeq}/{exerciseSeq}")
	public ResponseEntity<?> addTestResult(@PathVariable int userSeq, @PathVariable int exerciseSeq) {
		xtenService.addTestResult(userSeq, exerciseSeq);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// 테스트 결과 삭제
	@ApiOperation(value = "테스트 결과 삭제 ", notes = "유저 일련번호, 테스트 결과 일련번호 입력해서 추가")
	@DeleteMapping("/xten/testresults/{userSeq}/{testResultSeq}")
	public ResponseEntity<?> removeTestResult(@PathVariable int userSeq, @PathVariable int testResultSeq) {
		xtenService.removeTestResult(userSeq,testResultSeq);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
