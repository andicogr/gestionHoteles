package com.agonzales.gestionhotel.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agonzales.gestionhotel.dao.AccesoCompaniaRolDao;
import com.agonzales.gestionhotel.domain.AccesoCompaniaRol;
import com.agonzales.gestionhotel.domain.Compania;
import com.agonzales.gestionhotel.service.AccesoCompaniaRolService;
import com.agonzales.gestionhotel.service.UsuarioService;
import com.agonzales.gestionhotel.util.Constantes;
import com.agonzales.gestionhotel.util.Util;

@Service("AccesoCompaniaRolService")
public class AccesoCompaniaRolServiceImpl implements AccesoCompaniaRolService{
	
	private static final Logger log = LoggerFactory.getLogger(AccesoCompaniaRolServiceImpl.class);
	
	@Autowired
	private AccesoCompaniaRolDao accesoCompaniaRolDao;
	
	@Autowired
	private UsuarioService usuarioService;

	public List<Compania> listaCompaniasActivasPorRolParaConfiguracionUsuario(Integer idRol){
		List<Compania> listaCompanias = accesoCompaniaRolDao.listaCompaniasActivasPorRol(idRol);
		return listaCompanias;
	}

	public List<Compania> listaCompaniasActivasPorRol(Integer idRol){
		return accesoCompaniaRolDao.listaCompaniasActivasPorRol(idRol); 
	}

	@Transactional
	public Map<String, Object> guardar(AccesoCompaniaRol accesoCompaniaRol){
		Map<String, Object> retorno = new HashMap<String, Object>();
		Map<String, Object> notifiaccion = null;

		try {
			accesoCompaniaRolDao.guardar(accesoCompaniaRol, usuarioService.getUID());
			notifiaccion = Util.crearNotificacionSuccess("Correcto", Constantes.MENSAJE_REGISTRO_CORRECTO);
		} catch (Exception e) {
			log.error("[AccesoCompaniaRolService] - method: guardar - error: " + e.getMessage());
			notifiaccion = Util.notificacionErrorDelSistema();
		}

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

			try {
				accesoCompaniaRolDao.guardar(actual, usuarioService.getUID());
				notifiaccion = Util.crearNotificacionSuccess("Correcto", Constantes.MENSAJE_ACTUALIZACION_CORRECTA);
			} catch (Exception e) {
				log.error("[AccesoCompaniaRolService] - method: actualizar - error: " + e.getMessage());
				notifiaccion = Util.notificacionErrorDelSistema();
			}
		}else{
			notifiaccion = Util.notificacionErrorDelSistema();
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
			
			try {
				estadoEliminacion = accesoCompaniaRolDao.eliminar(accesoCompaniaRol);
				if(estadoEliminacion){
					notifiaccion = Util.crearNotificacionInfo("Informacion", textoNotificacion);
				}else{
					notifiaccion = Util.notificacionErrorDelSistema();
				}
			} catch (Exception e) {
				log.error("[AccesoCompaniaRolService] - method: eliminar - error: " + e.getMessage());
				notifiaccion = Util.notificacionErrorDelSistema();
			}
		}

		retorno.put("notificacion", notifiaccion);
		retorno.put("estado", estadoEliminacion);
		return retorno;
	}

}
