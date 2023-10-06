package com.example.versioncontrolservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public class UserDto {
	
	private Long userId;
	private String username;
	private String role;
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserDto(Long userId, String username, String role) {
		super();
		this.userId = userId;
		this.username = username;
		this.role = role;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	

}
