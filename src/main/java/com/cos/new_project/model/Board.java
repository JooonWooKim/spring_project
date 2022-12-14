
package com.cos.new_project.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder	//빌더 패턴
@Entity	//Board 클래스가 mysql 테이블에 생성이 된다.
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob	//대용량 데이터
	private String content;
	
	private int count;	
	
	@CreationTimestamp
	private Timestamp createDate;
	
	public String getCreateDate() {
		return new SimpleDateFormat("yyyy.MM.dd").format(createDate);
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userId")
	private User user;	//DB는 오브젝트를 저장할 수 없다. fk, 자바는 오브젝트를 사용할 수 있다.
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({"board"})
	@OrderBy("id desc")
	private List<Reply> replys;
	
	@OneToMany(mappedBy = "board", cascade= CascadeType.REMOVE)
	private List<Recommend> recommends;
	
	@Transient
	//@Transient를 사용해서, 데이터베이스에는 해당 칼럼이 생성하지 않게끔 방지
	//default = false
	private boolean recommend_state;
	//추천 상태 
	
	@Transient
	private int recommend_count;
	//추천 개수 
	
}
