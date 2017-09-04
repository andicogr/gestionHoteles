package com.agonzales.gestionhotel.controller;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agonzales.gestionhotel.domain.Compania;
import com.agonzales.gestionhotel.domain.Rol;
import com.agonzales.gestionhotel.domain.Usuario;
import com.agonzales.gestionhotel.service.AjustesConfiguracionService;
import com.agonzales.gestionhotel.service.CompaniaService;
import com.agonzales.gestionhotel.service.RolService;
import com.agonzales.gestionhotel.service.UsuarioService;
import com.agonzales.gestionhotel.util.Constantes;
import com.agonzales.gestionhotel.util.VariablesSession;

@Controller
public class LoginController {

	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private CompaniaService companiService;

	@Autowired
	private AjustesConfiguracionService ajustesConfiguracionService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private RolService rolService;

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

	@RequestMapping(value = "/")
 	public String principal(Integer idRol, Integer idCompania, boolean porDefecto) {
		log.info("[LoginController] - [principal]");

		//TODO mover a una clase apropiada para cargar toda esta informacion
		//TODO en un futuro debe estar en el scope de aplicacion
		Integer idUsuario = usuarioService.getUID();
		VariablesSession.setAttribute(Constantes.USUARIO_SESSION_ID, idUsuario);

		boolean multiCompania = ajustesConfiguracionService.isMultiCompaniaActivado();
		VariablesSession.setAttribute(Constantes.MULTICOMPANIA_ACTIVADO, multiCompania);

		List<Compania> listaDeCompanias = companiService.listarCompaniasActivas();
		VariablesSession.setAttribute(Constantes.LISTA_COMPANIAS_ACTIVAS, listaDeCompanias);

		if(idCompania != null){
			VariablesSession.setAttribute(Constantes.COMPANIA_SESSION_ID, idCompania);
		}else{
			VariablesSession.setAttribute(Constantes.COMPANIA_SESSION_ID, listaDeCompanias.get(0).getId());
		}

		Usuario usuario = usuarioService.get(idUsuario);
		if(idRol != null){
			Rol rol = rolService.get(idRol);
			VariablesSession.setAttribute(Constantes.ROL_SESSION_ID, idRol);
			Collection<? extends GrantedAuthority> authList = usuarioService.getAuthorities(rol);
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(),auth.getCredentials(),authList);
			SecurityContextHolder.getContext().setAuthentication(newAuth);
		}else{
			List<Rol> roles = usuario.getRolesActivos();
			if(!roles.isEmpty()){
				VariablesSession.setAttribute(Constantes.ROL_SESSION_ID, roles.get(0).getId());
			}

		}

		if(porDefecto){
			usuario.setRolPorDefecto(new Rol(idRol));
			usuario.setCompaniaPorDefecto(new Compania(idCompania));
			usuarioService.actualizar(usuario);
		}else if(!usuario.getFlagPorDefecto()){
			usuario.setRolPorDefecto(null);
			usuario.setCompaniaPorDefecto(null);
			usuarioService.actualizar(usuario);
		}

		

		return "principal";
	}

}
