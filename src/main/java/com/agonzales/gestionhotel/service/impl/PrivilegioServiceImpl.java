package com.agonzales.gestionhotel.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agonzales.gestionhotel.dao.PrivilegioDao;
import com.agonzales.gestionhotel.domain.Privilegio;
import com.agonzales.gestionhotel.dto.PaginacionDTO;
import com.agonzales.gestionhotel.service.PrivilegioService;
import com.agonzales.gestionhotel.service.UsuarioService;
import com.agonzales.gestionhotel.util.Constantes;
import com.agonzales.gestionhotel.util.Util;

@Service("privilegioDao")
public class PrivilegioServiceImpl implements PrivilegioService{
	
	@Autowired
	private PrivilegioDao privilegioDao;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public Map<String, Object> listarJson(PaginacionDTO paginacion){

		if(paginacion.getiDisplayLength()==null){
			return null;
		}

		Map<String, Object> columnas = new HashMap<String, Object>();
		
		columnas.put("1", "a.nombre");

		List<Privilegio> listaJson = privilegioDao.listarJson(paginacion, columnas);
		Number total = privilegioDao.totalListaJson();

		Map<String, Object> datos = new HashMap<String, Object>();

		datos.put("sEcho", paginacion.getsEcho());
		datos.put("iTotalRecords", total);
		datos.put("iTotalDisplayRecords", total);

		List<String[]> listas = new ArrayList<String[]>();
		
		for (Privilegio privilegio: listaJson) {
			
			String checkbox ="<input type=\"checkbox\" name=\"checkBoxRow\" class=\"flat\" value=\"" + privilegio.getId() + "\"/>";

			String[] aaDato = {
						checkbox,
						privilegio.getNombre(),
						Util.obtenerNombreEstado(privilegio.isActivo())
					};
			listas.add(aaDato);
		}

		datos.put("aaData", listas);
		
		return datos;
	}

	@Transactional
	public Map<String, Object> guardar(Privilegio privilegio){
		Map<String, Object> retorno = new HashMap<String, Object>();
		Map<String, Object> notifiaccion = null;
		String textoNotificacion = Constantes.MENSAJE_REGISTRO_CORRECTO;

		if(privilegioDao.isUniqueValue("nombre", privilegio.getNombre(), privilegio.getId())){
			notifiaccion = Util.crearNotificacionError("Error", "El nombre del privilegio ya esta registrada en el sistema.");
			retorno.put("notificacion", notifiaccion);
			retorno.put("estado", false);
			return retorno;
		}

		if(privilegio.getId() != null){
			textoNotificacion = Constantes.MENSAJE_ACTUALIZACION_CORRECTA;
			
			Privilegio actual = privilegioDao.get(privilegio.getId());
			actual.setNombre(privilegio.getNombre());
			actual.setDescripcion(privilegio.getDescripcion());
			actual.setActivo(privilegio.isActivo());

			privilegio = actual;
		}

		notifiaccion = Util.crearNotificacionSuccess("Correcto", textoNotificacion);

		privilegioDao.guardar(privilegio, usuarioService.getUID());

		retorno.put("notificacion", notifiaccion);
		retorno.put("id", privilegio.getId());
		retorno.put("estado", true);

		return retorno;
	}

	public Privilegio get(Integer id){
		return privilegioDao.get(id);
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
			Privilegio privilegio = privilegioDao.get(id);
			estadoEliminacion = privilegioDao.eliminar(privilegio);
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
	
	public List<Privilegio> listarTodos(){
		return privilegioDao.getTodos();
	}


}
