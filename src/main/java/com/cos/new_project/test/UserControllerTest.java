package com.cos.new_project.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

public class UserControllerTest {
	
	
	
	@DisplayName("사용자 추가 실패-이메일 형식이 아")
	@Test
	void addUserFail_NotEmailFormat() throws Exception{
		final User user = USer.builder()
				.username(null)
				.password(null)
				;
		
						
	}
}
