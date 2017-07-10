package com.agonzales.gestionhotel.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agonzales.gestionhotel.domain.Archivo;
import com.agonzales.gestionhotel.service.ArchivoService;
import com.agonzales.gestionhotel.util.Config;
import com.agonzales.gestionhotel.util.Constantes;

@Controller
@RequestMapping("/imagenes")
public class ImageController{
	
	private static final Logger logger = LoggerFactory.getLogger(ImageController.class);
	
	@Autowired
	private ArchivoService archivoService;
	
	@RequestMapping(value = "/getImage/{imageId}")
	@ResponseBody
	public byte[] getImage(@PathVariable Integer imageId, HttpServletRequest request){
		logger.info("[ImageController] - method: getImage - id: " + imageId);
		try {
			Archivo archivo = archivoService.get(imageId);
			String rpath = Config.getPropiedad(Constantes.PROPERTIES_IMAGENES_PATH) + File.separator + archivo.getNombre();
			Path path = Paths.get(rpath);
			byte[] data = null;
			data = Files.readAllBytes(path);
			return data;
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	@RequestMapping(value="/obtenerDefaultImagenPath")
	@ResponseBody
	public String obtenerPathDefaultImagen(){
		logger.info("[ImageController] - method: obtenerPathDefaultImagen");
		return Constantes.PATH_DEFAULT_IMAGEN;
	}

}
