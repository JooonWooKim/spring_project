package com.cos.new_project.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestControllerTest {

	@GetMapping("/test/hello")
	public String hello() {
		return "<h1>hello</h1>";
	}
}
