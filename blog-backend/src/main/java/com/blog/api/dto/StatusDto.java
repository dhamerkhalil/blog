package com.blog.api.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
public class StatusDto {
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	private Long id;

	private String text;

	private String date;

	private UserDto statusUser;

	private List<CommentDto> statusComments;

	public Date getSubmissionDateConverted(String timezone) throws ParseException {
		dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
		return dateFormat.parse(this.date);
	}

	public void setSubmissionDate(Date date, String timezone) {
		dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
		this.date = dateFormat.format(date);
	}


	public static SimpleDateFormat getDateformat() {
		return dateFormat;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public UserDto getStatusUser() {
		return statusUser;
	}

	public void setStatusUser(UserDto statusUser) {
		this.statusUser = statusUser;
	}

	public List<CommentDto> getStatusComments() {
		return statusComments;
	}

	public void setStatusComments(List<CommentDto> statusComments) {
		this.statusComments = statusComments;
	}


}