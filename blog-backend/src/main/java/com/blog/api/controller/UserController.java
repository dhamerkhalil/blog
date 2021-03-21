package com.blog.api.controller;

import java.text.ParseException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blog.api.controller.base.BaseController;
import com.blog.api.dto.StatusDto;
import com.blog.business.model.Status;
import com.blog.business.model.User;
import com.blog.business.service.UserService;

@RestController
public class UserController  extends BaseController {
	
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;

	@RequestMapping("/users")
	public List<User> getUserById() {
		return userService.getAllUsers();
	}
	
	@RequestMapping("/current-logged-user")
	public User getLoggedUser() {
		return super.getLoggedUser();
	}

	@RequestMapping(value = "/add-user", method = RequestMethod.POST)
	public void addUser(@RequestBody User userRecord) {
		userService.addUser(userRecord);
	}
	
	private Status convertToEntity(StatusDto statusDto) throws ParseException {
		Status status = modelMapper.map(statusDto, Status.class);
	    return status;
	}
	
	private StatusDto convertToDto(Status status)  throws ParseException  {
		StatusDto statusDto = modelMapper.map(status, StatusDto.class);	   
	    return statusDto;
	}
}
