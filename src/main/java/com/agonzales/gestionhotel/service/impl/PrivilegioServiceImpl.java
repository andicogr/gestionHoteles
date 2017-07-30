package com.agonzales.gestionhotel.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agonzales.gestionhotel.dao.PrivilegioDao;
import com.agonzales.gestionhotel.dao.RolDao;
import com.agonzales.gestionhotel.domain.Privilegio;
import com.agonzales.gestionhotel.service.PrivilegioService;

@Service("privilegioDao")
public class PrivilegioServiceImpl implements PrivilegioService{
	
	@Autowired
	private PrivilegioDao privilegioDao;

	@Autowired
	private RolDao rolDao;

	public List<Map<String, Object>> obtenerListaDePrivilegiosPadres(Integer idRol){
		List<Map<String, Object>> listaDeArbolDePrivilegios = new ArrayList<Map<String,Object>>();
		List<Privilegio> listaPrivilegiosPadres = privilegioDao.obtenerListaDePrivilegiosPadresActivos();

		for(Privilegio privilegio : listaPrivilegiosPadres){
			Map<String, Object> privilegioMap = new HashMap<String, Object>();
			privilegioMap.put("id", privilegio.getId());
			privilegioMap.put("text", privilegio.getDescripcion());
			privilegioMap.put("checked", mostrarPrivilegioChecked(privilegio, idRol));
			privilegioMap.put("children", obtenerHijosDePrivilegio(privilegio, idRol));
			listaDeArbolDePrivilegios.add(privilegioMap);
		}

		return listaDeArbolDePrivilegios;
	}

	public List<Map<String, Object>> obtenerHijosDePrivilegio(Privilegio privilegio, Integer idRol){
		List<Map<String, Object>> listaDePrivilegiosHijo = new ArrayList<Map<String,Object>>();

		for(Privilegio privilegioHijo : privilegio.getPrivilegios()){
			Map<String, Object> privilegioHijoMap = new HashMap<String, Object>();
			privilegioHijoMap.put("id", privilegioHijo.getId());
			privilegioHijoMap.put("text", privilegioHijo.getDescripcion());
			privilegioHijoMap.put("checked", mostrarPrivilegioChecked(privilegioHijo, idRol));
			privilegioHijoMap.put("children", obtenerHijosDePrivilegio(privilegioHijo, idRol));
			listaDePrivilegiosHijo.add(privilegioHijoMap);
		}
		return listaDePrivilegiosHijo;
	}

	public Privilegio get(Integer id){
		return privilegioDao.get(id);
	}

	public List<Privilegio> listarTodos(){
		return privilegioDao.getTodos();
	}

	public boolean mostrarPrivilegioChecked(Privilegio privilegio, Integer idRol){
		boolean estaAsociadoARol = rolDao.isPrivilegioAsociadoARol(privilegio.getId(), idRol);
		boolean tieneTodosLosHijos = true;

		for(Privilegio privilegioHijo: privilegio.getPrivilegios()){
			if(!rolDao.isPrivilegioAsociadoARol(privilegioHijo.getId(), idRol)){
				tieneTodosLosHijos = false;
			}
		}

		return estaAsociadoARol && tieneTodosLosHijos;
	}

}
