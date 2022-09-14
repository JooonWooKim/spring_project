package com.cos.new_project.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.new_project.controller.dto.ResponseDto;
import com.cos.new_project.model.RoleType;
import com.cos.new_project.model.User;
import com.cos.new_project.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) {
		//username, password, email
		System.out.println("save호출");
		user.setRole(RoleType.USER);
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
		//1이면 성공, -1이면 실패
	}
	
//	@PostMapping("/api/user/login")
//	public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
//		//username, password, email
//		System.out.println("login호출");
//		User principal = userService.로그인(user);
//	
//		if(principal != null) {
//			session.setAttribute("principal", principal);
//		}
//		//principal(접근주체)
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//		//1이면 성공, -1이면 실패
//	}
	
	
}
