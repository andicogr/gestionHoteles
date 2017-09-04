package com.agonzales.gestionhotel.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agonzales.gestionhotel.dao.AjustesConfiguracionDao;
import com.agonzales.gestionhotel.domain.AjustesConfiguracion;
import com.agonzales.gestionhotel.service.AjustesConfiguracionService;
import com.agonzales.gestionhotel.service.UsuarioService;
import com.agonzales.gestionhotel.util.Constantes;
import com.agonzales.gestionhotel.util.Util;

@Service("AjustesConfiguracionService")
public class AjustesConfiguracionServiceImpl implements AjustesConfiguracionService{

	private static final Logger log = LoggerFactory.getLogger(AjustesConfiguracionServiceImpl.class);

	@Autowired
	private AjustesConfiguracionDao ajustesConfiguracionDao;

	@Autowired
	private UsuarioService usuarioService;
	
	public boolean isMultiCompaniaActivado(){
		AjustesConfiguracion ajusteConfig = ajustesConfiguracionDao.getAjustesDeConfiguracion();

		if(ajusteConfig != null){
			return ajusteConfig.isActivarMultiCompania();
		}
		return false;
	}
	
	public AjustesConfiguracion obtenerAjustesDeConfiguracion(){
		return ajustesConfiguracionDao.getAjustesDeConfiguracion();
	}
	
	@Transactional
	public Map<String, Object> guardar(AjustesConfiguracion ajustesConfig){
		Map<String, Object> retorno = new HashMap<String, Object>();
		Map<String, Object> notifiaccion = null;

		try {
			ajustesConfiguracionDao.guardar(ajustesConfig, usuarioService.getUID());
			notifiaccion = Util.crearNotificacionSuccess("Correcto", Constantes.MENSAJE_REGISTRO_CORRECTO);
		} catch (Exception e) {
			log.error("[AjustesConfiguracionServiceImpl] - method: guardar - error: " + e.getMessage());
			notifiaccion = Util.notificacionErrorDelSistema();
		}

		retorno.put("notificacion", notifiaccion);
		retorno.put("id", ajustesConfig.getId());
		retorno.put("estado", true);

		return retorno;
	}
	
	@Transactional
	public Map<String, Object> actualizar(AjustesConfiguracion ajustesConfig){
		Map<String, Object> retorno = new HashMap<String, Object>();
		Map<String, Object> notifiaccion = null;

		if(ajustesConfig.getId() != null){

			AjustesConfiguracion actual = ajustesConfiguracionDao.get(ajustesConfig.getId());
			actual.setActivarMultiCompania(ajustesConfig.isActivarMultiCompania());

			try {
				ajustesConfiguracionDao.guardar(ajustesConfig, usuarioService.getUID());
				notifiaccion = Util.crearNotificacionSuccess("Correcto", Constantes.MENSAJE_REGISTRO_CORRECTO);
			} catch (Exception e) {
				log.error("[AjustesConfiguracionServiceImpl] - method: actualizar - error: " + e.getMessage());
				notifiaccion = Util.notificacionErrorDelSistema();
			}

		}else{
			notifiaccion = Util.notificacionErrorDelSistema();
		}

		retorno.put("notificacion", notifiaccion);
		retorno.put("id", ajustesConfig.getId());
		retorno.put("estado", true);

		return retorno;
	}
}
