package com.cos.new_project.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.new_project.controller.dto.ResponseDto;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value=Exception.class)
	public ResponseDto<String> handleArgumentExeption(Exception e) {
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
	}
	
}
