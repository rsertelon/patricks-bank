package com.excilys.patricksbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefaultController {

	@RequestMapping(value = "/login.html")
	public ModelAndView handleLogin() {
		return new ModelAndView("login");
	}

	@RequestMapping(value = "/")
	public ModelAndView handleDefault() {
		return new ModelAndView("login");
	}
}
