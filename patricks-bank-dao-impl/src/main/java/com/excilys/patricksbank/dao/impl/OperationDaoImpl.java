package com.excilys.patricksbank.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.patricksbank.dao.api.OperationDao;
import com.excilys.patricksbank.model.Compte;
import com.excilys.patricksbank.model.Operation;
import com.excilys.patricksbank.model.Operation.TypeOperation;

@Repository
public class OperationDaoImpl implements OperationDao {

	@Resource
	private HibernateTemplate hibernateTemplate;

	@SuppressWarnings("unchecked")
	public List<Operation> getOperationParCompte(Compte c) {
		return hibernateTemplate.find(
				"from OperationAbstract o where o.compte = ?", c);
	}

	@SuppressWarnings("unchecked")
	public List<Operation> getOperationParCompteEtParDate(Compte c, int annee,	int mois) {
		DetachedCriteria criteria = getCriteriaPourCompteAnneeMois(c, annee, mois)
				.add(Restrictions.ne("typeOperation", TypeOperation.PAIEMENT_CARTE));
		
		return (List<Operation>) hibernateTemplate.findByCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	public List<Operation> getPaiementsCarte(Compte c, int annee, int mois) {

		DetachedCriteria criteria = getCriteriaPourCompteAnneeMois(c, annee, mois)
				.add(Restrictions.eq("typeOperation", TypeOperation.PAIEMENT_CARTE));

		return (List<Operation>) hibernateTemplate.findByCriteria(criteria);

	}
	
	private DetachedCriteria getCriteriaPourCompteAnneeMois(Compte c, int annee, int mois){
		DateTime dateDeb = new DateTime(annee + "-" + (mois + 1) + "-01");
		DateTime dateFin;
		if (mois == 11) {
			dateFin = new DateTime(annee + 1 + "-01-01");
		} else {
			dateFin = new DateTime(annee + "-" + (mois + 2) + "-01");
		}

		DetachedCriteria criteria = DetachedCriteria.forClass(Operation.class);

		return criteria.add(Restrictions.between("date", dateDeb, dateFin))
				.add(Restrictions.eq("compte", c))
				.addOrder(Order.asc("date"));
	}

	@Override
	public void save(Operation o) {
		hibernateTemplate.save(o);
	}
}
