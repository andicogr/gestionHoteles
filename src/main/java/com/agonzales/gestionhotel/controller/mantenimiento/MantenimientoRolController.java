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
	public String listar(HttpSession session, Model model){
		logger.info("[MantenimientoRolController] - method: listar");
		return "mantenimiento/rol/listarRoles";
	}

	@RequestMapping(value="/listaJson")
	@ResponseBody
	public  Map<String, Object> listaJson(HttpSession session, PaginacionDTO paginacion){
		logger.info("[MantenimientoRolController] - method: listaJson");
		Map<String, Object> datos = rolService.listarJson(paginacion);
		return datos;
	}

	@RequestMapping(value="/ver")
	public String ver(HttpSession session, Model model, Integer id, String tab){
		logger.info("[MantenimientoRolController] - method: ver - id: " + id);
		if(id != null){
			Rol rol = rolService.get(id);
			model.addAttribute("rol", rol);
			model.addAttribute("nombreMostrar", rol.getNombre());
		}
		model.addAttribute("tab", tab == null ? "privilegios" : tab);
		return "mantenimiento/rol/verRol";
	}

	@RequestMapping(value="/guardar", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> guardar(Model model, Rol rol){
		logger.info("[MantenimientoRolController] - method: guardar");
		Map<String, Object> retorno = rolService.guardar(rol);
		return retorno;
	}
	
	@RequestMapping(value="/actualizar", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> actualizar(Model model, Rol rol){
		logger.info("[MantenimientoRolController] - method: actualizar");
		Map<String, Object> retorno = rolService.actualizar(rol);
		return retorno;
	}

	@RequestMapping(value="/eliminar")
	@ResponseBody
	public Map<String, Object> eliminar(Model model, Integer[] ids){
		logger.info("[MantenimientoRolController] - method: eliminar - ids: " + ids);
		Map<String, Object> retorno = rolService.eliminar(ids);
		return retorno;
	}

	@RequestMapping(value="/actualizarPrivilegios")
	@ResponseBody
	public Map<String, Object> actualizarPrivilegios(Integer idRol, Integer[] idPrvivilegios){
		logger.info("[MantenimientoRolController] - method: actualizarPrivilegios - idRol: " + idRol + " idPrvivilegios: " + idPrvivilegios);
		return rolService.actualizarPrivilegios(idRol, idPrvivilegios);
	}

}
