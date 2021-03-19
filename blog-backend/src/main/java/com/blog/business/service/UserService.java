package com.blog.business.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.business.model.User;
import com.blog.business.repository.UsersRepository;

@Service
public class UserService {

	@Autowired
	private UsersRepository usersRepository;

	public List<User> getAllUsers() {
		List<User> userRecords = new ArrayList<>();
		usersRepository.findAll().forEach(userRecords::add);
		return userRecords;
	}

	public void addUser(User user) {
		usersRepository.save(user);
	}
}
