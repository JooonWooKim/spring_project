package com.cos.new_project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.new_project.model.User;

//DAO
public interface UserRepository extends JpaRepository<User, Long>{

	//select * from user where username = ?;
	Optional<User> findByUsername(String username);

	boolean existsByUsername(String username);
	//boolean existsByPassword(String password);
	boolean existsByEmail(String email);
}
	//JPA Naming 쿼리
	//select * from user where username = ?(username) and password = ?(password)
//	User findByUsernameAndPassword(String username, String password);
	
//	@Query(value="select * from user where username = ? and password = ?", nativeQuery=true)
//	User login(String username, String password);

