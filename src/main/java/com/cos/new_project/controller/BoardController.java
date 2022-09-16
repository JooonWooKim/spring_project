package com.cos.new_project.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.new_project.config.auth.PrincipalDetail;

@Controller
public class BoardController {

	
//	@AuthenticationPrincipal principalDetail principal
	@GetMapping({"", "/"})
	public String index(@AuthenticationPrincipal PrincipalDetail principal) {
		System.out.println("로그인 사용자 아이디:" + principal.getUsername());
		return "index";
	}
	
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
}
