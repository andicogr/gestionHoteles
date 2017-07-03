package com.agonzales.gestionhotel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.agonzales.gestionhotel.dao.UsuarioDao;
import com.agonzales.gestionhotel.service.UsuarioService;

@Service("UsuarioService")
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioDao usuarioDAO;
	
	public Integer getUID(){
		User usuario = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		return usuarioDAO.getUID(usuario.getUsername());
	}

}
