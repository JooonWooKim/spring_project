package com.cos.new_project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.cos.new_project.config.auth.PrincipalDetail;
import com.cos.new_project.repository.BoardRepository;
import com.cos.new_project.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private BoardRepository boardRepository;
	
//	@AuthenticationPrincipal principalDetail principal

	@GetMapping({"", "/"})
	public String index(Model model, @PageableDefault(size=3,sort="id",direction=Sort.Direction.DESC)Pageable pageable,
			@RequestParam(value= "searchKeyword", required = false)String searchKeyword) {
		if(searchKeyword == null) {
			model.addAttribute("boards", boardService.글목록(pageable));
		}else {
			model.addAttribute("boards", boardRepository.findByTitleContaining(searchKeyword, pageable));
		}
		return "index";
	}
	
	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id, Model model, HttpServletRequest httpServletRequest, HttpServletResponse response) {
		model.addAttribute("board", boardService.글상세보기(id, httpServletRequest, response));
		return "board/detail";
	}
	
	@GetMapping("board/{id}/updateForm")
	public String upateForm(@PathVariable int id, Model model, HttpServletRequest httpServletRequest, HttpServletResponse response) {
		model.addAttribute("board", boardService.글상세보기(id, httpServletRequest, response));
		return "board/updateForm";
	}
	
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
}
