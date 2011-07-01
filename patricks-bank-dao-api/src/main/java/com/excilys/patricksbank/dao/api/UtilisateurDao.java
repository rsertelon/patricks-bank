package com.excilys.patricksbank.dao.api;

import com.excilys.patricksbank.model.Utilisateur;

public interface UtilisateurDao {
	 Utilisateur getUtilisateurParLogin(String login);
	 Utilisateur getUtilisateurParId(String id);
	 void update(Utilisateur u);
}
