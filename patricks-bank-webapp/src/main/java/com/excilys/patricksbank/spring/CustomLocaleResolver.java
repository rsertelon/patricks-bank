package com.excilys.patricksbank.spring;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.AbstractLocaleResolver;

import com.excilys.patricksbank.model.Utilisateur;
import com.excilys.patricksbank.service.api.UtilisateurService;

@Component
public class CustomLocaleResolver extends AbstractLocaleResolver {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private UtilisateurService utilisateurService;

	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		Locale locale = getDefaultLocale();

		Utilisateur user = (Utilisateur) request.getSession().getAttribute("utilisateur");

		if (user != null) {
			locale = new Locale(user.getLocale());
		}

		else if (request.getParameter("locale") != null) {
			if ("fr".equals(request.getParameter("locale")))
				locale = new Locale("fr");
			else if ("en".equals(request.getParameter("locale")))
				locale = new Locale("en");
		}

		return locale;
	}

	@Override
	public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		Utilisateur user = (Utilisateur) request.getSession().getAttribute("utilisateur");
		if (user != null) {
			logger.info("Old Locale : {}", user.getLocale());
			user.setLocale(locale.toString());
			logger.info("New Locale : {}  - User locale : {}", locale, user.getLocale());
			utilisateurService.update(user);
		}
	}

}
