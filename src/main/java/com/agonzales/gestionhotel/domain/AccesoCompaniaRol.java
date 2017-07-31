package com.agonzales.gestionhotel.domain;

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
import com.agonzales.gestionhotel.util.EntidadBase;
import com.agonzales.gestionhotel.util.Util;

@Entity
@Table(name="acceso_compania_rol")
public class AccesoCompaniaRol extends EntidadBase implements Entidad{

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="acceso_compania_rol_id_seq_generator",sequenceName="acceso_compania_rol_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="acceso_compania_rol_id_seq_generator")
	@Column(unique=true, nullable=false)
	private Integer id;

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
	
	public Integer getRolId(){
		if(getRol() != null){
			return getRol().getId();
		}else{
			return null;
		}
	}
	
	public Integer getCompaniaId(){
		if(getCompania() != null){
			return getCompania().getId();
		}
		
		return null;
	}
	
	public String getNombreCompania(){
		if(getCompania() != null){
			return getCompania().getRazonSocial();
		}else{
			return "";
		}
		
	}
	
	public String getEstadoAccesoCompaniaRol(){
		return Util.obtenerNombreEstado(isActivo());
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
