package com.excilys.patricksbank.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.patricksbank.dao.api.UtilisateurDao;
import com.excilys.patricksbank.model.Utilisateur;
import com.excilys.patricksbank.service.api.UtilisateurService;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

	@Resource
	private UtilisateurDao utilisateurDao;

	@Transactional(readOnly = true)
	public Utilisateur getUtilisateurParLogin(String login) {
		return utilisateurDao.getUtilisateurParLogin(login);
	}

	@Override
	@Transactional
	public void update(Utilisateur u) {
		utilisateurDao.update(u);
	}

	@Override
	@Transactional(readOnly = true)
	public Utilisateur getUtilisateurParId(String id) {
		return utilisateurDao.getUtilisateurParId(id);
	}
}
