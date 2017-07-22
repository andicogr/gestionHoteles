package com.agonzales.gestionhotel.controller.mantenimiento;

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
import com.agonzales.gestionhotel.dto.PaginacionDTO;
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
		return "mantenimiento/privilegio/listarPrivilegios";
	}

	@RequestMapping(value="/listaJson")
	@ResponseBody
	public  Map<String, Object> privilegioListaJson(HttpSession session, PaginacionDTO paginacion){
		logger.info("[MantenimientoPrivilegioController] - method: privilegioListaJson");
		Map<String, Object> datos = privilegioService.listarJson(paginacion);
		return datos;
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

	@RequestMapping(value="/guardar", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> privilegiioGuardar(Model model, Privilegio privilegio){
		logger.info("[MantenimientoPrivilegioController] - method: privilegiioGuardar");
		Map<String, Object> retorno = privilegioService.guardar(privilegio);
		return retorno;
	}

	@RequestMapping(value="/eliminar")
	@ResponseBody
	public Map<String, Object> privilegioEliminar(Model model, Integer[] ids){
		logger.info("[MantenimientoPrivilegioController] - method: privilegioEliminar - ids: " + ids);
		Map<String, Object> retorno = privilegioService.eliminar(ids);
		return retorno;
	}
}
