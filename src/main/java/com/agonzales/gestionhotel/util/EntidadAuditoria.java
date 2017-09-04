package com.agonzales.gestionhotel.util;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class EntidadAuditoria implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name="uid_creacion", updatable=false)
	private Integer usuarioCreacion;

	@Column(name="uid_actualizacion")
	private Integer usuarioActualizacion;
	
	@Column(name="fecha_creacion", updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;
	
	@Column(name="fecha_actualizacion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaActualizacion;
	
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
