package com.agonzales.gestionhotel.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.agonzales.gestionhotel.util.Entidad;
import com.agonzales.gestionhotel.util.EntidadAuditoria;
import com.agonzales.gestionhotel.util.Util;

@Entity
@Table(name="rol")
public class Rol extends EntidadAuditoria implements Entidad{

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="rol_id_seq_generator",sequenceName="rol_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="rol_id_seq_generator")
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=50, nullable=true)
	private String nombre;

	@Column(length=250)
	private String descripcion;

	@ManyToMany(fetch = FetchType.LAZY)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name="rol_privilegio", 
		joinColumns = {@JoinColumn(name = "rol_id")}, 
		inverseJoinColumns = {@JoinColumn(name = "privilegio_id")})
	private List<Privilegio> privilegios;

	@OneToMany(mappedBy="rol")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<AccesoCompaniaRol> companias;

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

	public List<Privilegio> getPrivilegios() {
		return privilegios;
	}

	public void setPrivilegios(List<Privilegio> privilegios) {
		this.privilegios = privilegios;
	}

	public List<AccesoCompaniaRol> getCompanias() {
		return companias;
	}

	public void setCompanias(List<AccesoCompaniaRol> companias) {
		this.companias = companias;
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

	public Rol(){}
	
	public Rol(Integer id, String nombre){
		this.id = id;
		this.nombre = nombre;
	}
	
	public Rol(Integer id){
		this.id = id;
	}
}
