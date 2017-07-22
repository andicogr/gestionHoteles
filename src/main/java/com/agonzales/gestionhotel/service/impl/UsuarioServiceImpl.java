package com.agonzales.gestionhotel.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
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

	public Map<String, Object> listarJson(PaginacionDTO paginacion, Boolean isMultiCompaniaActivado){

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
						usuario.getUsername(),
						usuario.isActivo() ? Constantes.ESTADO_ACTIVO_ETIQUETA : Constantes.ESTADO_INACTIVO_ETIQUETA
					};
			if(!isMultiCompaniaActivado){
				aaDato = (String[]) ArrayUtils.remove(aaDato, 1);
			}
			listas.add(aaDato);
		}

		datos.put("aaData", listas);

		return datos;
	}

	@Transactional
	public Map<String, Object> guardar(Usuario usuario){
		Map<String, Object> retorno = new HashMap<String, Object>();
		Map<String, Object> notifiaccion = null;
		String texto = "";

		if(usuarioDAO.isUniqueValue("username", usuario.getUsername(), usuario.getId())){
			notifiaccion = Util.crearNotificacionError("Error", "El nombre de usuario ya esta registrado en el sistema.");
			retorno.put("notificacion", notifiaccion);
			retorno.put("estado", false);
			return retorno;
		}

		if(usuario.getId() != null){
			Usuario actual = usuarioDAO.get(usuario.getId());
			actual.setUsername(usuario.getUsername());
			actual.setPassword(usuario.getPassword());
			actual.setActivo(usuario.isActivo());
			actual.setCompania(usuario.getCompania());
			actual.setExpirarUsuario(usuario.isExpirarUsuario());
			actual.setFechaExpiracionUsuario(usuario.getFechaExpiracionUsuario());
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

	public void registrarIntentoDeLogeoFallido(String username){
		Usuario usuario = usuarioDAO.buscaUsuario(username);
		usuario.incrementarNumeroDeIntentosFallidos();
		usuarioDAO.guardar(usuario, getUID());
	}

	public Boolean isPasswordIncorrecto(String username, String password){
		Usuario usuario = usuarioDAO.buscaUsuario(username);
		if(usuario.getPassword().equals(password)){
			return false;
		}
		return true;
	}

}
