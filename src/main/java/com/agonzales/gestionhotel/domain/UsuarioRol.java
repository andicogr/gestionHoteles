package com.agonzales.gestionhotel.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.agonzales.gestionhotel.util.Entidad;
import com.agonzales.gestionhotel.util.EntidadAuditoria;
import com.agonzales.gestionhotel.util.Util;

@Entity
@Table(name="usuario_rol")
public class UsuarioRol extends EntidadAuditoria implements Entidad{

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="usuario_rol_id_seq_generator",sequenceName="usuario_rol_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="usuario_rol_id_seq_generator")
	@Column(unique=true, nullable=false)
	private Integer id;

	@ManyToOne
	@JoinColumn(name="usuario_id", nullable=false)
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="rol_id", nullable=false)
	private Rol rol;
	
	private boolean activo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean isRolActivo(){
		if(getRol().isActivo()){
			return true;
		}
		return false;
	}
	
	public List<Privilegio> getPrivilegios(){
		return getRol().getPrivilegios();
	}

	public boolean isUsuarioRolYRolActivo(){
		if(isActivo() && isRolActivo()){
			return true;
		}
		return false;
	}
	
	public String getNombreRol(){
		if(getRol() != null){
			return getRol().getNombre();
		}
		return "";
	}
	
	public String getEstadoUsuarioRol(){
		return Util.obtenerNombreEstado(isActivo());
	}
	
	public Integer getUsuarioId(){
		return getUsuario().getId();
	}
	
	public Integer getRolId(){
		return getRol().getId();
	}
}
