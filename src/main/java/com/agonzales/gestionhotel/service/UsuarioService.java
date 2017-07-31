package com.agonzales.gestionhotel.service;

import java.util.List;
import java.util.Map;

import com.agonzales.gestionhotel.domain.Usuario;
import com.agonzales.gestionhotel.domain.UsuarioRol;
import com.agonzales.gestionhotel.dto.PaginacionDTO;

public interface UsuarioService {

	public Integer getUID();
	
	public Map<String, Object> listarJson(PaginacionDTO paginacion, boolean isMultiCompaniaActivado);

	public Map<String, Object> guardar(Usuario usuario);
	
	public Map<String, Object> actualizar(Usuario usuario);

	public Usuario get(Integer id);
	
	public Map<String, Object> eliminar(Integer[] ids);

	public List<Usuario> listarTodos();
	
	public void registrarIntentoDeLogeoFallido(String username);
	
	public Boolean isPasswordIncorrecto(String username, String password);
	
	public List<UsuarioRol> obtenerUsuarioRolesPorUsuario(Integer idUsuario);

}
