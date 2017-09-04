package com.agonzales.gestionhotel.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agonzales.gestionhotel.domain.AjustesConfiguracion;
import com.agonzales.gestionhotel.service.AjustesConfiguracionService;

@Controller
@RequestMapping("/ajustesConfiguracion")
public class AjustesConfiguracionController {

	@Autowired
	private AjustesConfiguracionService ajustesConfiguracionService;

	@RequestMapping(value="/ver")
	public String Ver(Model model){
		AjustesConfiguracion ajustesConfig = ajustesConfiguracionService.obtenerAjustesDeConfiguracion();
		model.addAttribute("ajustesConfig", ajustesConfig);

		return "ajustesDeConfiguracion";
	}
	
	@RequestMapping(value="/guardar", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> guardar(Model model, AjustesConfiguracion ajustesConfig){
		Map<String, Object> retorno = ajustesConfiguracionService.guardar(ajustesConfig);
		return retorno;
	}
	
	@RequestMapping(value="/actualizar", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> actualizar(Model model, AjustesConfiguracion ajustesConfig){
		Map<String, Object> retorno = ajustesConfiguracionService.actualizar(ajustesConfig);
		return retorno;
	}
}
