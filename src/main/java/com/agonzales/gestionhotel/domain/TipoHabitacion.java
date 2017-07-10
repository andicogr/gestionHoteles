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

@Entity
@Table(name="tipo_habitacion")
public class TipoHabitacion extends EntidadBase implements Entidad{

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="tipo_habitacion_id_seq_generator",sequenceName="tipo_habitacion_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="tipo_habitacion_id_seq_generator")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=60, nullable=false)
	private String nombre;
	
	@Column(length=250, nullable=false)
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
	
	
}
