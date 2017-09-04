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
			List<Map<String, Object>> childrens = obtenerHijosDePrivilegio(privilegio, idRol);
			privilegioMap.put("id", privilegio.getId());
			privilegioMap.put("text", privilegio.getDescripcion());
			privilegioMap.put("checked", isPrivilegioChecked(privilegio, idRol, childrens));
			privilegioMap.put("children", childrens);
			listaDeArbolDePrivilegios.add(privilegioMap);
		}

		return listaDeArbolDePrivilegios;
	}

	private List<Map<String, Object>> obtenerHijosDePrivilegio(Privilegio privilegio, Integer idRol){
		List<Map<String, Object>> listaDePrivilegiosHijo = new ArrayList<Map<String,Object>>();

		for(Privilegio privilegioHijo : privilegio.getPrivilegios()){
			List<Map<String, Object>> childrens = obtenerHijosDePrivilegio(privilegioHijo, idRol);
			Map<String, Object> privilegioHijoMap = new HashMap<String, Object>();
			privilegioHijoMap.put("id", privilegioHijo.getId());
			privilegioHijoMap.put("text", privilegioHijo.getDescripcion());
			privilegioHijoMap.put("checked", isPrivilegioChecked(privilegioHijo, idRol, childrens));
			privilegioHijoMap.put("children", childrens);
			listaDePrivilegiosHijo.add(privilegioHijoMap);
		}
		return listaDePrivilegiosHijo;
	}

	private boolean isPrivilegioChecked(Privilegio privilegio, Integer idRol, List<Map<String, Object>> hijos){
		boolean isPrivilegioAsociadoRol = rolDao.isPrivilegioAsociadoARol(privilegio.getId(), idRol);
		if(!hijos.isEmpty()){
			boolean isTodosHijosChecked = true;
			for(Map<String, Object> privilegioMap : hijos){
				if(((Boolean) privilegioMap.get("checked")) == false){
					isTodosHijosChecked = false;
				}
			}
			return isPrivilegioAsociadoRol && isTodosHijosChecked;
		}else{
			return isPrivilegioAsociadoRol;
		}
	}

	public Privilegio get(Integer id){
		return privilegioDao.get(id);
	}

	public List<Privilegio> listarTodos(){
		return privilegioDao.getTodos();
	}


}
