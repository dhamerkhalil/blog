package com.blog.api.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blog.api.controller.base.BaseController;
import com.blog.api.dto.CommentDto;
import com.blog.api.dto.StatusDto;
import com.blog.api.dto.UserDto;
import com.blog.business.model.Comment;
import com.blog.business.model.Status;
import com.blog.business.model.User;
import com.blog.business.service.StatusService;

@RestController
public class StatusController extends BaseController {

	Logger logger = LoggerFactory.getLogger(StatusController.class);
	
	@Autowired
	private StatusService statusService;
	
	@Autowired
	private ModelMapper modelMapper;

	@RequestMapping("/status")
	public List<StatusDto> getAllStatus() {
		 List<Status> status = statusService.getAllStatus();
		 return status.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@RequestMapping(value = "/add-status", method = RequestMethod.POST)
	public void addUser(@RequestBody Status status) {
		status.setStatusUser(getLoggedUser());
		statusService.addStatus(status);
	}
	
	@RequestMapping(value = "/delete-status/{statusId}", method = RequestMethod.DELETE)
	public void deleteStatus(@PathVariable("statusId") Long statusId) {
		Status status = statusService.getStatusById(statusId);
		logger.info("status text to be deleted : {}", status.getText());
		if(!status.getStatusUser().getId().equals(getLoggedUser().getId())) {
			throw new BadCredentialsException("cannot delete other users status");
		}
		statusService.deleteStatus(status);			
	}
	
	private Status convertToEntity(StatusDto statusDto) throws ParseException {
		Status status = modelMapper.map(statusDto, Status.class);
	    return status;
	}
	
	private StatusDto convertToDto(Status status) {
		StatusDto statusDto = modelMapper.map(status, StatusDto.class);	 
		
		User statusUser = status.getStatusUser();
		UserDto statusUserDto = new UserDto();
		
		statusUserDto.setId(status.getStatusUser().getId());
		statusUserDto.setFullName(statusUser.getFullName());
		
		 Set<Comment> statusComments = status.getStatusComments();
		 List<CommentDto> statusCommentsDto = new ArrayList<CommentDto>();
		 
		for (Comment comment : statusComments) {
			
			UserDto commentUserDto = new UserDto();
			CommentDto commentDto = new CommentDto();
			
			commentDto.setDate(comment.getDate().toString());
			commentDto.setId(comment.getId());
			commentDto.setText(comment.getText());
			
			User commentUser = comment.getCommentUser();
			commentUserDto.setId(commentUser.getId());
			commentUserDto.setFullName(commentUser.getFullName());
			
			commentDto.setUser(commentUserDto);
			statusCommentsDto.add(commentDto);
		}
		statusDto.setStatusUser(statusUserDto);
		statusDto.setStatusComments(statusCommentsDto);
	    return statusDto;
	}
	
}
