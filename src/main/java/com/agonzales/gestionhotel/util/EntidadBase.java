package com.agonzales.gestionhotel.util;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.agonzales.gestionhotel.domain.Compania;

@MappedSuperclass
public class EntidadBase implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="compania_id")
	private Compania compania;

	@Column(name="uid_creacion")
	private Integer usuarioCreacion;

	@Column(name="uid_actualizacion")
	private Integer usuarioActualizacion;
	
	@Column(name="fecha_creacion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;
	
	@Column(name="fecha_actualizacion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaActualizacion;

	public Compania getCompania() {
		return compania;
	}

	public void setCompania(Compania compania) {
		this.compania = compania;
	}

	public Integer getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(Integer usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Integer getUsuarioActualizacion() {
		return usuarioActualizacion;
	}

	public void setUsuarioActualizacion(Integer usuarioActualizacion) {
		this.usuarioActualizacion = usuarioActualizacion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	
}
