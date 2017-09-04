package com.agonzales.gestionhotel.dao;

import com.agonzales.gestionhotel.domain.AjustesConfiguracion;
import com.agonzales.gestionhotel.util.IDAO;

public interface AjustesConfiguracionDao extends IDAO<AjustesConfiguracion>{

	public AjustesConfiguracion getAjustesDeConfiguracion();
}
