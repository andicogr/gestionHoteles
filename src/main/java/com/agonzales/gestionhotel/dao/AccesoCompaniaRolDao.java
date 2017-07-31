package com.agonzales.gestionhotel.dao;

import java.util.List;

import com.agonzales.gestionhotel.domain.AccesoCompaniaRol;
import com.agonzales.gestionhotel.util.IDAO;

public interface AccesoCompaniaRolDao extends IDAO<AccesoCompaniaRol>{
	
	public List<AccesoCompaniaRol> listaDeAccesoCompaniaRolActivasPorRol(Integer idRol);
	
	public List<AccesoCompaniaRol> obtenerAccesoCompaniaRolPorRol(Integer idRol);

}
