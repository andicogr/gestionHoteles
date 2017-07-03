package com.agonzales.gestionhotel.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.agonzales.gestionhotel.util.Entidad;

@Entity
@Table(name="compania")
public class Compania implements Entidad{

	@Id
	@SequenceGenerator(name="compania_id_seq_generator",sequenceName="compania_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="compania_id_seq_generator")
	@Column(unique=true, nullable=false)
	private Integer id;
	
	@Column(name="razon_social", length=200, nullable=false, unique=true)
	private String razonSocial;
	
	@Column(length=11, nullable=false, unique=true)
	private String ruc;
	
	@Column(length=200)
	private String direccion;
	
	@Column(length=15)
	private String telefono;
	
	@Column(name="correo_contacto", length=64)
	private String correoContacto;
	
	private byte[] logo;
	
	@Column(name="uid_creacion")
	protected Integer usuarioCreacion;

	@Column(name="uid_actualizacion")
	protected Integer usuarioActualizacion;
	
	@Column(name="fecha_creacion")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date fechaCreacion;
	
	@Column(name="fecha_actualizacion")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date fechaActualizacion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial.toUpperCase();
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreoContacto() {
		return correoContacto;
	}

	public void setCorreoContacto(String correoContacto) {
		this.correoContacto = correoContacto;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
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

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}

}