package com.agonzales.gestionhotel.dao;

import java.util.List;

import com.agonzales.gestionhotel.domain.Rol;
import com.agonzales.gestionhotel.util.IDAO;

public interface RolDao extends IDAO<Rol>{

	public List<Rol> listarRolesActivos();
	
	boolean isPrivilegioAsociadoARol(Integer idPrivilegio, Integer idRol);
}
