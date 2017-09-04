package com.agonzales.gestionhotel.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agonzales.gestionhotel.dao.UsuarioDao;
import com.agonzales.gestionhotel.domain.Privilegio;
import com.agonzales.gestionhotel.domain.Rol;
import com.agonzales.gestionhotel.domain.Usuario;
import com.agonzales.gestionhotel.dto.PaginacionDTO;
import com.agonzales.gestionhotel.service.UsuarioService;
import com.agonzales.gestionhotel.util.Constantes;
import com.agonzales.gestionhotel.util.Util;
import com.agonzales.gestionhotel.util.VariablesSession;

@Service("UsuarioService")
public class UsuarioServiceImpl implements UsuarioService{
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);
	
	@Autowired
	private UsuarioDao usuarioDAO;

	public Integer getUID(){
		User usuario = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return usuarioDAO.getUID(usuario.getUsername());
	}

	public Map<String, Object> listarJson(PaginacionDTO paginacion){

		boolean multiCompania = (Boolean) VariablesSession.getAttribute(Constantes.MULTICOMPANIA_ACTIVADO);
		
		if(paginacion.getiDisplayLength()==null){
			return null;
		}

		Map<String, Object> columnas = new HashMap<String, Object>();

		columnas.put("1", "a.compania.razonSocial");
		columnas.put("2", "a.username");
		
		if(!multiCompania){
			columnas = Util.reordenarColumnasPorConfiguracionMultiCompania(columnas);
		}

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
						Util.obtenerNombreEstado(usuario.isActivo())
					};
			if(!multiCompania){
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

		if(usuarioDAO.isUniqueValue("username", usuario.getUsername(), usuario.getId())){
			notifiaccion = Util.crearNotificacionError("Error", "El nombre de usuario ya esta registrado en el sistema.");
			retorno.put("notificacion", notifiaccion);
			retorno.put("estado", false);
			return retorno;
		}

		try {
			usuarioDAO.guardar(usuario, getUID());
			notifiaccion = Util.crearNotificacionSuccess("Correcto", Constantes.MENSAJE_REGISTRO_CORRECTO);
		} catch (Exception e) {
			log.error("[UsuarioServiceImpl] - method: guardar - error: " + e.getMessage());
			notifiaccion = Util.notificacionErrorDelSistema();
		}

		retorno.put("notificacion", notifiaccion);
		retorno.put("id", usuario.getId());
		retorno.put("estado", true);

		return retorno;
	}

	@Transactional
	public Map<String, Object> actualizar(Usuario usuario){
		Map<String, Object> retorno = new HashMap<String, Object>();
		Map<String, Object> notifiaccion = null;

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
			actual.setRolPorDefecto(usuario.getRolPorDefecto());
			actual.setCompaniaPorDefecto(usuario.getCompaniaPorDefecto());

			try {
				usuarioDAO.guardar(usuario, getUID());
				notifiaccion = Util.crearNotificacionSuccess("Correcto", Constantes.MENSAJE_ACTUALIZACION_CORRECTA);
			} catch (Exception e) {
				log.error("[UsuarioServiceImpl] - method: actualizar - error: " + e.getMessage());
				notifiaccion = Util.notificacionErrorDelSistema();
			}

		}else{
			notifiaccion = Util.notificacionErrorDelSistema();
		}

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
		
		if(isUsuarioPrincipal(ids)){
			notifiaccion = Util.crearNotificacion("error", "Error", 
					"No se puede eliminar el usuario principal del sistema.", 5000);
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
			Usuario usuario = usuarioDAO.get(id);
			
			try {
				estadoEliminacion = usuarioDAO.eliminar(usuario);

				if(estadoEliminacion){
					notifiaccion = Util.crearNotificacionInfo("Informacion", textoNotificacion);
				}else{
					notifiaccion = Util.notificacionErrorDelSistema();
				}

			} catch (Exception e) {
				log.error("[UsuarioServiceImpl] - method: eliminar - error: " + e.getMessage());
				notifiaccion = Util.notificacionErrorDelSistema();
			}
		}

		retorno.put("notificacion", notifiaccion);
		retorno.put("estado", estadoEliminacion);
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
	
	public boolean isUsuarioPrincipal(Integer[] ids){
		for(Integer id : ids){
			if(id == Constantes.SUPER_UID){
				return true;
			}
		}
		return false;
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities(List<Rol> roles) {
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		for(Rol rol: roles){
			authList.addAll(getGrantedAuthorities(getPrivilegios(rol)));
		}
				
		return authList;
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities(Rol rol) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getPrivilegios(rol));
		return authList;
	}

    private List<String> getPrivilegios(Rol rol) {
        List<String> privilegios = new ArrayList<String>();
        List<Privilegio> collection = new ArrayList<Privilegio>();
        if(rol != null){
            collection.addAll(rol.getPrivilegios());  
            
            for (Privilegio item : collection) {
            	privilegios.add(item.getNombrePrivilegio());
            }
        }

        return privilegios;
    }

	public static List<GrantedAuthority> getGrantedAuthorities(List<String> privilegios) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String privilegio : privilegios) {
			authorities.add(new SimpleGrantedAuthority(privilegio));
		}
		return authorities;
	}

}
