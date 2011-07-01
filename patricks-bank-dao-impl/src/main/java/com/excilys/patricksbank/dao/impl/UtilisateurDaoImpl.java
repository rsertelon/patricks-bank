package com.excilys.patricksbank.dao.impl;

import javax.annotation.Resource;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.patricksbank.dao.api.UtilisateurDao;
import com.excilys.patricksbank.model.Utilisateur;

@Repository
public class UtilisateurDaoImpl implements UtilisateurDao {

	@Resource
	private HibernateTemplate hibernateTemplate;

	@Override
	public Utilisateur getUtilisateurParLogin(String login) {

		return (Utilisateur) hibernateTemplate.find(
				"select u from Utilisateur u where u.login=?", login).get(0);
	}

	@Override
	public void update(Utilisateur u) {
		hibernateTemplate.update(u);
	}

	@Override
	public Utilisateur getUtilisateurParId(String id) {
		return (Utilisateur) hibernateTemplate.get(Utilisateur.class, id);	
	}
	
}
