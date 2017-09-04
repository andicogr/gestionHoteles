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

import com.agonzales.gestionhotel.domain.Archivo;
import com.agonzales.gestionhotel.domain.Compania;
import com.agonzales.gestionhotel.dto.PaginacionDTO;
import com.agonzales.gestionhotel.service.ArchivoService;
import com.agonzales.gestionhotel.service.CompaniaService;
import com.agonzales.gestionhotel.util.Constantes;

@Controller
@RequestMapping("/mantenimiento/compania")
public class MantenimientoCompaniaController {

	private static final Logger logger = LoggerFactory.getLogger(MantenimientoCompaniaController.class);

	@Autowired
	private CompaniaService companiaService;
	
	@Autowired
	private ArchivoService archivoService;

	@RequestMapping(value="/listar")
	public String companiaListar(){
		logger.info("[MantenimientoController] - method: companiaListar");
		return "mantenimiento/compania/listarCompanias";
	}

	@RequestMapping(value="/listaJson")
	@ResponseBody
	public  Map<String, Object> companiaListaJson(PaginacionDTO paginacion){
		logger.info("[MantenimientoController] - method: companiaListaJson");
		
		Map<String, Object> datos = companiaService.listarJson(paginacion);

		return datos;
	}

	@RequestMapping(value="/ver")
	public String companiaVer(Model model, Integer id){
		logger.info("[MantenimientoController] - method: companiaVer - id: " + id);
		if(id != null){
			Compania compania = companiaService.get(id);
			model.addAttribute("compania", compania);
			model.addAttribute("nombreMostrar", compania.getRazonSocial());
		}
		model.addAttribute("pathImg", Constantes.PATH_DEFAULT_IMAGEN);

		return "mantenimiento/compania/verCompania";
	}

	@RequestMapping(value="/guardar", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> guardar(HttpServletRequest request, Model model, Compania compania){
		logger.info("[MantenimientoController] - method: companiaGuardar");
		if(compania.isGuardarImagen() && compania.existeLogo()){
			Archivo archivo = archivoService.guardarArchivoYOtorgarNormbreConPrefijo(request, compania.getLogo(), Constantes.PREFIJO_IMG_LOGO);
			compania.setArchivo(archivo);
		}

		Map<String, Object> retorno = companiaService.guardar(compania);
		return retorno;
	}
	
	@RequestMapping(value="/actualizar", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> actualizar(HttpServletRequest request, Model model, Compania compania){
		logger.info("[MantenimientoController] - method: companiaGuardar");
		if(compania.isGuardarImagen() && compania.existeLogo()){
			Archivo archivo = archivoService.guardarArchivoYOtorgarNormbreConPrefijo(request, compania.getLogo(), Constantes.PREFIJO_IMG_LOGO);
			compania.setArchivo(archivo);
		}

		Map<String, Object> retorno = companiaService.guardar(compania);
		return retorno;
	}

	@RequestMapping(value="/eliminar")
	@ResponseBody
	public Map<String, Object> companiaEliminar(Model model, Integer[] ids){
		logger.info("[MantenimientoController] - method: companiaEliminar - ids: " + ids);
		Map<String, Object> retorno = companiaService.eliminar(ids);
		return retorno;
	}

}
