package com.agonzales.gestionhotel.service;

import java.util.List;
import java.util.Map;

import com.agonzales.gestionhotel.domain.AccesoCompaniaRol;

public interface AccesoCompaniaRolService {
	
	public List<AccesoCompaniaRol> listaDeAccesoCompaniaRolActivasPorRol(Integer idRol);
	
	public Map<String, Object> guardar(AccesoCompaniaRol usuarioRol);
	
	public Map<String, Object> actualizar(AccesoCompaniaRol usuarioRol);

	public AccesoCompaniaRol get(Integer id);
	
	public Map<String, Object> eliminar(Integer[] ids);

}
