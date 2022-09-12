package com.cos.new_project.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.new_project.controller.dto.ResponserDto;
import com.cos.new_project.model.User;
import com.cos.new_project.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/api/user")
	public ResponserDto<Integer> save(@RequestBody User user) {
		//username, password, email
		System.out.println("save호출");
		int result = userService.회원가입(user);
		return new ResponserDto<Integer>(HttpStatus.OK, result);
		//1이면 성공, -1이면 실패
	}
}
