package com.cos.new_project.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.cos.new_project.model.User;
import com.cos.new_project.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CheckUsernameValidator extends AbstractValidator<User>{
	private final UserRepository userRepository;

	@Override
	protected void doValidate(User dto, Errors errors) {
		if(userRepository.existsByUsername(dto.getUsername())) {
			errors.rejectValue("username", "아이디 중복 오류", "이미 사용중인 아이디입니다.");
		}
	}
	
}
