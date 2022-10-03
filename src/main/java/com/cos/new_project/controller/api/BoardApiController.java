package com.cos.new_project.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.new_project.config.auth.PrincipalDetail;
import com.cos.new_project.controller.dto.ReplySaveRequestDto;
import com.cos.new_project.controller.dto.ResponseDto;
import com.cos.new_project.model.Board;
import com.cos.new_project.model.Reply;
import com.cos.new_project.service.BoardService;
//import com.cos.new_project.service.RecommendService;
import com.cos.new_project.service.RecommendService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	
	private final RecommendService recommendService;
	
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
		boardService.글쓰기(board, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id){
		boardService.글삭제하기(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer>update(@PathVariable int id, @RequestBody Board board){
		boardService.글수정하기(id, board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@PostMapping("/api/board/{boardId}/reply")
	public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto) {
		boardService.댓글작성(replySaveRequestDto);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@DeleteMapping("/api/board/{boardId}/reply/{replyId}")
	public ResponseDto<Integer>replyDelete(@PathVariable int replyId){
		boardService.댓글삭제(replyId);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@PostMapping("/api/board/{board_id}/recommend")
	public ResponseDto<Integer> recommend(@PathVariable("board_id") Long board_id ,@AuthenticationPrincipal PrincipalDetail principal){
		recommendService.recommend(board_id, principal.getUser().getId());
		return new ResponseDto<Integer>(HttpStatus.CREATED.value(), 1);
	}
	
	@DeleteMapping("/api/board/{board_id}/recommend")
	public ResponseDto<Integer> cancelRecommend(@PathVariable("board_id") Long board_id, @AuthenticationPrincipal PrincipalDetail principal){
		recommendService.cancelRecommend(board_id, principal.getUser().getId());
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

}
