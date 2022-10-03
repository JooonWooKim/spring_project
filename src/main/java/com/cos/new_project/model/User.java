package com.cos.new_project.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder	//빌더 패턴
//@DynamicInsert	//null인 객체 제외 insert
@Entity	//User 클래스가 mysql 테이블에 생성이 된다.
public class User {
	
	@Id	//pk
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;	//시퀀스,auto_increment
	
	@Column(nullable=false, length=100, unique = true)
	@NotBlank(message = "아이디는 필수 입력 값입니다.")
	private String username;	//아이디
	
	@Column(nullable=false, length=100)
	@NotBlank(message = "비밀번호는 필수 입력 값입니다.")
	//@Pattern(regexp="[a-zA-Z1-9]{6,12}", message = "비밀번호는 영어와 숫자로 포함해서 6~12자리 이내로 입력해주세요.")
	private String password;
	
	@Column(nullable=false, length=50)
	@NotBlank(message = "이메일은 필수 입력 값입니다.")
	@Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
	@Email
	private String email;

//	@ColumnDefault("USER")
	//DB는 ROLETYPE이라는 것이 없다.
	@Enumerated(EnumType.STRING)
	private RoleType role;	//권한,user, admin, manager	//ADMIN, USER
	
	private String oauth;	//kakao, google
	
	@CreationTimestamp	//시간이 자동 입력
	private Timestamp createDate;
}
