package com.cos.new_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.new_project.config.auth.PrincipalDetail;
import com.cos.new_project.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
//	@AuthenticationPrincipal principalDetail principal

	@GetMapping({"", "/"})
	public String index() {
		return "index";
	}
	
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
}
