package com.agonzales.gestionhotel.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agonzales.gestionhotel.dao.RolDao;
import com.agonzales.gestionhotel.domain.Rol;
import com.agonzales.gestionhotel.domain.UsuarioRol;
import com.agonzales.gestionhotel.dto.PaginacionDTO;
import com.agonzales.gestionhotel.service.RolService;
import com.agonzales.gestionhotel.service.UsuarioService;
import com.agonzales.gestionhotel.util.Constantes;
import com.agonzales.gestionhotel.util.Util;

@Service("RolService")
public class RolServiceImpl implements RolService{
	
	@Autowired
	private RolDao rolDao;
	
	@Autowired
	private UsuarioService usuarioService;
	
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
		String textoNotificacion = Constantes.MENSAJE_REGISTRO_CORRECTO;

		if(rolDao.isUniqueValue("nombre", rol.getNombre(), rol.getId())){
			notifiaccion = Util.crearNotificacionError("Error", "El nombre del rol ya esta registrada en el sistema.");
			retorno.put("notificacion", notifiaccion);
			retorno.put("estado", false);
			return retorno;
		}

		if(rol.getId() != null){
			textoNotificacion = Constantes.MENSAJE_ACTUALIZACION_CORRECTA;
			
			Rol actual = rolDao.get(rol.getId());
			actual.setNombre(rol.getNombre());
			actual.setDescripcion(rol.getDescripcion());
			actual.setActivo(rol.isActivo());

			rol = actual;
		}

		notifiaccion = Util.crearNotificacionSuccess("Correcto", textoNotificacion);

		rolDao.guardar(rol, usuarioService.getUID());

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
			estadoEliminacion = rolDao.eliminar(rol);
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
		List<UsuarioRol> listaUsuarioRolesPorUsuario = usuarioService.obtenerUsuarioRolesPorUsuario(idUsuario);
		
		for(Rol rol : listaRolesActivos){
			
			boolean isRolRegistrado = false;
			
			for(UsuarioRol usuarioRol: listaUsuarioRolesPorUsuario){
				if(usuarioRol.getRolId() == rol.getId()){
					isRolRegistrado = true;
				}
			}

			if(!isRolRegistrado){
				listaRolesActivosSinRepeteir.add(rol);
			}
		}
		
		return listaRolesActivosSinRepeteir;
	}

}
