package com.excilys.patricksbank.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.patricksbank.dao.api.CompteDao;
import com.excilys.patricksbank.model.Compte;
import com.excilys.patricksbank.model.Utilisateur;
import com.excilys.patricksbank.service.api.CompteService;

@Transactional
@Service
public class CompteServiceImpl implements CompteService {

	@Resource
	private CompteDao compteDao;

	@Transactional(readOnly = true)
	public List<Compte> getComptesParUtilisateur(Utilisateur u) {
		return compteDao.getComptesParUtilisateur(u);
	}

	@Override
	public Compte getAndVerifyCompteDansListeCompte(List<Compte> listComptes, String idCompte) {
		for (Compte c : listComptes) {
			if (c.getIdCompte().equals(idCompte)) {
				return c;
			}
		}
		return null;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Compte getCompteParId(String id) {
		return compteDao.getCompteParIdAvecOperations(id);
	}
	
	@Override
	@Transactional
	public void update(Compte c) {
		compteDao.update(c);
	}
}
