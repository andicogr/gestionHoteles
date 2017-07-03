package com.agonzales.gestionhotel.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping("/login")
	public String login(Model model, String message) {
		log.info("[LoginController] - [login]");
		//model.addAttribute("message", message);
		return "login";
	}
	
	@RequestMapping(value = "/denied")
 	public String denied() {
		return "denied";
	}
	
	@RequestMapping(value = "/login/failure")
 	public String loginFailure(Model model, Integer op) {
		log.info("[LoginController] - [loginFailure]");
		//String message = "usuarioClaveIncorrectos";
		//model.addAttribute("message", message);
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/logout/success")
 	public String logoutSuccess(Model model) {
		log.info("[LoginController] - [logoutSuccess]");
		//String message = "sesionCerrada";
		//model.addAttribute("message", message);
		return "redirect:/login";
	}

}
