package com.agonzales.gestionhotel.util;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.agonzales.gestionhotel.domain.Compania;

@MappedSuperclass
public class EntidadBase extends EntidadAuditoria implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="compania_id")
	private Compania compania;

	public Compania getCompania() {
		return compania;
	}

	public void setCompania(Compania compania) {
		this.compania = compania;
	}
	
}
