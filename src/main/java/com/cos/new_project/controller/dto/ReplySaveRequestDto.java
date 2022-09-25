package com.cos.new_project.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReplySaveRequestDto {
	private int userId;
	private int boardId;
	private String content;
}
