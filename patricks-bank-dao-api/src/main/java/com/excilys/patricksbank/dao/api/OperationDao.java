package com.excilys.patricksbank.dao.api;

import java.util.List;

import com.excilys.patricksbank.model.Compte;
import com.excilys.patricksbank.model.Operation;

public interface OperationDao {
	List<Operation> getOperationParCompte(Compte c);
	List<Operation> getOperationParCompteEtParDate(Compte c, int annee, int mois);
	List<Operation> getPaiementsCarte(Compte c, int annee, int mois);
	void save(Operation o);
}
