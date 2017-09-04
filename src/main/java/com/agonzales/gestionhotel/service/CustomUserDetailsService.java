package com.agonzales.gestionhotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agonzales.gestionhotel.dao.UsuarioDao;
import com.agonzales.gestionhotel.domain.Usuario;


/**
 * A custom {@link UserDetailsService} where user information is retrieved from
 * a JPA repository
 */
@Service
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioDao usuarioDAO;
	
	@Autowired
	private UsuarioService usuarioService;

	/**
	 * Returns a populated {@link UserDetails} object. The username is first
	 * retrieved from the database and then mapped to a {@link UserDetails}
	 * object.
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {

			Usuario usuario = usuarioDAO.buscaUsuario(username);

			if(usuario == null){
				return null;
			}

			if(usuarioService.isPasswordIncorrecto(username, usuario.getPassword())){
				usuarioService.registrarIntentoDeLogeoFallido(username);
			}

			//TODO obtener solo los privilegios de un rol no de todos
			return new User(
					username, usuario.getPassword(), usuario.isActivo(),
					!usuario.isUsuarioExpirado(), usuario.isRolesActivos(), !usuario.isBloqueado(),
					usuarioService.getAuthorities(usuario.getRolUsuario()));

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}


}
