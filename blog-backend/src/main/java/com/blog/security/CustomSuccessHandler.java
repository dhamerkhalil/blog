package com.blog.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class CustomSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private static final Logger LOG = LoggerFactory.getLogger(CustomSuccessHandler.class);


	private ObjectMapper objectMapper = new ObjectMapper();

    @Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
    	LOG.info("Logged in successfully");
    	response.setStatus(HttpStatus.OK.value());
	      Map<String, Object> data = new HashMap<>();
	      data.put(
	        "timestamp", 
	        Calendar.getInstance().getTime());
	      data.put(
	        "message", 
	        "Logged in successfully");
	      response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
	      response.setHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
	      response.setHeader("Allow-Origin-With-Credentials","true");
	      response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
	      response.setHeader("Access-Control-Allow-Credentials", "true");

	      response.getOutputStream()
	        .println(objectMapper.writeValueAsString(data));
	        }

}