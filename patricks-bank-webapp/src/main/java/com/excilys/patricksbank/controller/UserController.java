package com.excilys.patricksbank.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.patricksbank.model.Utilisateur;
import com.excilys.patricksbank.service.api.CompteService;

@Controller
@RequestMapping(value = "/user/")
@SessionAttributes("utilisateur")
public class UserController {

	@Resource
	private CompteService compteService;
	
	@RequestMapping(value = "/home.html")
	public ModelAndView handleHome(@ModelAttribute("utilisateur") Utilisateur user, HttpServletRequest request) {
		Map<String,Object> mapModel = new HashMap<String, Object>(); 
		
		mapModel.put("listComptes", compteService.getComptesParUtilisateur(user));
		mapModel.put("here", "comptes");
		mapModel.put("messageConfirmation",	request.getAttribute("messageConfirmation"));
		
		return new ModelAndView("accueil", mapModel);
	}
}
