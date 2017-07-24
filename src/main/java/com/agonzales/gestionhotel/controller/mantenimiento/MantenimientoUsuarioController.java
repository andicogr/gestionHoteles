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

import com.agonzales.gestionhotel.domain.Usuario;
import com.agonzales.gestionhotel.dto.PaginacionDTO;
import com.agonzales.gestionhotel.service.UsuarioService;

@Controller
@RequestMapping("/mantenimiento/usuario")
public class MantenimientoUsuarioController {
	
	private static final Logger logger = LoggerFactory.getLogger(MantenimientoUsuarioController.class);

	@Autowired
	private UsuarioService usuarioService;
	

	@RequestMapping(value="/listar")
	public String usuarioListar(HttpSession session, Model model){
		logger.info("[MantenimientoUsuarioController] - method: usuarioListar");
		Boolean isMultiCompaniaActivado = (Boolean) session.getAttribute("isMultiCompaniaActivado");
		model.addAttribute("isMultiCompaniaActivado", isMultiCompaniaActivado);
		return "mantenimiento/usuario/listarUsuarios";
	}

	@RequestMapping(value="/listaJson")
	@ResponseBody
	public  Map<String, Object> usuarioListaJson(HttpSession session, PaginacionDTO paginacion){
		logger.info("[MantenimientoUsuarioController] - method: usuarioListaJson");
		Boolean isMultiCompaniaActivado = (Boolean) session.getAttribute("isMultiCompaniaActivado");
		Map<String, Object> datos = usuarioService.listarJson(paginacion, isMultiCompaniaActivado);

		return datos;
	}

	@RequestMapping(value="/ver")
	public String usuarioVer(HttpSession session, Model model, Integer id){
		logger.info("[MantenimientoUsuarioController] - method: usuarioVer - id: " + id);
		model.addAttribute("listaDeCompanias", session.getAttribute("listaDeCompanias"));
		if(id != null){
			Usuario usuario = usuarioService.get(id);
			model.addAttribute("usuario", usuario);
			model.addAttribute("nombreMostrar", usuario.getUsername());
		}
		return "mantenimiento/usuario/verUsuario";
	}

	@RequestMapping(value="/guardar", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> usuarioGuardar(Model model, Usuario usuario){
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
