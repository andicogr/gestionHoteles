package com.agonzales.gestionhotel.dao;

import java.util.List;

import com.agonzales.gestionhotel.domain.Compania;
import com.agonzales.gestionhotel.util.IDAO;

public interface CompaniaDao extends IDAO<Compania>{
	
	public List<Compania> listarCompaniasActivas();

}
