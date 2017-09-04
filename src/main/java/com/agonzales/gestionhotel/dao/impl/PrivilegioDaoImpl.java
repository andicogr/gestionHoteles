package com.agonzales.gestionhotel.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.agonzales.gestionhotel.dao.PrivilegioDao;
import com.agonzales.gestionhotel.domain.Privilegio;
import com.agonzales.gestionhotel.util.DAO;

@Repository("PrivilegioDao")
public class PrivilegioDaoImpl extends DAO<Privilegio> implements PrivilegioDao{
	
	@SuppressWarnings("unchecked")
	public List<Privilegio> obtenerListaDePrivilegiosPadresActivos(){
		String sql = "from Privilegio where privilegioPadre is null and activo = true order by orden asc";

		Query q = em.createQuery(sql);
		
		return q.getResultList();
	}

}
