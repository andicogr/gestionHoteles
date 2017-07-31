package com.agonzales.gestionhotel.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.agonzales.gestionhotel.dao.AccesoCompaniaRolDao;
import com.agonzales.gestionhotel.domain.AccesoCompaniaRol;
import com.agonzales.gestionhotel.util.DAO;

@Repository("AccesoCompaniaRolDao")
public class AccesoCompaniaRolDaoImpl extends DAO<AccesoCompaniaRol> implements AccesoCompaniaRolDao{

	@SuppressWarnings("unchecked")
	public List<AccesoCompaniaRol> listaDeAccesoCompaniaRolActivasPorRol(Integer idRol){
		String sql = "from AccesoCompaniaRol where rol.id = :idRol and activo = true";
		
		Query q = em.createQuery(sql);
		
		q.setParameter("idRol", idRol);
		
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<AccesoCompaniaRol> obtenerAccesoCompaniaRolPorRol(Integer idRol){
		String sql = "from AccesoCompaniaRol where rol.id = :idRol";
		
		Query q = em.createQuery(sql);
		
		q.setParameter("idRol", idRol);
		
		return q.getResultList();
	}

}
