package com.blog.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.api.controller.base.BaseController;

@Controller
public class loginController extends BaseController  {

	@RequestMapping("/")
	public String root() {
		return "blog/index.html";
	}

	@RequestMapping("/login")
	public String login() {
		return "blog/index.html";
	}

}
