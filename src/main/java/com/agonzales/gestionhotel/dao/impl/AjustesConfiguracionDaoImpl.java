package com.agonzales.gestionhotel.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.agonzales.gestionhotel.dao.AjustesConfiguracionDao;
import com.agonzales.gestionhotel.domain.AjustesConfiguracion;
import com.agonzales.gestionhotel.util.DAO;

@Repository("AjustesConfiguracionDao")
public class AjustesConfiguracionDaoImpl extends DAO<AjustesConfiguracion> implements AjustesConfiguracionDao{
	
	public AjustesConfiguracion getAjustesDeConfiguracion(){
		String sql = "from AjustesConfiguracion order by id desc limit 1";
		
		Query q = em.createQuery(sql);
		
		@SuppressWarnings("unchecked")
		List<AjustesConfiguracion> listaAjustes = q.getResultList();
		if(!listaAjustes.isEmpty()){
			return listaAjustes.get(0);
		}
		return null;
	}

}
