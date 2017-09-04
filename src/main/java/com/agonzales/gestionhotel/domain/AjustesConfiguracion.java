package com.agonzales.gestionhotel.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.agonzales.gestionhotel.util.Entidad;
import com.agonzales.gestionhotel.util.EntidadAuditoria;

@Entity
@Table(name="ajustes_configuracion")
public class AjustesConfiguracion extends EntidadAuditoria implements Entidad{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ajustes_configuracion_id_seq_generator",sequenceName="ajustes_configuracion_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="ajustes_configuracion_id_seq_generator")
	@Column(unique=true, nullable=false)
	private Integer id;
	
	@Column(name="activar_multi_compania")
	private boolean activarMultiCompania;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isActivarMultiCompania() {
		return activarMultiCompania;
	}

	public void setActivarMultiCompania(boolean activarMultiCompania) {
		this.activarMultiCompania = activarMultiCompania;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
