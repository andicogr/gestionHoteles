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
	
	public Usuario existeUsuario(String username, String clave){
		String sql = "from Usuario where usuario like :usuario and clave like :clave";
		
		Query q = em.createQuery(sql);
		
		q.setParameter("usuario", username);
		q.setParameter("clave", clave);
		
		Usuario usuario = (Usuario) q.getSingleResult();
		return usuario;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Usuario buscaUsuario(String username) {
			
		String sql="from Usuario a where a.usuario=:usuario";
		Query q = em.createQuery(sql);
		q.setParameter("usuario",username);

		try{
			return (Usuario) q.getSingleResult();
		}
		catch(NoResultException e){
			return null;
		}
	}
	
	public Integer getUID(String username){
		String sql = "SELECT id FROM Usuario WHERE usuario =:usuario";
		
		Query q = em.createQuery(sql);
		
		q.setParameter("usuario", username);
		
		Integer uid = (Integer) q.getSingleResult();
		
		return uid;
	}

}

