package com.excilys.patricksbank.service.api;

import com.excilys.patricksbank.model.Utilisateur;


public interface UtilisateurService {

	Utilisateur getUtilisateurParLogin(String login);
	void update(Utilisateur u);
	Utilisateur getUtilisateurParId(String id);
}
