package com.excilys.patricksbank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/admin")
@SessionAttributes("utilisateur")
public class AdminController {
	
	@RequestMapping(value = "/home.html")
	public ModelAndView handleHome() {
		return new ModelAndView("admin");
	}
}
