package com.agonzales.gestionhotel.service;

import java.util.List;
import java.util.Map;

import com.agonzales.gestionhotel.domain.Usuario;
import com.agonzales.gestionhotel.dto.PaginacionDTO;

public interface UsuarioService {

	public Integer getUID();
	
	public Map<String, Object> listarJson(PaginacionDTO paginacion, Boolean isMultiCompaniaActivado);

	public Map<String, Object> guardar(Usuario compania);

	public Usuario get(Integer id);
	
	public Map<String, Object> eliminar(Integer[] ids);

	public List<Usuario> listarTodos();
	
	public void registrarIntentoDeLogeoFallido(String username);
	
	public Boolean isPasswordIncorrecto(String username, String password);

}
