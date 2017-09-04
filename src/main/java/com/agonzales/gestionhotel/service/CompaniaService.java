package com.agonzales.gestionhotel.service;

import java.util.List;
import java.util.Map;

import com.agonzales.gestionhotel.domain.Compania;
import com.agonzales.gestionhotel.dto.PaginacionDTO;

public interface CompaniaService {
	
	public Map<String, Object> listarJson(PaginacionDTO paginacion);

	public Map<String, Object> guardar(Compania compania);
	
	public Map<String, Object> actualizar(Compania compania);

	public Compania get(Integer id);
	
	public Map<String, Object> eliminar(Integer[] ids);

	public List<Compania> listarTodos();
	
	public List<Compania> listarCompaniasActivasSinRepetirPorRol(Integer idRol);
	
	public List<Compania> listarCompaniasActivas();

}
