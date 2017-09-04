package com.agonzales.gestionhotel.service;

import java.util.List;
import java.util.Map;

import com.agonzales.gestionhotel.domain.AccesoCompaniaRol;
import com.agonzales.gestionhotel.domain.Rol;
import com.agonzales.gestionhotel.dto.PaginacionDTO;

public interface RolService {

	public Map<String, Object> listarJson(PaginacionDTO paginacion);

	public Map<String, Object> guardar(Rol rol);
	
	public Map<String, Object> actualizar(Rol rol);

	public Rol get(Integer id);
	
	public Map<String, Object> eliminar(Integer[] ids);

	public List<Rol> listarTodos();
	
	public List<Rol> listarRolesActivos();
	
	public List<Rol> listarRolesActivosSinRepetirPorUsuario(Integer idUsuario);
	
	Map<String, Object> actualizarPrivilegios(Integer idRol, Integer[] idPrvivilegios);
	
	public List<AccesoCompaniaRol> obtenerAccesoCompaniaRolPorRol(Integer idRol);

}
