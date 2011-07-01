package com.excilys.patricksbank.service.api;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.excilys.patricksbank.model.Compte;
import com.excilys.patricksbank.model.Operation;

@Transactional
public interface OperationService {

	List<Operation> getOperationParCompteEtParDate(Compte c, int annee, int mois);

	List<Operation> getOperationCarte(Compte c, int annee, int mois);

	double getTotalPaiementsCarte(Compte c, int annee, int mois);

	void save(Operation o);
}
