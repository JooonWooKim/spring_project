package com.cos.new_project.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cos.new_project.model.Board;
import com.cos.new_project.repository.BoardRepository;
import com.cos.new_project.repository.RecommendRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RecommendService {

	private final RecommendRepository recommendRepository;
	
	@Transactional
	public void recommend(Long board_id, Long principal_id) {
		recommendRepository.recommend(board_id, principal_id);
//		recommendRepository.increaseRecommendCount(board_id);
	}
	
	@Transactional
	public void cancelRecommend(Long board_id, Long principal_id) {
		recommendRepository.cancelRecommend(board_id, principal_id);
//		recommendRepository.decreaseRecommendCount(board_id);
	}
}
