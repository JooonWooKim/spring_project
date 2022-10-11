package com.cos.new_project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.new_project.model.Board;
import com.cos.new_project.model.Recommend;

public interface RecommendRepository extends JpaRepository<Recommend, Long>{

	@Modifying
	@Query(value = "INSERT INTO recommend(board_id, user_id) VALUES(:board_id, :principal_id)", nativeQuery = true)
	//추천 
	int recommend(Long board_id, Long principal_id);
	
	@Modifying
	@Query(value = "DELETE FROM recommend WHERE board_id = :board_id AND user_id = :principal_id", nativeQuery = true)
	//추천 취소 
	int cancelRecommend(Long board_id, Long principal_id);

//	@Modifying
//	@Query(value = "update Board b set b.recommendCount = b.recommendCount + 1 where b.id = :board_id")
//	void increaseRecommendCount(Long board_id);
//
//	@Modifying
//	@Query(value = "update Board b set b.recommendCount = b.recommendCount - 1 where b.id = :board_id")
//	void decreaseRecommendCount(Long board_id);
}