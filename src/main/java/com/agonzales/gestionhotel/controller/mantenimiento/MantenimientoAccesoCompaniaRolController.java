package com.agonzales.gestionhotel.controller.mantenimiento;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agonzales.gestionhotel.domain.AccesoCompaniaRol;
import com.agonzales.gestionhotel.domain.Compania;
import com.agonzales.gestionhotel.service.AccesoCompaniaRolService;
import com.agonzales.gestionhotel.service.CompaniaService;

@Controller
@RequestMapping("/mantenimiento/accesocompaniarol")
public class MantenimientoAccesoCompaniaRolController {
	
	private static final Logger logger = LoggerFactory.getLogger(MantenimientoAccesoCompaniaRolController.class);
	
	@Autowired
	private AccesoCompaniaRolService accesoCompaniaRolService;
	
	@Autowired
	private CompaniaService companiaService;

	@RequestMapping(value="/ver")
	public String ver(Model model, Integer id, Integer idRol){
		logger.info("[MantenimientoAccesoCompaniaRolController] - method: ver - id: " + id);
		model.addAttribute("idRol", idRol);
		List<Compania> listaCompaniasActivas = companiaService.listarCompaniasActivasSinRepetirPorRol(idRol);
		if(id != null){
			AccesoCompaniaRol accesoCompaniaRol = accesoCompaniaRolService.get(id);
			model.addAttribute("accesoCompaniaRol", accesoCompaniaRol);
			model.addAttribute("nombreMostrar", accesoCompaniaRol.getNombreCompania());
			listaCompaniasActivas.add(accesoCompaniaRol.getCompania());
		}

		model.addAttribute("listaCompaniasActivas", listaCompaniasActivas);

		return "mantenimiento/accesocompaniarol/verAccesoCompaniaRol";
	}

	@RequestMapping(value="/guardar", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> guardar(Model model, AccesoCompaniaRol accesoCompaniaRol){
		logger.info("[MantenimientoAccesoCompaniaRolController] - method: guardar");
		Map<String, Object> retorno = accesoCompaniaRolService.guardar(accesoCompaniaRol);
		return retorno;
	}
	
	@RequestMapping(value="/actualizar", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> actualizar(Model model, AccesoCompaniaRol accesoCompaniaRol){
		logger.info("[MantenimientoAccesoCompaniaRolController] - method: actualizar");
		Map<String, Object> retorno = accesoCompaniaRolService.actualizar(accesoCompaniaRol);
		return retorno;
	}

	@RequestMapping(value="/eliminar")
	@ResponseBody
	public Map<String, Object> eliminar(Model model, Integer[] ids){
		logger.info("[MantenimientoAccesoCompaniaRolController] - method: eliminar - ids: " + ids);
		Map<String, Object> retorno = accesoCompaniaRolService.eliminar(ids);
		return retorno;
	}

}
