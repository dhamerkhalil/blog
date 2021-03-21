package com.blog.api.dto;

import java.text.SimpleDateFormat;
import java.util.List;

public class UserDto {
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	private Long id;

	private String fullName;

	private String email;
	
	private String password;

	private List<StatusDto> status;

	private List<CommentDto> comments;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static SimpleDateFormat getDateformat() {
		return dateFormat;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<StatusDto> getStatus() {
		return status;
	}

	public void setStatus(List<StatusDto> status) {
		this.status = status;
	}

	public List<CommentDto> getComments() {
		return comments;
	}

	public void setComments(List<CommentDto> comments) {
		this.comments = comments;
	}



}
