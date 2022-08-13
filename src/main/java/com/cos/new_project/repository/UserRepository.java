package com.cos.new_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.new_project.model.User;

//DAO
public interface UserRepository extends JpaRepository<User, Integer>{
	
}
