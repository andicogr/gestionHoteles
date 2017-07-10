package com.agonzales.gestionhotel.domain;

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
	@JoinTable(name="usuario_privilegio", joinColumns = {@JoinColumn(name = "usuario_id")}, inverseJoinColumns = {@JoinColumn(name = "privilegio_id")})
	private List<Privilegio> Privilegio;
	
	private String estado;

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


	public List<Privilegio> getPrivilegio() {
		return Privilegio;
	}

	public void setPrivilegio(List<Privilegio> privilegio) {
		Privilegio = privilegio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

}
