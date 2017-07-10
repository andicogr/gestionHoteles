package com.agonzales.gestionhotel.dao.impl;

import org.springframework.stereotype.Repository;

import com.agonzales.gestionhotel.dao.TipoArchivoDao;
import com.agonzales.gestionhotel.domain.TipoArchivo;
import com.agonzales.gestionhotel.util.DAO;

@Repository("TipoArchivoDao")
public class TipoArchivoDaoImpl extends DAO<TipoArchivo> implements TipoArchivoDao{

}
