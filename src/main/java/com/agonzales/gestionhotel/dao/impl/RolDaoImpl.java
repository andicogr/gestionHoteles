package com.agonzales.gestionhotel.dao.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.agonzales.gestionhotel.dao.RolDao;
import com.agonzales.gestionhotel.domain.Rol;
import com.agonzales.gestionhotel.dto.PaginacionDTO;
import com.agonzales.gestionhotel.util.DAO;
import com.agonzales.gestionhotel.util.Util;

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
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Rol> listarJson(PaginacionDTO paginacion, Map<String, Object> columnas){
		String sql = "FROM Rol a ";

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

		String sql="select count(a) from Rol a ";

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
