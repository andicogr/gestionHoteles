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

import com.agonzales.gestionhotel.domain.Rol;
import com.agonzales.gestionhotel.domain.UsuarioRol;
import com.agonzales.gestionhotel.service.RolService;
import com.agonzales.gestionhotel.service.UsuarioRolService;

@Controller
@RequestMapping("/mantenimiento/usuariorol")
public class MantenimientoUsuarioRolController {

	private static final Logger logger = LoggerFactory.getLogger(MantenimientoUsuarioRolController.class);

	@Autowired
	private UsuarioRolService usuarioRolService;

	@Autowired
	private RolService rolService;

	@RequestMapping(value="/ver")
	public String ver(Model model, Integer id, Integer idUsuario){
		logger.info("[MantenimientoUsuarioController] - method: ver - id: " + id);
		model.addAttribute("idUsuario", idUsuario);
		List<Rol> listaRolesActivos = rolService.listarRolesActivosSinRepetirPorUsuario(idUsuario);
		if(id != null){
			UsuarioRol usuarioRol = usuarioRolService.get(id);
			model.addAttribute("usuarioRol", usuarioRol);
			model.addAttribute("nombreMostrar", usuarioRol.getNombreRol());
			listaRolesActivos.add(usuarioRol.getRol());
		}

		model.addAttribute("listaRolesActivos", listaRolesActivos);

		return "mantenimiento/usuariorol/verUsuarioRol";
	}

	@RequestMapping(value="/guardar", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> guardar(Model model, UsuarioRol usuarioRol){
		logger.info("[MantenimientoUsuarioController] - method: guardar");
		Map<String, Object> retorno = usuarioRolService.guardar(usuarioRol);
		return retorno;
	}
	
	@RequestMapping(value="/actualizar", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> actualizar(Model model, UsuarioRol usuarioRol){
		logger.info("[MantenimientoUsuarioController] - method: guardar");
		Map<String, Object> retorno = usuarioRolService.guardar(usuarioRol);
		return retorno;
	}

	@RequestMapping(value="/eliminar")
	@ResponseBody
	public Map<String, Object> eliminar(Model model, Integer[] ids){
		logger.info("[MantenimientoUsuarioController] - method: eliminar - ids: " + ids);
		Map<String, Object> retorno = usuarioRolService.eliminar(ids);
		return retorno;
	}

}
