package com.excilys.patricksbank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.patricksbank.dao.api.OperationDao;
import com.excilys.patricksbank.model.Compte;
import com.excilys.patricksbank.model.Operation;
import com.excilys.patricksbank.service.api.OperationService;

@Service
public class OperationServiceImpl implements OperationService {

	@Autowired
	OperationDao operationDao;

	@Transactional(readOnly = true)
	public List<Operation> getOperationParCompteEtParDate(Compte c, int annee, int mois) {
		return operationDao.getOperationParCompteEtParDate(c, annee, mois);
	}

	@Transactional(readOnly = true)
	public double getTotalPaiementsCarte(Compte c, int annee, int mois) {
		double total = 0;
		for (Operation o : operationDao.getPaiementsCarte(c, annee, mois)) {
			total += o.getMontant();
		}
		return total;
	}

	@Transactional(readOnly = true)
	public List<Operation> getOperationCarte(Compte c, int annee, int mois) {
		return operationDao.getPaiementsCarte(c, annee, mois);
	}

	@Transactional
	public void save(Operation o) {
		operationDao.save(o);
	}
}
