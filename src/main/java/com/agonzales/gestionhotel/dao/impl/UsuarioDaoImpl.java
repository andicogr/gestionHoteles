package com.agonzales.gestionhotel.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.agonzales.gestionhotel.dao.UsuarioDao;
import com.agonzales.gestionhotel.domain.Usuario;
import com.agonzales.gestionhotel.util.DAO;


@Repository("UsuarioDao")
public class UsuarioDaoImpl extends DAO<Usuario> implements UsuarioDao{
	
	public Usuario existeUsuario(String username, String pasword){
		String sql = "from Usuario where username like :username and password like :password";
		
		Query q = em.createQuery(sql);
		
		q.setParameter("username", username);
		q.setParameter("password", pasword);
		
		Usuario usuario = (Usuario) q.getSingleResult();
		return usuario;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Usuario buscaUsuario(String username) {
			
		String sql="from Usuario a where a.username=:username";
		Query q = em.createQuery(sql);
		q.setParameter("username",username);

		try{
			return (Usuario) q.getSingleResult();
		}
		catch(NoResultException e){
			return null;
		}
	}
	
	public Integer getUID(String username){
		String sql = "SELECT id FROM Usuario WHERE username =:username";
		
		Query q = em.createQuery(sql);
		
		q.setParameter("username", username);
		
		Integer uid = (Integer) q.getSingleResult();
		
		return uid;
	}

}

