package com.cos.new_project.service;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.cos.new_project.model.RoleType;
import com.cos.new_project.model.User;
import com.cos.new_project.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional(readOnly = true)
	public User 회원찾기(String username) {
		User user = userRepository.findByUsername(username).orElseGet(()->{
			return new User();
		});
		return user;
	}
	
	@Transactional
	public void 회원가입(User user) {
		String rawPassword = user.getPassword();//1234원문
		String encPassword = encoder.encode(rawPassword);	//해쉬화
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}
		
	@Transactional
	public void 회원수정(User user) {
		User persistance = userRepository.findById(user.getId())
				.orElseThrow(()->{
					return new IllegalArgumentException("회원 찾기 실패");
				});
		
		if(persistance.getOauth() == null || persistance.getOauth().equals("")) {
			String rawpassword = user.getPassword();
			String encPassword = encoder.encode(rawpassword);
			persistance.setPassword(encPassword);
			persistance.setEmail(user.getEmail());
		}
	}
	
	//회원가입 시, 유효성 체
	@Transactional(readOnly = true)
	public Map<String, String> validationHandling(BindingResult bindingResult){
		Map<String, String>validatorResult = new HashMap<>();
		
		for(FieldError error : bindingResult.getFieldErrors()) {
			String valiKeyName = String.format("valid_%s",error.getField());
			validatorResult.put(valiKeyName, error.getDefaultMessage());
		}
		return validatorResult;
	}
}
