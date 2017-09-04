package com.agonzales.gestionhotel.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;

import com.agonzales.gestionhotel.domain.Rol;
import com.agonzales.gestionhotel.domain.Usuario;
import com.agonzales.gestionhotel.dto.PaginacionDTO;

public interface UsuarioService {

	public Integer getUID();
	
	public Map<String, Object> listarJson(PaginacionDTO paginacion);

	public Map<String, Object> guardar(Usuario usuario);
	
	public Map<String, Object> actualizar(Usuario usuario);

	public Usuario get(Integer id);
	
	public Map<String, Object> eliminar(Integer[] ids);

	public List<Usuario> listarTodos();
	
	public void registrarIntentoDeLogeoFallido(String username);
	
	public Boolean isPasswordIncorrecto(String username, String password);
	
	public Collection<? extends GrantedAuthority> getAuthorities(List<Rol> roles);
	
	public Collection<? extends GrantedAuthority> getAuthorities(Rol rol);

}
