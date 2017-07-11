package com.agonzales.gestionhotel.service;

import java.util.List;
import java.util.Map;

import com.agonzales.gestionhotel.domain.Usuario;
import com.agonzales.gestionhotel.dto.PaginacionDTO;

public interface UsuarioService {

	public Integer getUID();
	
	public Map<String, String> obtenerEstadosDeUsuario();
	
	public Map<String, Object> listarJson(PaginacionDTO paginacion);
	
	public String obtenerNombreParaMostrarDeEstado(String estadoDeUsuario);

	public Map<String, Object> guardar(Usuario compania);

	public Usuario get(Integer id);
	
	public Map<String, Object> eliminar(Integer[] ids);

	public List<Usuario> listarTodos();
	

}
