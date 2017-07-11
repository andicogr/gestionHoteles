package com.agonzales.gestionhotel.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
	private String usuario;

	@Column(length=20, nullable=false)
	private String clave;
	
	@ManyToMany
	@JoinTable(name="usuario_rol", joinColumns = {@JoinColumn(name = "usuario_id")}, inverseJoinColumns = {@JoinColumn(name = "rol_id")})
	private List<Rol> roles;
	
	private boolean activo;
	
	private boolean bloqueado;
	
	@Column(name="expirar_usuario")
	private boolean expirarUsuario;
	
	@Column(name="fecha_expirancion_usuario")
	private Date fechaExpiracionUsuario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}


	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
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
		for(Rol rol : getRoles()){
			if(rol.isActivo()){
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

}
