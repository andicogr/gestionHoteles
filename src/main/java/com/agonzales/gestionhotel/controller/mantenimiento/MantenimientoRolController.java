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

import com.agonzales.gestionhotel.domain.Rol;
import com.agonzales.gestionhotel.dto.PaginacionDTO;
import com.agonzales.gestionhotel.service.RolService;

@Controller
@RequestMapping("/mantenimiento/rol")
public class MantenimientoRolController {
	
	private static final Logger logger = LoggerFactory.getLogger(MantenimientoRolController.class);

	@Autowired
	private RolService rolService;

	@RequestMapping(value="/listar")
	public String rolListar(HttpSession session, Model model){
		logger.info("[MantenimientoRolController] - method: rolListar");
		return "mantenimiento/rol/listarRoles";
	}

	@RequestMapping(value="/listaJson")
	@ResponseBody
	public  Map<String, Object> rolListaJson(HttpSession session, PaginacionDTO paginacion){
		logger.info("[MantenimientoRolController] - method: rolListaJson");
		Map<String, Object> datos = rolService.listarJson(paginacion);
		return datos;
	}

	@RequestMapping(value="/ver")
	public String rolVer(HttpSession session, Model model, Integer id){
		logger.info("[MantenimientoRolController] - method: rolVer - id: " + id);
		if(id != null){
			Rol rol = rolService.get(id);
			model.addAttribute("rol", rol);
			model.addAttribute("nombreMostrar", rol.getNombre());
		}
		return "mantenimiento/rol/verRol";
	}

	@RequestMapping(value="/guardar", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> rolGuardar(Model model, Rol rol){
		logger.info("[MantenimientoRolController] - method: rolGuardar");
		Map<String, Object> retorno = rolService.guardar(rol);
		return retorno;
	}

	@RequestMapping(value="/eliminar")
	@ResponseBody
	public Map<String, Object> rolEliminar(Model model, Integer[] ids){
		logger.info("[MantenimientoRolController] - method: rolEliminar - ids: " + ids);
		Map<String, Object> retorno = rolService.eliminar(ids);
		return retorno;
	}

}
