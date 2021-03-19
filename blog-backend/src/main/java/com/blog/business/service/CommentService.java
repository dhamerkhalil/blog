package com.blog.business.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.business.model.Comment;
import com.blog.business.repository.CommentRepository;

@Service
public class CommentService {
	

	@Autowired
	private CommentRepository commentRepository;
	
	public Comment getCommentsById(Long commentId) {
		Comment comment = commentRepository.findById(commentId).get();
		return comment;
	}

	
	public List<Comment> getAllComments() {
		List<Comment> commentList = new ArrayList<>();
		commentRepository.findAll().forEach(commentList::add);
		return commentList;
	}

	public void addComment(Comment comment) {
		commentRepository.save(comment);
	}
	
	public void deleteComment(Comment comment) {
		commentRepository.delete(comment);
	}
	
	
}

