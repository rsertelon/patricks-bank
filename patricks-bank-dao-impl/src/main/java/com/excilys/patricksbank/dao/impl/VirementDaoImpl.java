package com.excilys.patricksbank.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.patricksbank.dao.api.VirementDao;
import com.excilys.patricksbank.model.Virement;

@Repository
public class VirementDaoImpl implements VirementDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public void save(Virement virement) {
		hibernateTemplate.save(virement);
	}
}
