package com.agonzales.gestionhotel.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.agonzales.gestionhotel.util.Entidad;
import com.agonzales.gestionhotel.util.EntidadBase;
import com.agonzales.gestionhotel.util.Util;

@Entity
@Table(name="privilegio")
public class Privilegio extends EntidadBase implements Entidad{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="privilegio_id_seq_generator",sequenceName="privilegio_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="privilegio_id_seq_generator")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=50, nullable=true)
	private String nombre;
	
	@Column(length=250)
	private String descripcion;
	
	private boolean activo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	
	public String obtenerEstado(){
		return Util.obtenerNombreEstado(isActivo());
	}

}
