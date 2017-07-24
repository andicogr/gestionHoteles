package com.agonzales.gestionhotel.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.agonzales.gestionhotel.dao.UsuarioRolDao;
import com.agonzales.gestionhotel.domain.UsuarioRol;
import com.agonzales.gestionhotel.util.DAO;

@Repository("UsuarioRolDao")
public class UsuarioRolDaoImpl extends DAO<UsuarioRol> implements UsuarioRolDao{
	
	public List<UsuarioRol> obtenerUsuarioRolesPorUsuario(Integer idUsuario){
		String sql = "from UsuarioRol where usuario.id = :idUsuario";

		Query q = em.createQuery(sql);
		q.setParameter("idUsuario", idUsuario);
		
		@SuppressWarnings("unchecked")
		List<UsuarioRol> listarRolesActivos =  q.getResultList();
		return listarRolesActivos;
	}

}
