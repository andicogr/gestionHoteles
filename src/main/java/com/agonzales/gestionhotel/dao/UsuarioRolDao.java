package com.agonzales.gestionhotel.dao;

import java.util.List;

import com.agonzales.gestionhotel.domain.UsuarioRol;
import com.agonzales.gestionhotel.util.IDAO;

public interface UsuarioRolDao extends IDAO<UsuarioRol>{
	
	public List<UsuarioRol> obtenerUsuarioRolesPorUsuario(Integer idUsuario);

}
