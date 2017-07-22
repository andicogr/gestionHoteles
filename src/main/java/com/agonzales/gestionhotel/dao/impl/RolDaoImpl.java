package com.agonzales.gestionhotel.dao.impl;

import org.springframework.stereotype.Repository;

import com.agonzales.gestionhotel.dao.RolDao;
import com.agonzales.gestionhotel.domain.Rol;
import com.agonzales.gestionhotel.util.DAO;

@Repository("RolDao")
public class RolDaoImpl extends DAO<Rol> implements RolDao{

}
