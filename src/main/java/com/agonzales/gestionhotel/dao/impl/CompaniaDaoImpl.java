package com.agonzales.gestionhotel.dao.impl;

import org.springframework.stereotype.Repository;

import com.agonzales.gestionhotel.dao.CompaniaDao;
import com.agonzales.gestionhotel.domain.Compania;
import com.agonzales.gestionhotel.util.DAO;

@Repository("CompaniaDao")
public class CompaniaDaoImpl extends DAO<Compania> implements CompaniaDao{


}
