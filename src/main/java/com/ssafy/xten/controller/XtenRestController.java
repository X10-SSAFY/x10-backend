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
import com.ssafy.xten.model.dto.SearchCondition;
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
	
	// 전체 운동 목록 조회
	@ApiOperation(value="운동 조회", notes = "")
	@GetMapping("/xten/exercises")
	public ResponseEntity<?> list(SearchCondition condition){
		List<Exercise> list = xtenService.getExerciseList(); 
		
		if(list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Exercise>>(list, HttpStatus.OK);
	}
	// 운동 상세 보기 
	@GetMapping("/xten/exercises/{id}")
	public ResponseEntity<Exercise> detail(@PathVariable int id){
		Exercise exercise = xtenService.readExercise(id);
		return new ResponseEntity<Exercise>(exercise, HttpStatus.OK);
	}
	
	// 요청받은 url의 영상을 찜한영상에 추가 
	@PostMapping("/xten/videos/{url}")
	public ResponseEntity<Void> write(@PathVariable String url){
		xtenService.addFavoriteVideos(url);
		//지금 우리의 게시글은 키가 절대로 중복이 되지 않는다. 그래서 무조건 등록은 될꺼임... 
		//가끔가다가 혹여나 여기말고 다른곳에서 문제가 발생해서 글이 등록되지 않았다... 
		//DB에 댕겨올때 테이블을 변경하는 작업이라면 무엇인가를 하나 돌려줌... 무엇? 테이블을 건드린 행의 개수가 반환이된다.
		//만약에 0이라면 이거 등록 안된거니까 등록 안됬어요 ㅠㅠ 하고 프론트에게 돌려주어야 겠다.
		//그게 아니라면 잘 등록이 된거니까... OK 보내도 가넝
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	//4. 삭제
	@DeleteMapping("/exercises/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id){
		xtenService.readExercise(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
}
