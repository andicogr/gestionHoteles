package com.agonzales.gestionhotel.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.agonzales.gestionhotel.domain.Compania;
import com.agonzales.gestionhotel.dto.PaginacionDTO;
import com.agonzales.gestionhotel.service.CompaniaService;

@Controller
@RequestMapping("/mantenimiento")
public class MantenimientoController {

	private static final Logger logger = LoggerFactory.getLogger(MantenimientoController.class);
	
	@Autowired
	CompaniaService companiaService;
	
	@RequestMapping(value="/compania/listar")
	public String companiaListar(){
		logger.info("[MantenimientoController] - method: companiaListar");
		return "mantenimiento/compania/listar";
	}
	
	@RequestMapping(value="/compania/listaJson")
	public @ResponseBody Map<String, Object> companiaListaJson(PaginacionDTO paginacion){
		logger.info("[MantenimientoController] - method: companiaListaJson");
		
		Map<String, Object> datos = companiaService.listarJson(paginacion);

		return datos;
	}

	@RequestMapping(value="/compania/ver")
	public String companiaVer(Model model, Integer id){
		logger.info("[MantenimientoController] - method: companiaVer");
		if(id != null){
			Compania compania = companiaService.get(id);
			model.addAttribute("compania", compania);
			model.addAttribute("nombreMostrar", compania.getRazonSocial());
		}
		return "mantenimiento/compania/ver";
	}

	@RequestMapping(value="/compania/guardar", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> companiaGuardar(HttpServletRequest request, Model model, Compania compania, @RequestParam("logo1") MultipartFile logo){
		logger.info("[MantenimientoController] - method: companiaGuardar");
		Map<String, Object> retorno = companiaService.guardar(compania);
		return retorno;
	}
	
	@RequestMapping(value="/compania/eliminar")
	public @ResponseBody Map<String, Object> companiaEliminar(Model model, Integer[] ids){
		logger.info("[MantenimientoController] - method: companiaEliminar");
		Map<String, Object> retorno = companiaService.eliminar(ids);
		return retorno;
	}
}
