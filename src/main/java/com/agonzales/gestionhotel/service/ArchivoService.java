package com.agonzales.gestionhotel.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.agonzales.gestionhotel.domain.Archivo;

public interface ArchivoService {

	public Archivo guardar(Archivo archivo);

	public Archivo get(Integer id);
	
	public void eliminar(Integer[] ids);
	
	public Archivo guardarArchivoYOtorgarNormbreConPrefijo(HttpServletRequest request, MultipartFile file, String prefijoArchivo);

}
