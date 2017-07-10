package com.agonzales.gestionhotel.service;

import java.util.List;
import java.util.Map;

import com.agonzales.gestionhotel.domain.TipoArchivo;
import com.agonzales.gestionhotel.dto.PaginacionDTO;

public interface TipoArchivoService {

	public Map<String, Object> listarJson(PaginacionDTO paginacion);

	public Map<String, Object> guardar(TipoArchivo tipoArchivo);

	public TipoArchivo get(Integer id);
	
	public Map<String, Object> eliminar(Integer[] ids);

	public List<TipoArchivo> listarTodos();
}
