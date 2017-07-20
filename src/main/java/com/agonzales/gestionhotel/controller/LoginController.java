package com.agonzales.gestionhotel.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.agonzales.gestionhotel.domain.Compania;
import com.agonzales.gestionhotel.service.CompaniaService;

@Controller
public class LoginController {

	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private CompaniaService companiaService;


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
	public String configuracionUsiaro(Model model) {
		log.info("[LoginController] - [configuracionUsiaro]");
		List<Compania> listaCompanias = companiaService.listarTodos();
		model.addAttribute("listaCompanias", listaCompanias);
		return "configuracionUsuario";
	}

	@RequestMapping(value = "/")
 	public String principal(HttpSession session) {
		log.info("[LoginController] - [principal]");
		session.setAttribute("isMultiCompaniaActivado", companiaService.isMultiCompaniaActivado());
		return "principal";
	}


}
