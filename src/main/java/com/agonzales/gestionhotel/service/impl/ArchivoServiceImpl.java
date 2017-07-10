package com.agonzales.gestionhotel.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.agonzales.gestionhotel.dao.ArchivoDao;
import com.agonzales.gestionhotel.domain.Archivo;
import com.agonzales.gestionhotel.service.ArchivoService;
import com.agonzales.gestionhotel.service.UsuarioService;
import com.agonzales.gestionhotel.util.Util;

@Service("ArchivoService")
public class ArchivoServiceImpl implements ArchivoService{
	
	@Autowired
	private ArchivoDao archivoDAO;

	@Autowired
	private UsuarioService usuarioService;

	@Transactional
	public Archivo guardar(Archivo archivo){

		archivo = archivoDAO.guardar(archivo, usuarioService.getUID());

		return archivo;
	}
	
	public Archivo get(Integer id){
		return archivoDAO.get(id);
	}

	@Transactional
	public void eliminar(Integer[] ids){
		for(Integer id : ids){
			Archivo archivo = archivoDAO.get(id);
			Util.eliminarArchivo(archivo.getNombre());
			archivoDAO.eliminar(archivo);
		}
	}

	@Transactional
	public Archivo guardarArchivoYOtorgarNormbreConPrefijo(HttpServletRequest request, MultipartFile file, String prefijoArchivo){
		Archivo archivo =  new Archivo();
		String nombreImagen = Util.otorgarNombreImagen(request, file, prefijoArchivo);
		archivo.setNombre(nombreImagen);
		guardar(archivo);			
		return archivo;
	}
}
