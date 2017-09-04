package com.agonzales.gestionhotel.controller.mantenimiento;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agonzales.gestionhotel.domain.Usuario;
import com.agonzales.gestionhotel.dto.PaginacionDTO;
import com.agonzales.gestionhotel.service.UsuarioService;
import com.agonzales.gestionhotel.util.Constantes;
import com.agonzales.gestionhotel.util.VariablesSession;

@Controller
@RequestMapping("/mantenimiento/usuario")
public class MantenimientoUsuarioController {
	
	private static final Logger logger = LoggerFactory.getLogger(MantenimientoUsuarioController.class);

	@Autowired
	private UsuarioService usuarioService;
	

	@RequestMapping(value="/listar")
	public String listar(Model model){
		logger.info("[MantenimientoUsuarioController] - method: listar");
		boolean multiCompania = (Boolean) VariablesSession.getAttribute(Constantes.MULTICOMPANIA_ACTIVADO);
		model.addAttribute("multiCompania", multiCompania);
		return "mantenimiento/usuario/listarUsuarios";
	}

	@RequestMapping(value="/listaJson")
	@ResponseBody
	public  Map<String, Object> listaJson(PaginacionDTO paginacion){
		logger.info("[MantenimientoUsuarioController] - method: listaJson");
		Map<String, Object> datos = usuarioService.listarJson(paginacion);

		return datos;
	}

	@RequestMapping(value="/ver")
	public String ver(Model model, Integer id){
		logger.info("[MantenimientoUsuarioController] - method: ver - id: " + id);
		model.addAttribute("listaDeCompanias", VariablesSession.getAttribute(Constantes.LISTA_COMPANIAS_ACTIVAS));
		if(id != null){
			Usuario usuario = usuarioService.get(id);
			model.addAttribute("usuario", usuario);
			model.addAttribute("nombreMostrar", usuario.getUsername());
		}
		return "mantenimiento/usuario/verUsuario";
	}

	@RequestMapping(value="/guardar", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> guardar(Model model, Usuario usuario){
		logger.info("[MantenimientoUsuarioController] - method: guardar");
		Map<String, Object> retorno = usuarioService.guardar(usuario);
		return retorno;
	}

	@RequestMapping(value="/actualizar", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> actualizar(Model model, Usuario usuario){
		logger.info("[MantenimientoUsuarioController] - method: actualizar");
		Map<String, Object> retorno = usuarioService.actualizar(usuario);
		return retorno;
	}

	@RequestMapping(value="/eliminar")
	@ResponseBody
	public Map<String, Object> eliminar(Model model, Integer[] ids){
		logger.info("[MantenimientoUsuarioController] - method: eliminar - ids: " + ids);
		Map<String, Object> retorno = usuarioService.eliminar(ids);
		return retorno;
	}

}
