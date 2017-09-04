package com.agonzales.gestionhotel.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agonzales.gestionhotel.dao.AccesoCompaniaRolDao;
import com.agonzales.gestionhotel.dao.RolDao;
import com.agonzales.gestionhotel.domain.AccesoCompaniaRol;
import com.agonzales.gestionhotel.domain.Privilegio;
import com.agonzales.gestionhotel.domain.Rol;
import com.agonzales.gestionhotel.dto.PaginacionDTO;
import com.agonzales.gestionhotel.service.RolService;
import com.agonzales.gestionhotel.service.UsuarioRolService;
import com.agonzales.gestionhotel.service.UsuarioService;
import com.agonzales.gestionhotel.util.Constantes;
import com.agonzales.gestionhotel.util.Util;

@Service("RolService")
public class RolServiceImpl implements RolService{
	
	private static final Logger log = LoggerFactory.getLogger(RolServiceImpl.class);
	
	@Autowired
	private RolDao rolDao;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private AccesoCompaniaRolDao accesoCompaniaRolDao;
	
	@Autowired
	private UsuarioRolService usuarioRolService;
	
	public Map<String, Object> listarJson(PaginacionDTO paginacion){

		if(paginacion.getiDisplayLength()==null){
			return null;
		}

		Map<String, Object> columnas = new HashMap<String, Object>();
		
		columnas.put("1", "a.nombre");

		List<Rol> listaJson = rolDao.listarJson(paginacion, columnas);
		Number total = rolDao.totalListaJson();

		Map<String, Object> datos = new HashMap<String, Object>();

		datos.put("sEcho", paginacion.getsEcho());
		datos.put("iTotalRecords", total);
		datos.put("iTotalDisplayRecords", total);

		List<String[]> listas = new ArrayList<String[]>();
		
		for (Rol rol: listaJson) {
			
			String checkbox ="<input type=\"checkbox\" name=\"checkBoxRow\" class=\"flat\" value=\"" + rol.getId() + "\"/>";

			String[] aaDato = {
						checkbox,
						rol.getNombre(),
						Util.obtenerNombreEstado(rol.isActivo())
					};
			listas.add(aaDato);
		}

		datos.put("aaData", listas);
		
		return datos;
	}

	@Transactional
	public Map<String, Object> guardar(Rol rol){
		Map<String, Object> retorno = new HashMap<String, Object>();
		Map<String, Object> notifiaccion = null;

		if(rolDao.isUniqueValue("nombre", rol.getNombre(), rol.getId())){
			notifiaccion = Util.crearNotificacionError("Error", "El nombre del rol ya esta registrada en el sistema.");
			retorno.put("notificacion", notifiaccion);
			retorno.put("estado", false);
			return retorno;
		}

		try {
			rolDao.guardar(rol, usuarioService.getUID());
			notifiaccion = Util.crearNotificacionSuccess("Correcto", Constantes.MENSAJE_REGISTRO_CORRECTO);
		} catch (Exception e) {
			log.error("[RolServiceImpl] - method: guardar - error: " + e.getMessage());
			notifiaccion = Util.notificacionErrorDelSistema();
		}
		

		retorno.put("notificacion", notifiaccion);
		retorno.put("id", rol.getId());
		retorno.put("estado", true);

		return retorno;
	}
	
	@Transactional
	public Map<String, Object> actualizar(Rol rol){
		Map<String, Object> retorno = new HashMap<String, Object>();
		Map<String, Object> notifiaccion = null;

		if(rolDao.isUniqueValue("nombre", rol.getNombre(), rol.getId())){
			notifiaccion = Util.crearNotificacionError("Error", "El nombre del rol ya esta registrada en el sistema.");
			retorno.put("notificacion", notifiaccion);
			retorno.put("estado", false);
			return retorno;
		}

		if(rol.getId() != null){

			Rol actual = rolDao.get(rol.getId());
			actual.setNombre(rol.getNombre());
			actual.setDescripcion(rol.getDescripcion());
			actual.setActivo(rol.isActivo());
			
			try {
				rolDao.guardar(actual, usuarioService.getUID());
				notifiaccion = Util.crearNotificacionSuccess("Correcto", Constantes.MENSAJE_ACTUALIZACION_CORRECTA);
			} catch (Exception e) {
				log.error("[RolServiceImpl] - method: actualizar - error: " + e.getMessage());
				notifiaccion = Util.notificacionErrorDelSistema();
			}

		}else{
			notifiaccion = Util.notificacionErrorDelSistema();
		}

		retorno.put("notificacion", notifiaccion);
		retorno.put("id", rol.getId());
		retorno.put("estado", true);

		return retorno;
	}

