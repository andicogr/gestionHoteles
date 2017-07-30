package com.agonzales.gestionhotel.service;

import java.util.List;
import java.util.Map;

import com.agonzales.gestionhotel.domain.Privilegio;

public interface PrivilegioService {

	public List<Map<String, Object>> obtenerListaDePrivilegiosPadres(Integer idRol);

	public Privilegio get(Integer id);

	public List<Privilegio> listarTodos();

}
