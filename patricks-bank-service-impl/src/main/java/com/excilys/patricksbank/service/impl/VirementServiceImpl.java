package com.excilys.patricksbank.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.patricksbank.dao.api.OperationDao;
import com.excilys.patricksbank.dao.api.VirementDao;
import com.excilys.patricksbank.model.Compte;
import com.excilys.patricksbank.model.Operation;
import com.excilys.patricksbank.model.Operation.TypeOperation;
import com.excilys.patricksbank.model.Virement;
import com.excilys.patricksbank.service.api.VirementService;

@Service
public class VirementServiceImpl implements VirementService {

	@Autowired
	private OperationDao operationDao;
	
	@Autowired
	private VirementDao virementDao;
	
	@Transactional
	public boolean saveVirement(Compte compteSource, Compte compteCible, double montant) {
		
		compteSource.setMontant(compteSource.getMontant() - montant);
		compteCible.setMontant(compteCible.getMontant() + montant);
		
		Operation operationSource = new Operation(compteSource, compteCible, montant, TypeOperation.VIREMENT_EMIS);
		Operation operationCible = new Operation(compteSource, compteCible, montant, TypeOperation.VIREMENT_RECU);
		
		Virement virement = new Virement(compteSource, compteCible, montant);
		
		operationDao.save(operationCible);
		operationDao.save(operationSource);
		
		virementDao.save(virement);
		
		return true;
	}
	
	@Transactional
	public void save(Virement v) {
		virementDao.save(v);
	}

}
