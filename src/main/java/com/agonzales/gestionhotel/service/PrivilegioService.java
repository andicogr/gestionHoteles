package com.agonzales.gestionhotel.service;

import java.util.List;
import java.util.Map;

import com.agonzales.gestionhotel.domain.Privilegio;
import com.agonzales.gestionhotel.dto.PaginacionDTO;

public interface PrivilegioService {

	public Map<String, Object> listarJson(PaginacionDTO paginacion);

	public Map<String, Object> guardar(Privilegio privilegio);

	public Privilegio get(Integer id);
	
	public Map<String, Object> eliminar(Integer[] ids);

	public List<Privilegio> listarTodos();

}
