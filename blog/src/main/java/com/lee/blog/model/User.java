package com.lee.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity // User 클래스가 MySQL에 테이블이 생성이 된다. 
// @DynamicInsert // insert시에 null인 필드 제외 시켜준다. 
public class User {

	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다. 
	private int id; // 시퀀스, auto_increment
	
	@Column(nullable = false, length = 30,unique = true)
	private String username; // 아이디
	
	@Column(nullable = false, length = 100) // 12345 => 해쉬 (비밀번호 암호화)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	// @ColumnDefault("'user'")
	// DB는 RoleType이라는게 없다. 
	@Enumerated(EnumType.STRING)
	private RoleType role; // Enum을 쓰는게 좋다. //ADMIN, USER, MANAGER
	
	@CreationTimestamp
	private Timestamp createDate;
}
