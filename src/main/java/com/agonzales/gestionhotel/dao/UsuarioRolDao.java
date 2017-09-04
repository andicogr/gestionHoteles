package com.agonzales.gestionhotel.dao;

import java.util.List;

import com.agonzales.gestionhotel.domain.Rol;
import com.agonzales.gestionhotel.domain.UsuarioRol;
import com.agonzales.gestionhotel.util.IDAO;

public interface UsuarioRolDao extends IDAO<UsuarioRol>{
	
	public List<Rol> obtenerRolesActivosPorUsuario(Integer idUsuario);
	
	public List<Rol> obtenerRolesPorUsuario(Integer idUsuario);

}
