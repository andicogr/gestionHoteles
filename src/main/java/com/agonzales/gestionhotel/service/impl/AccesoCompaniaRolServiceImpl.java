package com.agonzales.gestionhotel.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agonzales.gestionhotel.dao.AccesoCompaniaRolDao;
import com.agonzales.gestionhotel.domain.AccesoCompaniaRol;
import com.agonzales.gestionhotel.service.AccesoCompaniaRolService;
import com.agonzales.gestionhotel.service.UsuarioService;
import com.agonzales.gestionhotel.util.Constantes;
import com.agonzales.gestionhotel.util.Util;

@Service("AccesoCompaniaRolService")
public class AccesoCompaniaRolServiceImpl implements AccesoCompaniaRolService{
	
	@Autowired
	private AccesoCompaniaRolDao accesoCompaniaRolDao;
	
	@Autowired
	private UsuarioService usuarioService;


	public List<AccesoCompaniaRol> listaDeAccesoCompaniaRolActivasPorRol(Integer idRol){
		return accesoCompaniaRolDao.listaDeAccesoCompaniaRolActivasPorRol(idRol); 
	}

	@Transactional
	public Map<String, Object> guardar(AccesoCompaniaRol accesoCompaniaRol){
		Map<String, Object> retorno = new HashMap<String, Object>();
		Map<String, Object> notifiaccion = null;

		notifiaccion = Util.crearNotificacionSuccess("Correcto", Constantes.MENSAJE_REGISTRO_CORRECTO);

		accesoCompaniaRolDao.guardar(accesoCompaniaRol, usuarioService.getUID());

		retorno.put("notificacion", notifiaccion);
		retorno.put("id", accesoCompaniaRol.getId());
		retorno.put("idRol", accesoCompaniaRol.getRolId());
		retorno.put("estado", true);

		return retorno;
	}
	
	@Transactional
	public Map<String, Object> actualizar(AccesoCompaniaRol accesoCompaniaRol){
		Map<String, Object> retorno = new HashMap<String, Object>();
		Map<String, Object> notifiaccion = null;
		if(accesoCompaniaRol.getId() != null){

			AccesoCompaniaRol actual = accesoCompaniaRolDao.get(accesoCompaniaRol.getId());
			actual.setCompania(accesoCompaniaRol.getCompania());
			actual.setActivo(accesoCompaniaRol.isActivo());

			accesoCompaniaRolDao.guardar(actual, usuarioService.getUID());
			
			notifiaccion = Util.crearNotificacionSuccess("Correcto", Constantes.MENSAJE_ACTUALIZACION_CORRECTA);
		}else{
			notifiaccion = Util.crearNotificacionError("Error", Constantes.MENSAJE_ERROR_GUARDAR);
		}

		retorno.put("notificacion", notifiaccion);
		retorno.put("id", accesoCompaniaRol.getId());
		retorno.put("idRol", accesoCompaniaRol.getRolId());
		retorno.put("estado", true);

		return retorno;
	}

	public AccesoCompaniaRol get(Integer id){
		return accesoCompaniaRolDao.get(id);
	}

	@Transactional
	public Map<String, Object> eliminar(Integer[] ids){
		Map<String, Object> retorno = new HashMap<String, Object>();
		Map<String, Object> notifiaccion = null;

		boolean estadoEliminacion = false;
		String textoNotificacion = Constantes.MENSAJE_REGISTRO_ELIMINADO;
		if(ids.length > 1){
			textoNotificacion = Constantes.MENSAJE_REGISTROS_ELIMINADOS;
		}

		for(Integer id : ids){
			AccesoCompaniaRol accesoCompaniaRol = accesoCompaniaRolDao.get(id);
			estadoEliminacion = accesoCompaniaRolDao.eliminar(accesoCompaniaRol);
		}
		if(estadoEliminacion){
			notifiaccion = Util.crearNotificacionInfo("Informacion", textoNotificacion);
		}else{
			notifiaccion = Util.crearNotificacion("error", "Error", 
					"Ocurrio un error mientras se eliminaba el registro, "
					+ "por favor comuniquese con el administrador del sistema.", 5000);
		}

		retorno.put("notificacion", notifiaccion);
		retorno.put("estado", estadoEliminacion);
		return retorno;
	}

}
