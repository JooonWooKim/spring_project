package com.cos.new_project.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.new_project.model.User;
import com.cos.new_project.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public int 회원가입(User user) {
		try {
			userRepository.save(user);
			return 1;
			//정상이면 1, 비정상이면 -1
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return -1;
	}
}
