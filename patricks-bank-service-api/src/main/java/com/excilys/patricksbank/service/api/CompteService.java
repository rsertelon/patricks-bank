package com.excilys.patricksbank.service.api;

import java.util.List;

import com.excilys.patricksbank.model.Compte;
import com.excilys.patricksbank.model.Utilisateur;

public interface CompteService {
	 List<Compte> getComptesParUtilisateur(Utilisateur u);
	 Compte getAndVerifyCompteDansListeCompte(List<Compte> listComptes, String idCompte);
	 Compte getCompteParId(String id);
	 void update(Compte c);
}
