package com.blog.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.business.model.User;

public interface UsersRepository extends JpaRepository<User, Long> {
	
	public User findUserByFullName(String username);

}
