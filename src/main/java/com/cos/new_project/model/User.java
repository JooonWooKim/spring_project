package com.cos.new_project.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	
	@Column(nullable=false, length=30)
	private String username;	//아이디
	
	@Column(nullable=false, length=100)
	private String password;
	
	@Column(nullable=false, length=50)
	private String email;

//	@ColumnDefault("USER")
	//DB는 ROLETYPE이라는 것이 없다.
	@Enumerated(EnumType.STRING)
	private RoleType role;	//권한,user, admin, manager	//ADMIN, USER
	
	@CreationTimestamp	//시간이 자동 입력
	private Timestamp createDate;
}
