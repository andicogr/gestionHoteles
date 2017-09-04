package com.agonzales.gestionhotel.service;

import java.util.List;
import java.util.Map;

import com.agonzales.gestionhotel.domain.Rol;
import com.agonzales.gestionhotel.domain.UsuarioRol;

public interface UsuarioRolService {

	public Map<String, Object> guardar(UsuarioRol usuarioRol);
	
	public Map<String, Object> actualizar(UsuarioRol usuarioRol);

	public UsuarioRol get(Integer id);
	
	public Map<String, Object> eliminar(Integer[] ids);
	
	public List<Rol> obtenerRolesActivosPorUsuario(Integer idUsuario);
	
	public List<Rol> obtenerRolesPorUsuario(Integer idUsuario);

}
