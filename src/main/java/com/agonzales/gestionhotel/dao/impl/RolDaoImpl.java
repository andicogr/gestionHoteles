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
	
	public boolean isPrivilegioAsociadoARol(Integer idPrivilegio, Integer idRol){
		String sql = "select exists (select * from rol_privilegio where privilegio_id =:idPrivilegio and rol_id =:idRol)";
		
		Query q = em.createNativeQuery(sql);
		
		q.setParameter("idPrivilegio", idPrivilegio);
		q.setParameter("idRol", idRol);
		
		Boolean existe = (Boolean) q.getSingleResult();
		
		return existe;
	}

}
