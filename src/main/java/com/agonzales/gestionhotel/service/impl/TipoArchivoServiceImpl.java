package com.agonzales.gestionhotel.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agonzales.gestionhotel.dao.TipoArchivoDao;
import com.agonzales.gestionhotel.domain.TipoArchivo;
import com.agonzales.gestionhotel.dto.PaginacionDTO;
import com.agonzales.gestionhotel.service.TipoArchivoService;
import com.agonzales.gestionhotel.service.UsuarioService;
import com.agonzales.gestionhotel.util.Constantes;
import com.agonzales.gestionhotel.util.Util;

@Service("TipoArchivoService")
public class TipoArchivoServiceImpl implements TipoArchivoService{

	@Autowired
	private TipoArchivoDao tipoArchivoDAO;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public Map<String, Object> listarJson(PaginacionDTO paginacion){

		if(paginacion.getiDisplayLength()==null){
			return null;
		}

		Map<String, Object> columnas = new HashMap<String, Object>();
		
		columnas.put("1", "a.nombre");

		List<TipoArchivo> listaJson = tipoArchivoDAO.listarJson(paginacion, columnas);
		Number total = tipoArchivoDAO.totalListaJson();

		Map<String, Object> datos = new HashMap<String, Object>();

		datos.put("sEcho", paginacion.getsEcho());
		datos.put("iTotalRecords", total);
		datos.put("iTotalDisplayRecords", total);

		List<String[]> listas = new ArrayList<String[]>();
		
		for (TipoArchivo tipoArchivo: listaJson) {
			
			String checkbox ="<input type=\"checkbox\" name=\"checkBoxRow\" class=\"flat\" value=\"" + tipoArchivo.getId() + "\"/>";
			String activo = tipoArchivo.isActivo() ? Constantes.ESTADO_ACTIVO : Constantes.ESTADO_INACTIVO;

			String[] aaDato = {
						checkbox,
						tipoArchivo.getNombre(),
						activo
					};
			listas.add(aaDato);
		}
		
		datos.put("aaData", listas);
		
		return datos;
	}

	@Transactional
	public Map<String, Object> guardar(TipoArchivo tipoArchivo){
		Map<String, Object> retorno = new HashMap<String, Object>();
		Map<String, Object> notifiaccion = null;
		String texto = "";

		if(tipoArchivoDAO.isUniqueValue("nombre", tipoArchivo.getNombre(), tipoArchivo.getId())){
			notifiaccion = Util.crearNotificacionError("Error", "El nombre ya esta registrado en el sistema.");
			retorno.put("notificacion", notifiaccion);
			retorno.put("estado", false);
			return retorno;
		}

		if(tipoArchivo.getId() != null){
			TipoArchivo actual = tipoArchivoDAO.get(tipoArchivo.getId());
			actual.setNombre(tipoArchivo.getNombre());
			actual.setDescripcion(tipoArchivo.getDescripcion());
			actual.setActivo(tipoArchivo.isActivo());
			tipoArchivo = actual;
		}

		if(tipoArchivo.getId() == null){
			texto = Constantes.MENSAJE_REGISTRO_CORRECTO;
		}else{
			texto = Constantes.MENSAJE_ACTUALIZACION_CORRECTA;
		}
		notifiaccion = Util.crearNotificacionSuccess("Correcto", texto);

		tipoArchivoDAO.guardar(tipoArchivo, usuarioService.getUID());

		retorno.put("notificacion", notifiaccion);
		retorno.put("id", tipoArchivo.getId());
		retorno.put("estado", true);

		return retorno;
	}
	
	public TipoArchivo get(Integer id){
		return tipoArchivoDAO.get(id);
	}

	@Transactional
	public Map<String, Object> eliminar(Integer[] ids){
		Map<String, Object> retorno = new HashMap<String, Object>();
		Map<String, Object> notifiaccion = null;
		boolean estado = false;
		String texto = Constantes.MENSAJE_REGISTRO_ELIMINADO;
		if(ids.length > 1){
			texto = Constantes.MENSAJE_REGISTROS_ELIMINADOS;
		}
		for(Integer id : ids){
			TipoArchivo tipoArchivo = tipoArchivoDAO.get(id);
			estado = tipoArchivoDAO.eliminar(tipoArchivo);
		}
		if(estado){
			notifiaccion = Util.crearNotificacionInfo("Informacion", texto);
		}else{
			notifiaccion = Util.crearNotificacion("error", "Error", 
					"Ocurrio un error mientras se eliminaba el registro, "
					+ "por favor comuniquese con el administrador del sistema.", 5000);
		}

		retorno.put("notificacion", notifiaccion);
		retorno.put("estado", estado);
		return retorno;
	}
	
	public List<TipoArchivo> listarTodos(){
		return tipoArchivoDAO.getTodos();
	}
}
