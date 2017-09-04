package com.agonzales.gestionhotel.domain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.agonzales.gestionhotel.util.Constantes;
import com.agonzales.gestionhotel.util.Entidad;
import com.agonzales.gestionhotel.util.EntidadBase;

@Entity
@Table(name="usuario")
public class Usuario extends EntidadBase implements Entidad{

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="usuario_id_seq_generator",sequenceName="usuario_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="usuario_id_seq_generator")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=20, nullable=false)
	private String username;

	@Column(length=20, nullable=false)
	private String password;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="usuario")
	private List<UsuarioRol> roles;
	
	private boolean activo;

	private boolean bloqueado;
	
	@Column(name="expirar_usuario")
	private boolean expirarUsuario;
	
	@Column(name="fecha_expirancion_usuario")
	private Date fechaExpiracionUsuario;
	
	@Column(name="numero_intentos_fallidos")
	private Integer numeroIntentosFallidos;

	@ManyToOne
	@JoinColumn(name="rol_id_por_defecto")
	private Rol rolPorDefecto;

	@ManyToOne
	@JoinColumn(name="compania_id_por_defecto")
	private Compania companiaPorDefecto;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<UsuarioRol> getRoles() {
		return roles;
	}

	public void setRoles(List<UsuarioRol> roles) {
		this.roles = roles;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public boolean isBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	public boolean isExpirarUsuario() {
		return expirarUsuario;
	}

	public void setExpirarUsuario(boolean expirarUsuario) {
		this.expirarUsuario = expirarUsuario;
	}

	public Date getFechaExpiracionUsuario() {
		return fechaExpiracionUsuario;
	}

	public void setFechaExpiracionUsuario(Date fechaExpiracionUsuario) {
		this.fechaExpiracionUsuario = fechaExpiracionUsuario;
	}

	public Integer getNumeroIntentosFallidos() {
		return numeroIntentosFallidos;
	}

	public void setNumeroIntentosFallidos(Integer numeroIntentosFallidos) {
		this.numeroIntentosFallidos = numeroIntentosFallidos;
	}

	public Rol getRolPorDefecto() {
		return rolPorDefecto;
	}

	public void setRolPorDefecto(Rol rolPorDefecto) {
		this.rolPorDefecto = rolPorDefecto;
	}

	public Compania getCompaniaPorDefecto() {
		return companiaPorDefecto;
	}

	public void setCompaniaPorDefecto(Compania companiaPorDefecto) {
		this.companiaPorDefecto = companiaPorDefecto;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNombreCompania(){
		if(getCompania() != null){
			return getCompania().getRazonSocial();
		}
		return "";
	}

	public boolean isRolesActivos(){
		for(UsuarioRol rol : getRoles()){
			if(rol.isUsuarioRolYRolActivo()){
				return true;
			}
		}
		return false;
	}

	public boolean isUsuarioExpirado(){
		if(isExpirarUsuario() && getFechaExpiracionUsuario().compareTo(new Date()) > 0){
			return true;
		}
		return false;
	}
	
	public Rol getRolUsuario(){
		if(getRolPorDefecto() != null){
			return getRolPorDefecto();
		}else{
			List<Rol> roles = this.getRolesActivos();
			if(roles.isEmpty()){
				return null;
			}else{
				return roles.get(0);
			}
		}
	}

	public List<Rol> getRolesActivos(){
		List<Rol> listaRolesActivos = new ArrayList<Rol>();
		for(UsuarioRol rol : getRoles()){
			if(rol.isUsuarioRolYRolActivo()){
				listaRolesActivos.add(rol.getRol());
			}
		}
		return listaRolesActivos;
	}
	
	public Date getFechaExpiracionUsuarioValidandoNull(){
		if(getFechaExpiracionUsuario() != null){
			return getFechaExpiracionUsuario();
		}
		return new Date();
	}
	
	public String getFechaExpiracionUsuarioConFormato(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constantes.FORMATO_FECHA_DDMMYYYY);
		String fechaFormateada = simpleDateFormat.format(getFechaExpiracionUsuarioValidandoNull());
		return fechaFormateada;
	}
	
	public void incrementarNumeroDeIntentosFallidos(){
		setNumeroIntentosFallidos(getNumeroIntentosFallidos() + 1);
		bloquearUsuarioPorNumeroDeIntentosFallidos();
	}
	
	public void bloquearUsuarioPorNumeroDeIntentosFallidos(){
		if(getNumeroIntentosFallidos() == Constantes.INTENTOS_FALLIDOS_MAXIMOS){
			setBloqueado(true);
		}
	}
	
	public Integer getRolPorDefectoId(){
		if(getRolPorDefecto() != null){
			return getRolPorDefecto().getId();
		}
		return null;
	}
	
	public Integer getCompaniaPorDefectoId(){
		if(getCompaniaPorDefecto() != null){
			return getCompaniaPorDefecto().getId();
		}
		return null;
	}
	
	public boolean getFlagPorDefecto(){
		if(getCompaniaPorDefecto() != null && getRolPorDefectoId() != null){
			return true;
		}
		return false;
	}

}
