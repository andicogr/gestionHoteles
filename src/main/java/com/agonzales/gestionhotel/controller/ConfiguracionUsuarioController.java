package com.agonzales.gestionhotel.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agonzales.gestionhotel.domain.Compania;
import com.agonzales.gestionhotel.domain.Rol;
import com.agonzales.gestionhotel.domain.Usuario;
import com.agonzales.gestionhotel.service.AccesoCompaniaRolService;
import com.agonzales.gestionhotel.service.UsuarioRolService;
import com.agonzales.gestionhotel.service.UsuarioService;
import com.agonzales.gestionhotel.util.Constantes;

@Controller
public class ConfiguracionUsuarioController {

	private static final Logger log = LoggerFactory.getLogger(ConfiguracionUsuarioController.class);

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private AccesoCompaniaRolService accesoCompaniaRolService;

	@Autowired
	private UsuarioRolService usuarioRolService;

	@RequestMapping(value = "/configuracionUsuario/ver", method = RequestMethod.GET)
	public String ver(HttpSession session, Model model) {
		log.info("[ConfiguracionUsuarioController] - [ver]");
		Integer idUsuarioSession = (Integer) session.getAttribute(Constantes.USUARIO_SESSION_ID);
		Usuario usuarioSession = usuarioService.get(idUsuarioSession);
		List<Rol> listaRolesActivos = usuarioRolService.obtenerRolesActivosPorUsuario(idUsuarioSession);
		model.addAttribute("listaRolesActivos", listaRolesActivos);
		model.addAttribute("multiCompania", session.getAttribute(Constantes.MULTICOMPANIA_ACTIVADO));
		model.addAttribute("usuarioSession", usuarioSession);
		return "configuracionUsuario";
	}

	@RequestMapping(value = "/configuracionUsuario/obtenerCompaniasPorRol")
	public @ResponseBody List<Compania> obtenerCompaniasPorRol(HttpSession session, Model model, Integer idRol){
		log.info("[ConfiguracionUsuarioController] - [obtenerCompaniasPorRol]");
		List<Compania> listaCompaniasPorRol = accesoCompaniaRolService.listaCompaniasActivasPorRolParaConfiguracionUsuario(idRol);
		return listaCompaniasPorRol;
	}

}
