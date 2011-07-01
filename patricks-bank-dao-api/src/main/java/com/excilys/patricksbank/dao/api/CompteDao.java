package com.excilys.patricksbank.dao.api;

import java.util.List;

import com.excilys.patricksbank.model.Compte;
import com.excilys.patricksbank.model.Utilisateur;

public interface CompteDao {
	 List<Compte> getComptesParUtilisateur(Utilisateur u);
	 Compte getCompteParIdAvecOperations(String id);
	 void update(Compte c);
}
