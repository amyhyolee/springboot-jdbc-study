package com.lee.blog.test;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Getter
// @Setter
@Data
//@AllArgsConstructor // 생성자
@NoArgsConstructor
// @RequiredArgsConstructor // final(불변) 붙은 것만 생성자 생성
public class Member {
	private int id;
	private String username;
	private String password;
	private String email;
	
	@Builder
	// Source -> Generate Constructor using Fields
	public Member(int id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
//	
//	// Source -> Generate Getters and Setters
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	public String getUsername() {
//		return username;
//	}
//	public void setUsername(String username) {
//		this.username = username;
//	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
}
