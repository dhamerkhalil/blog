package com.blog.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.business.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
