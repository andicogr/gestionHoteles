package com.agonzales.gestionhotel.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.agonzales.gestionhotel.dao.RolDao;
import com.agonzales.gestionhotel.domain.Rol;
import com.agonzales.gestionhotel.util.DAO;

@Repository("RolDao")
public class RolDaoImpl extends DAO<Rol> implements RolDao{
	
	@SuppressWarnings("unchecked")
	public List<Rol> listarRolesActivos(){
		String sql = "from Rol where activo = true";
		
		Query q = em.createQuery(sql);

		return q.getResultList();
	}

}
