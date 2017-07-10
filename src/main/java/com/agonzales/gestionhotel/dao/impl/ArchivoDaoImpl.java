package com.agonzales.gestionhotel.dao.impl;

import org.springframework.stereotype.Repository;

import com.agonzales.gestionhotel.dao.ArchivoDao;
import com.agonzales.gestionhotel.domain.Archivo;
import com.agonzales.gestionhotel.util.DAO;

@Repository("ArchivoDao")
public class ArchivoDaoImpl extends DAO<Archivo> implements ArchivoDao{

}
