package com.agonzales.gestionhotel.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agonzales.gestionhotel.dao.CompaniaDao;
import com.agonzales.gestionhotel.domain.Compania;
import com.agonzales.gestionhotel.dto.PaginacionDTO;
import com.agonzales.gestionhotel.service.CompaniaService;
import com.agonzales.gestionhotel.service.UsuarioService;
import com.agonzales.gestionhotel.util.Util;

@Service("CompaniaService")
public class CompaniaServiceImpl implements CompaniaService{

	@Autowired
	private CompaniaDao companiaDAO;
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	public Map<String, Object> listarJson(PaginacionDTO paginacion){

		if(paginacion.getiDisplayLength()==null){
			return null;
		}

		Map<String, Object> columnas = new HashMap<String, Object>();
		
		columnas.put("1", "a.razonSocial");
		columnas.put("2", "a.ruc");
		columnas.put("3", "a.telefono");
		columnas.put("4", "a.correoContacto");

		List<Compania> listaJson = companiaDAO.listarJson(paginacion, columnas);
		Number total = companiaDAO.totalListaJson();

		Map<String, Object> datos = new HashMap<String, Object>();

		datos.put("sEcho", paginacion.getsEcho());
		datos.put("iTotalRecords", total);
		datos.put("iTotalDisplayRecords", total);

		List<String[]> listas = new ArrayList<String[]>();
		
		for (Compania compania: listaJson) {
			
			String checkbox ="<input type=\"checkbox\" name=\"checkBoxRow\" class=\"flat\" value=\"" + compania.getId() + "\"/>";

			String[] aaDato = {
						checkbox,
						compania.getRazonSocial(),
						compania.getRuc(),
						compania.getTelefono(),
						compania.getCorreoContacto()
					};
			listas.add(aaDato);
		}
		
		datos.put("aaData", listas);
		
		return datos;
	}

	@Transactional
	public Map<String, Object> guardar(Compania compania){
		Map<String, Object> retorno = new HashMap<String, Object>();
		Map<String, Object> notifiaccion = null;
		String texto = "";

		if(companiaDAO.isUniqueValue("razonSocial", compania.getRazonSocial(), compania.getId())){
			notifiaccion = Util.crearNotificacion("error", "Error", "La Razon Social ya esta registrada en el sistema.");
			retorno.put("notificacion", notifiaccion);
			retorno.put("estado", false);
			return retorno;
		}

		if(companiaDAO.isUniqueValue("ruc", compania.getRuc(), compania.getId())){
			notifiaccion = Util.crearNotificacion("error", "Error", "El RUC ya esta registrado en el sistema.");
			retorno.put("notificacion", notifiaccion);
			retorno.put("estado", false);
			return retorno;
		}


		if(compania.getId() != null){
			Compania actual = companiaDAO.get(compania.getId());
			actual.setCorreoContacto(compania.getCorreoContacto());
			actual.setDireccion(compania.getDireccion());
			actual.setLogo(compania.getLogo());
			actual.setRazonSocial(compania.getRazonSocial());
			actual.setRuc(compania.getRuc());
			actual.setTelefono(compania.getTelefono());
			compania = actual;
		}
		
		if(compania.getId() == null){
			texto = "Los datos se registraron correctamente.";
		}else{
			texto = "Los datos se actualizaron correctamente.";
		}
		notifiaccion = Util.crearNotificacion("success", "Correcto", texto);

		companiaDAO.guardar(compania, usuarioService.getUID());

		retorno.put("notificacion", notifiaccion);
		retorno.put("id", compania.getId());
		retorno.put("estado", true);

		return retorno;
	}
	
	public Compania get(Integer id){
		return companiaDAO.get(id);
	}

	@Transactional
	public Map<String, Object> eliminar(Integer[] ids){
		Map<String, Object> retorno = new HashMap<String, Object>();
		Map<String, Object> notifiaccion = null;
		boolean estado = false;
		String texto = "El registro se elimino correctamente";
		if(ids.length > 1){
			texto = "Los registros se eliminaron correctamente";
		}
		for(Integer id : ids){
			Compania compania = companiaDAO.get(id);
			estado = companiaDAO.eliminar(compania);
		}
		if(estado){
			notifiaccion = Util.crearNotificacion("info", "Informacion", texto);
		}else{
			notifiaccion = Util.crearNotificacion("error", "Error", 
					"Ocurrio un error mientras se eliminaba el registro, "
					+ "por favor comuniquese con el administrador del sistema.", 5000);
		}

		retorno.put("notificacion", notifiaccion);
		retorno.put("estado", estado);
		return retorno;
	}
	
	public List<Compania> listarTodos(){
		return companiaDAO.getTodos();
	}
	
}