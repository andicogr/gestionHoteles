package com.agonzales.gestionhotel.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agonzales.gestionhotel.dao.UsuarioDao;
import com.agonzales.gestionhotel.domain.Usuario;
import com.agonzales.gestionhotel.dto.PaginacionDTO;
import com.agonzales.gestionhotel.service.UsuarioService;
import com.agonzales.gestionhotel.util.Constantes;
import com.agonzales.gestionhotel.util.Util;

@Service("UsuarioService")
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioDao usuarioDAO;
	
	public Integer getUID(){
		User usuario = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return usuarioDAO.getUID(usuario.getUsername());
	}
	
	public Map<String, String> obtenerEstadosDeUsuario(){
		Map<String, String> listaDeEstados = new HashMap<String, String>();
		listaDeEstados.put(Constantes.ESTADO_BLOQUEADO, "Bloqueado");
		listaDeEstados.put(Constantes.ESTADO_INACTIVO, "Inactivo");
		listaDeEstados.put(Constantes.ESTADO_ACTIVO, "Activo");
		return listaDeEstados;
	}
	
	public Map<String, Object> listarJson(PaginacionDTO paginacion){

		if(paginacion.getiDisplayLength()==null){
			return null;
		}

		Map<String, Object> columnas = new HashMap<String, Object>();
		
		columnas.put("1", "a.compania.razonSocial");
		columnas.put("2", "a.usuario");

		List<Usuario> listaJson = usuarioDAO.listarJson(paginacion, columnas);
		Number total = usuarioDAO.totalListaJson();

		Map<String, Object> datos = new HashMap<String, Object>();

		datos.put("sEcho", paginacion.getsEcho());
		datos.put("iTotalRecords", total);
		datos.put("iTotalDisplayRecords", total);

		List<String[]> listas = new ArrayList<String[]>();
		
		for (Usuario usuario: listaJson) {
			
			String checkbox ="<input type=\"checkbox\" name=\"checkBoxRow\" class=\"flat\" value=\"" + usuario.getId() + "\"/>";

			String[] aaDato = {
						checkbox,
						usuario.getNombreCompania(),
						usuario.getUsuario(),
						usuario.isActivo() ? Constantes.ESTADO_ACTIVO : Constantes.ESTADO_INACTIVO
					};
			listas.add(aaDato);
		}

		datos.put("aaData", listas);
		
		return datos;
	}
	
	public String obtenerNombreParaMostrarDeEstado(String estadoDeUsuario){
		Map<String, String> listaDeEstados = obtenerEstadosDeUsuario();
		return listaDeEstados.get(estadoDeUsuario);
	}

	@Transactional
	public Map<String, Object> guardar(Usuario usuario){
		Map<String, Object> retorno = new HashMap<String, Object>();
		Map<String, Object> notifiaccion = null;
		String texto = "";

		if(usuarioDAO.isUniqueValue("usuario", usuario.getUsuario(), usuario.getId())){
			notifiaccion = Util.crearNotificacionError("Error", "El nombre de usuario ya esta registrado en el sistema.");
			retorno.put("notificacion", notifiaccion);
			retorno.put("estado", false);
			return retorno;
		}

		if(usuario.getId() != null){
			Usuario actual = usuarioDAO.get(usuario.getId());
			actual.setUsuario(usuario.getUsuario());
			actual.setClave(usuario.getClave());
			actual.setActivo(usuario.isActivo());
			usuario = actual;
		}

		if(usuario.getId() == null){
			texto = Constantes.MENSAJE_REGISTRO_CORRECTO;
		}else{
			texto = Constantes.MENSAJE_ACTUALIZACION_CORRECTA;
		}
		notifiaccion = Util.crearNotificacionSuccess("Correcto", texto);

		usuarioDAO.guardar(usuario, getUID());

		retorno.put("notificacion", notifiaccion);
		retorno.put("id", usuario.getId());
		retorno.put("estado", true);

		return retorno;
	}

	public Usuario get(Integer id){
		return usuarioDAO.get(id);
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
			Usuario usuario = usuarioDAO.get(id);
			estado = usuarioDAO.eliminar(usuario);
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
	
	public List<Usuario> listarTodos(){
		return usuarioDAO.getTodos();
	}

}
