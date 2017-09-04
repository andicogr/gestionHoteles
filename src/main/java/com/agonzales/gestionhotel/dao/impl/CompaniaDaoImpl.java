package com.agonzales.gestionhotel.dao.impl;


import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.agonzales.gestionhotel.dao.CompaniaDao;
import com.agonzales.gestionhotel.domain.Compania;
import com.agonzales.gestionhotel.dto.PaginacionDTO;
import com.agonzales.gestionhotel.util.Constantes;
import com.agonzales.gestionhotel.util.DAO;
import com.agonzales.gestionhotel.util.Util;
import com.agonzales.gestionhotel.util.VariablesSession;

@Repository("CompaniaDao")
public class CompaniaDaoImpl extends DAO<Compania> implements CompaniaDao{
	
	@SuppressWarnings("unchecked")
	public List<Compania> listarCompaniasActivas(){

		boolean multiCompania = (Boolean) VariablesSession.getAttribute(Constantes.MULTICOMPANIA_ACTIVADO);

		String sql = "FROM Compania ";
		
		if(!multiCompania){
			sql += " WHERE id = 2 ";
		}

		sql += " ORDER BY id ASC ";

		Query q = em.createQuery(sql);

		return q.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Compania> listarJson(PaginacionDTO paginacion, Map<String, Object> columnas){
		String sql = "FROM Compania a ";
		
		boolean multiCompania = (Boolean) VariablesSession.getAttribute(Constantes.MULTICOMPANIA_ACTIVADO);

		if(!multiCompania){
			sql += " WHERE a.id = 2";
		}

		sql += Util.OrderByPagination(columnas, paginacion.getiSortCol_0(), paginacion.getsSortDir_0());
		
		Query q = em.createQuery(sql);

		if(paginacion.getiDisplayLength()!=-1){
			q.setMaxResults(paginacion.getiDisplayLength());	
		}
		q.setFirstResult(paginacion.getiDisplayStart());	
	
		try{
			return q.getResultList();
		}
		catch(NoResultException e){
			return null;
		}
	}
	
	@Override
	public Number totalListaJson() {

		String sql="select count(a) from Compania a ";
		
		boolean multiCompania = (Boolean) VariablesSession.getAttribute(Constantes.MULTICOMPANIA_ACTIVADO);

		if(!multiCompania){
			sql += " WHERE a.id = 2";
		}

		/*List<Map<String, Object>> lista = new ArrayList<Map<String, Object>>();
		Map<String, Object> datos = new HashMap<String, Object>();
		if(!busqueda.getCodigo().equals("")){datos.put("1", "upper(a.codigo) like upper(:codigo)");}
		if(!busqueda.getNombres().equals("")){datos.put("2", "(upper(a.persona.nombres) like upper(:nombres) or upper(a.persona.apellidos) like upper(:nombres))");}
		lista.add(datos);
		
		sql += Util.query(lista);*/
		
		Query q = em.createQuery(sql);
		
		/*if(!busqueda.getCodigo().equals("")){q.setParameter("codigo","%"+busqueda.getCodigo()+"%");}
		if(!busqueda.getNombres().equals("")){q.setParameter("nombres","%"+busqueda.getNombres()+"%");}*/
			
		try{
			return (Number) q.getSingleResult();
		}
		catch(NoResultException e){
			return null;
		}
	}
}
