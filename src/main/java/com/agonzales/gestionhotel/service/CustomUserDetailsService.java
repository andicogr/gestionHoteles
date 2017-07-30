package com.agonzales.gestionhotel.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agonzales.gestionhotel.dao.UsuarioDao;
import com.agonzales.gestionhotel.domain.Privilegio;
import com.agonzales.gestionhotel.domain.Rol;
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

			return new User(
					username, usuario.getPassword(), usuario.isActivo(),
					!usuario.isUsuarioExpirado(), usuario.isRolesActivos(), !usuario.isBloqueado(),
					getAuthorities(usuario.getRolesActivos()));

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public Collection<? extends GrantedAuthority> getAuthorities(
			List<Rol> roles) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getPrivilegios(roles));
		return authList;
	}

    private List<String> getPrivilegios(List<Rol> roles) {

        List<String> privilegios = new ArrayList<String>();
        List<Privilegio> collection = new ArrayList<Privilegio>();
        for (Rol rol : roles) {
        	collection.addAll(rol.getPrivilegios());  
        }
        for (Privilegio item : collection) {
        	privilegios.add(item.getNombrePrivilegio());
        }
        return privilegios;
    }

	public static List<GrantedAuthority> getGrantedAuthorities(
			List<String> privilegios) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String privilegio : privilegios) {
			authorities.add(new SimpleGrantedAuthority(privilegio));
		}
		return authorities;
	}
}
