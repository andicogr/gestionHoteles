package com.agonzales.gestionhotel.dao.impl;


import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.agonzales.gestionhotel.dao.CompaniaDao;
import com.agonzales.gestionhotel.domain.Compania;
import com.agonzales.gestionhotel.util.DAO;

@Repository("CompaniaDao")
public class CompaniaDaoImpl extends DAO<Compania> implements CompaniaDao{
	
	@SuppressWarnings("unchecked")
	public List<Compania> listarCompaniasActivas(){
		String sql = "from Compania";
		
		Query q = em.createQuery(sql);

		return q.getResultList();
	}

}