	public Rol get(Integer id){
		return rolDao.get(id);
	}

	@Transactional
	public Map<String, Object> eliminar(Integer[] ids){
		Map<String, Object> retorno = new HashMap<String, Object>();
		Map<String, Object> notifiaccion = null;
		
		if(isRolPrincipal(ids)){
			notifiaccion = Util.crearNotificacion("error", "Error", 
					"No se puede eliminar el rol principal del sistema.", 5000);
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
			Rol rol = rolDao.get(id);

			try {
				estadoEliminacion = rolDao.eliminar(rol);
				if(estadoEliminacion){
					notifiaccion = Util.crearNotificacionInfo("Informacion", textoNotificacion);
				}else{
					notifiaccion = Util.notificacionErrorDelSistema();
				}
			} catch (Exception e) {
				log.error("[RolServiceImpl] - method: eliminar - error: " + e.getMessage());
				notifiaccion = Util.notificacionErrorDelSistema();
			}
		}

		retorno.put("notificacion", notifiaccion);
		retorno.put("estado", estadoEliminacion);
		return retorno;
	}
	
	public List<Rol> listarTodos(){
		return rolDao.getTodos();
	}
	
	public List<Rol> listarRolesActivos(){
		return rolDao.listarRolesActivos();
	}
	
	public boolean isRolPrincipal(Integer[] ids){
		for(Integer id : ids){
			if(id == Constantes.SUPER_ROL_ID){
				return true;
			}
		}
		return false;
	}

	public List<Rol> listarRolesActivosSinRepetirPorUsuario(Integer idUsuario){
		List<Rol> listaRolesActivosSinRepeteir = new ArrayList<Rol>();
		List<Rol> listaRolesActivos = rolDao.listarRolesActivos();
		List<Rol> listaRolesPorUsuario = usuarioRolService.obtenerRolesPorUsuario(idUsuario);
		
		for(Rol rolActivo : listaRolesActivos){

			boolean isRolRegistrado = false;
			
			for(Rol rolUsuario: listaRolesPorUsuario){
				if(rolUsuario.getId() == rolActivo.getId()){
					isRolRegistrado = true;
				}
			}

			if(!isRolRegistrado){
				listaRolesActivosSinRepeteir.add(rolActivo);
			}
		}
		
		return listaRolesActivosSinRepeteir;
	}

	@Transactional
	public Map<String, Object> actualizarPrivilegios(Integer idRol, Integer[] idPrvivilegios){
		Map<String, Object> retorno = new HashMap<String, Object>();
		Map<String, Object> notifiaccion = null;

		Rol rol = rolDao.get(idRol);
		List<Privilegio> listaPrivilegios = new ArrayList<Privilegio>();
		for(Integer idPrivilegio: idPrvivilegios){
			Privilegio privilegio = new Privilegio();
			privilegio.setId(idPrivilegio);
			listaPrivilegios.add(privilegio);
		}

		rol.setPrivilegios(listaPrivilegios);

		try {
			rolDao.guardar(rol, usuarioService.getUID());
			notifiaccion = Util.crearNotificacionSuccess("Correcto", Constantes.MENSAJE_PRIVILEGIOS_ACTUALIZADOS_CORRECTAMENTE);
		} catch (Exception e) {
			log.error("[RolServiceImpl] - method: actualizarPrivilegios - error: " + e.getMessage());
			notifiaccion = Util.notificacionErrorDelSistema();
		}

		retorno.put("notificacion", notifiaccion);
		retorno.put("id", rol.getId());
		retorno.put("estado", true);

		return retorno;
	}
	
	public List<AccesoCompaniaRol> obtenerAccesoCompaniaRolPorRol(Integer idRol){
		return accesoCompaniaRolDao.obtenerAccesoCompaniaRolPorRol(idRol);
	}

}
