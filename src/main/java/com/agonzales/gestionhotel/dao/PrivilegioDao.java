package com.agonzales.gestionhotel.dao;

import java.util.List;

import com.agonzales.gestionhotel.domain.Privilegio;
import com.agonzales.gestionhotel.util.IDAO;

public interface PrivilegioDao extends IDAO<Privilegio>{
	
	public List<Privilegio> obtenerListaDePrivilegiosPadresActivos();

}
