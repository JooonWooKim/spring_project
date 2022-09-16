package com.cos.new_project.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.new_project.model.Board;
import com.cos.new_project.model.RoleType;
import com.cos.new_project.model.User;
import com.cos.new_project.repository.BoardRepository;
import com.cos.new_project.repository.UserRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	@Transactional
	public void 글쓰기(Board board, User user) {
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
}
