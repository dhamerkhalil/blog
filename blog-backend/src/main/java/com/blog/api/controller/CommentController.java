package com.blog.api.controller;

import java.text.ParseException;
import java.util.List;

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
import com.blog.api.dto.StatusDto;
import com.blog.business.model.Comment;
import com.blog.business.model.Status;
import com.blog.business.service.CommentService;
import com.blog.business.service.StatusService;

@RestController
public class CommentController extends BaseController {

	Logger logger = LoggerFactory.getLogger(StatusController.class);

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private StatusService statusService;

	@RequestMapping("/comment")
	public List<Comment> getAllComment() {
		return commentService.getAllComments();
	}

	@RequestMapping(value = "/add-comment/{statusId}", method = RequestMethod.POST)
	public void addUser(@RequestBody Comment comment, @PathVariable(name = "statusId") Long statusId) {
		Status status = statusService.getStatusById(statusId);
		comment.setCommentStatus(status);
		comment.setCommentUser(getLoggedUser());
		commentService.addComment(comment);
	}
	
	@RequestMapping(value = "/delete-comment/{commentId}", method = RequestMethod.DELETE)
	public void deleteComment(@PathVariable("commentId") Long commentId) {
		  Comment comment = commentService.getCommentsById(commentId);
		if(!comment.getCommentUser().getId().equals(getLoggedUser().getId())) {
			throw new BadCredentialsException("cannot delete other users comments");
		}
		commentService.deleteComment(comment);
	}
	
	private Status convertToEntity(StatusDto statusDto) throws ParseException {
		Status status = modelMapper.map(statusDto, Status.class);
	    return status;
	}
	
	private StatusDto convertToDto(Status status) throws ParseException  {
		StatusDto statusDto = modelMapper.map(status, StatusDto.class);	   
	    return statusDto;
	}
	
}
