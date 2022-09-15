package com.cos.new_project.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.new_project.model.User;

public class principalDetail implements UserDetails{
	private User user;	//컴포지션, 상속과 다른 개념

	public principalDetail(User user) {
		this.user = user;
	}
	
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	//계정이 만료되지 않았는지 리턴한다.(true: 만료 안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//계정이 잠겼는지 안잠겼는지 리턴한다.(true: 안잠긴것)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	//비밀번호가 만료되었는지 확인(true: 만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//계정 활성화가 되었는지 return(true: 활성화)
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	//계정이 갖고있는 권한목록을 return
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		collectors.add(()-> {return "ROLE_"+user.getRole();});
		
		return collectors;
	}
}
