package com.cos.new_project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.new_project.model.Board;
import com.cos.new_project.model.User;

//DAO
public interface BoardRepository extends JpaRepository<Board, Integer>{
	
}
