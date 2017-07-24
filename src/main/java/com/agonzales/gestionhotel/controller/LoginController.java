package com.agonzales.gestionhotel.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.agonzales.gestionhotel.service.CompaniaService;
import com.agonzales.gestionhotel.service.UsuarioService;

@Controller
public class LoginController {

	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private CompaniaService companiService;
	
	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping("/login")
	public String login(Model model) {
		log.info("[LoginController] - [login]");
		return "login";
	}
	
	@RequestMapping(value = "/denied")
 	public String denied() {
		log.info("[LoginController] - [denied]");
		return "denied";
	}

	@RequestMapping(value = "/login/failure")
 	public String loginFailure(Model model) {
		log.info("[LoginController] - [loginFailure]");
		return "redirect:/login";
	}

	@RequestMapping(value = "/logout/success")
 	public String logoutSuccess(Model model) {
		log.info("[LoginController] - [logoutSuccess]");
		return "redirect:/login";
	}

	@RequestMapping(value = "/configuracionUsuario", method = RequestMethod.GET)
	public String configuracionUsiaro(HttpSession session, Model model) {
		log.info("[LoginController] - [configuracionUsiaro]");
		model.addAttribute("listaDeUsuarioRolesActivos", usuarioService.obtenerUsuarioRolesPorUsuario(usuarioService.getUID()));
		model.addAttribute("listaDeCompanias", session.getAttribute("listaDeCompanias"));
		return "configuracionUsuario";
	}

	@RequestMapping(value = "/")
 	public String principal(HttpSession session) {
		log.info("[LoginController] - [principal]");
		session.setAttribute("isMultiCompaniaActivado", companiService.isMultiCompaniaActivado());
		session.setAttribute("listaDeCompanias", companiService.listarTodos());
		return "principal";
	}

}
