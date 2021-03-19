package com.blog.api.controller.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.blog.business.model.CustomUserDetails;
import com.blog.business.model.User;

public abstract class BaseController {
	
	Logger logger = LoggerFactory.getLogger(BaseController.class);

	
	public User getLoggedUser() {
		CustomUserDetails currentUser = null;
		try {
			currentUser =  (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}catch(ClassCastException e) {
			logger.info("no user logged!");
			throw new BadCredentialsException("no user logged!");
		}
		logger.info("current logged user name : {} ",currentUser.getUsername());
		return currentUser.getUser();
	}

}
