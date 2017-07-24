package com.agonzales.gestionhotel.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agonzales.gestionhotel.dao.UsuarioRolDao;
import com.agonzales.gestionhotel.domain.UsuarioRol;
import com.agonzales.gestionhotel.service.UsuarioRolService;
import com.agonzales.gestionhotel.service.UsuarioService;
import com.agonzales.gestionhotel.util.Constantes;
import com.agonzales.gestionhotel.util.Util;

@Service("UsuarioRolService")
public class UsuarioRolServiceImpl implements UsuarioRolService{
	
	@Autowired
	private UsuarioRolDao usuarioRolDao;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Transactional
	public Map<String, Object> guardar(UsuarioRol usuarioRol){
		Map<String, Object> retorno = new HashMap<String, Object>();
		Map<String, Object> notifiaccion = null;
		String textoNotificacion = Constantes.MENSAJE_REGISTRO_CORRECTO;

		if(usuarioRol.getId() != null){
			textoNotificacion = Constantes.MENSAJE_ACTUALIZACION_CORRECTA;
			
			UsuarioRol actual = usuarioRolDao.get(usuarioRol.getId());
			actual.setRol(usuarioRol.getRol());
			actual.setActivo(usuarioRol.isActivo());
			usuarioRol = actual;
		}

		notifiaccion = Util.crearNotificacionSuccess("Correcto", textoNotificacion);

		usuarioRolDao.guardar(usuarioRol, usuarioService.getUID());

		retorno.put("notificacion", notifiaccion);
		retorno.put("id", usuarioRol.getId());
		retorno.put("idUsuario", usuarioRol.getUsuarioId());
		retorno.put("estado", true);

		return retorno;
	}

	public UsuarioRol get(Integer id){
		return usuarioRolDao.get(id);
	}

	@Transactional
	public Map<String, Object> eliminar(Integer[] ids){
		Map<String, Object> retorno = new HashMap<String, Object>();
		Map<String, Object> notifiaccion = null;

		if(isUsuarioRolPrincipal(ids)){
			notifiaccion = Util.crearNotificacion("error", "Error", 
					"No se puede eliminar el rol principal del usuario principal del sistema.", 5000);
			retorno.put("notificacion", notifiaccion);
			retorno.put("estado", false);
			return retorno;
		}

		boolean estadoEliminacion = false;
		String textoNotificacion = Constantes.MENSAJE_REGISTRO_ELIMINADO;
		if(ids.length > 1){
			textoNotificacion = Constantes.MENSAJE_REGISTROS_ELIMINADOS;
		}
		for(Integer id : ids){
			UsuarioRol usuarioRol = usuarioRolDao.get(id);
			estadoEliminacion = usuarioRolDao.eliminar(usuarioRol);
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
	
	public boolean isUsuarioRolPrincipal(Integer[] ids){
		for(Integer id : ids){
			if(id == Constantes.SUPER_USUARIO_ROL_ID){
				return true;
			}
		}
		return false;
	}

}
