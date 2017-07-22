package com.agonzales.gestionhotel.dao.impl;

import org.springframework.stereotype.Repository;

import com.agonzales.gestionhotel.dao.PrivilegioDao;
import com.agonzales.gestionhotel.domain.Privilegio;
import com.agonzales.gestionhotel.util.DAO;

@Repository("PrivilegioDao")
public class PrivilegioDaoImpl extends DAO<Privilegio> implements PrivilegioDao{

}
