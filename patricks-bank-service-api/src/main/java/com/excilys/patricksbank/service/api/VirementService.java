package com.excilys.patricksbank.service.api;

import com.excilys.patricksbank.model.Compte;
import com.excilys.patricksbank.model.Virement;

public interface VirementService {

	boolean saveVirement(Compte compteSource, Compte compteCible, double montant);
	void save(Virement virement);
}
