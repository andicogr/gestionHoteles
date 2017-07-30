package com.agonzales.gestionhotel.controller.mantenimiento;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agonzales.gestionhotel.domain.Privilegio;
import com.agonzales.gestionhotel.service.PrivilegioService;

@Controller
@RequestMapping("/mantenimiento/privilegio")
public class MantenimientoPrivilegioController {

	private static final Logger logger = LoggerFactory.getLogger(MantenimientoPrivilegioController.class);

	@Autowired
	private PrivilegioService privilegioService;

	@RequestMapping(value="/listar")
	public String privilegioListar(HttpSession session, Model model){
		logger.info("[MantenimientoPrivilegioController] - method: privilegioListar");
		//return "mantenimiento/privilegio/listarPrivilegios";
		return "mantenimiento/privilegio/treePrivilegio";
	}
	
	@RequestMapping(value="/listaPrivilegiosPadre", method = RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> listaPrivilegiosPadre(Integer idRol){
		logger.info("[MantenimientoPrivilegioController] - method: listaPrivilegiosPadre");
		List<Map<String, Object>> listaDeArbolDePrivilegios = privilegioService.obtenerListaDePrivilegiosPadres(idRol);
		return listaDeArbolDePrivilegios;
	}

	@RequestMapping(value="/ver")
	public String privilegioVer(HttpSession session, Model model, Integer id){
		logger.info("[MantenimientoPrivilegioController] - method: privilegioVer - id: " + id);
		if(id != null){
			Privilegio privilegio = privilegioService.get(id);
			model.addAttribute("privilegio", privilegio);
			model.addAttribute("nombreMostrar", privilegio.getNombre());
		}
		return "mantenimiento/privilegio/verPrivilegio";
	}

}
