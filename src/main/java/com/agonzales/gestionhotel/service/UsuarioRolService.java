package com.agonzales.gestionhotel.service;

import java.util.Map;

import com.agonzales.gestionhotel.domain.UsuarioRol;

public interface UsuarioRolService {

	public Map<String, Object> guardar(UsuarioRol usuarioRol);

	public UsuarioRol get(Integer id);
	
	public Map<String, Object> eliminar(Integer[] ids);

}
