package com.agonzales.gestionhotel.service;

import java.util.Map;

import com.agonzales.gestionhotel.domain.AjustesConfiguracion;

public interface AjustesConfiguracionService {
	
	public boolean isMultiCompaniaActivado();
	
	public AjustesConfiguracion obtenerAjustesDeConfiguracion();
	
	public Map<String, Object> guardar(AjustesConfiguracion ajustesConfig);
	
	public Map<String, Object> actualizar(AjustesConfiguracion ajustesConfig);
}
