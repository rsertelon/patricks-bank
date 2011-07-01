package com.excilys.patricksbank.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.patricksbank.dao.api.CompteDao;
import com.excilys.patricksbank.model.Compte;
import com.excilys.patricksbank.model.Utilisateur;

@Repository
public class CompteDaoImpl implements CompteDao {

	@Resource
	private HibernateTemplate hibernateTemplate;

	@Override
	@SuppressWarnings("unchecked")
	public List<Compte> getComptesParUtilisateur(Utilisateur u) {

		return hibernateTemplate.find("select c from Compte c join c.proprietaires p where p.id=?",
				u.getId());
	}
	
	@Override
	public Compte getCompteParIdAvecOperations(String id) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Compte.class);
		criteria.add(Restrictions.eq("idCompte", id));
		criteria.setFetchMode("operations", FetchMode.JOIN);
		return (Compte) hibernateTemplate.findByCriteria(criteria).get(0);	
	}

	@Override
	public void update(Compte c) {
		hibernateTemplate.update(c);
	}
}
