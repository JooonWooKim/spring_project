package com.cos.new_project.controller.api;


import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.new_project.controller.dto.ResponseDto;
import com.cos.new_project.model.User;
import com.cos.new_project.service.UserService;
import com.cos.new_project.validator.CheckEmailValidator;
import com.cos.new_project.validator.CheckUsernameValidator;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserApiController {

	@Autowired
	private UserService userService;
	private final CheckUsernameValidator checkUsernameValidator;
	private final CheckEmailValidator checkEmailValidator;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@InitBinder
	public void validatorBinder(WebDataBinder binder) {
		binder.addValidators(checkUsernameValidator);
		binder.addValidators(checkEmailValidator);
	}
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<?> joinProc(@Valid @RequestBody User user, BindingResult bindingResult) {
		//username, password, email
		if(bindingResult.hasErrors()) {
			//회원가입 실패시 입력 데이터 값을 유
			Map<String, String> validatorResult = userService.validationHandling(bindingResult);
			//실패면 로그인페이지이
			return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), validatorResult);
		}
		System.out.println("save호출");
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
		//1이면 성공, -1이면 실패
	}
	
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user) {
		userService.회원수정(user);
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
}
