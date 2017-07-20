package com.agonzales.gestionhotel.dao;

import com.agonzales.gestionhotel.domain.Usuario;
import com.agonzales.gestionhotel.util.IDAO;

public interface UsuarioDao extends IDAO<Usuario>{
	
	Usuario existeUsuario(String username, String password);
	
	Usuario buscaUsuario(String username);
	
	Integer getUID(String username);

}
