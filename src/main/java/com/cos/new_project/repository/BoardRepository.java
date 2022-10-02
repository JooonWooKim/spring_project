package com.cos.new_project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.new_project.model.Board;
import com.cos.new_project.model.User;

//DAO
public interface BoardRepository extends JpaRepository<Board, Integer>{
	
	@Modifying
	@Query("update Board b set b.count = b.count + 1 where b.id = :id")
	void updateCount(int id);
}
