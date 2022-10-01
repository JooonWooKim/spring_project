package com.cos.new_project.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.cos.new_project.model.User;
import com.cos.new_project.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CheckEmailValidator extends AbstractValidator<User>{
	private final UserRepository userRepository;

	@Override
	protected void doValidate(User dto, Errors errors) {
		if(userRepository.existsByUsername(dto.getEmail())) {
			errors.rejectValue("email", "이메일 중복 오류", "이미 사용중인 이메일입니다.");
		}
	}
	
}
