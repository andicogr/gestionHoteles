package com.agonzales.gestionhotel.controller.mantenimiento;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.agonzales.gestionhotel.service.CompaniaService;
import com.agonzales.gestionhotel.service.UsuarioService;

@Controller
@RequestMapping("/mantenimiento/usuario")
public class MantenimientoUsuarioController {
	
	private static final Logger logger = LoggerFactory.getLogger(MantenimientoUsuarioController.class);

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private CompaniaService companiaService;

	@RequestMapping(value="/listar")
	public String usuarioListar(){
		logger.info("[MantenimientoUsuarioController] - method: usuarioListar");
		return "mantenimiento/usuario/listarUsuarios";
	}

	@RequestMapping(value="/listaJson")
	@ResponseBody
	public  Map<String, Object> usuarioListaJson(PaginacionDTO paginacion){
		logger.info("[MantenimientoUsuarioController] - method: usuarioListaJson");
		
		Map<String, Object> datos = usuarioService.listarJson(paginacion);

		return datos;
	}

	@RequestMapping(value="/ver")
	public String usuarioVer(Model model, Integer id){
		logger.info("[MantenimientoUsuarioController] - method: usuarioVer - id: " + id);
		model.addAttribute("estadosDeUsuario", usuarioService.obtenerEstadosDeUsuario());
		//TODO la lista de companias debe cargarse en cache para estar diponible siempre
		model.addAttribute("listaDeCompanias", companiaService.listarTodos());
		if(id != null){
			Usuario usuario = usuarioService.get(id);
			model.addAttribute("usuario", usuario);
			model.addAttribute("nombreMostrar", usuario.getUsuario());
		}
		return "mantenimiento/usuario/verUsuario";
	}

	@RequestMapping(value="/guardar", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> usuarioGuardar(HttpServletRequest request, Model model, Usuario usuario){
		logger.info("[MantenimientoUsuarioController] - method: usuarioGuardar");
		Map<String, Object> retorno = usuarioService.guardar(usuario);
		return retorno;
	}

	@RequestMapping(value="/eliminar")
	@ResponseBody
	public Map<String, Object> usuarioEliminar(Model model, Integer[] ids){
		logger.info("[MantenimientoUsuarioController] - method: usuarioEliminar - ids: " + ids);
		Map<String, Object> retorno = usuarioService.eliminar(ids);
		return retorno;
	}
}
