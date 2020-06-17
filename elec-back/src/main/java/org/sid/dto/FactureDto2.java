package org.sid.dto;

import java.io.Serializable;

import org.sid.entities.Chantier;
import org.sid.utils.DTOEntity;

public class FactureDto2 implements Serializable,DTOEntity {

	private static final long serialVersionUID = 1L;

    private Long id;
	
	private String ref;
	
	private Chantier chantier;

	public FactureDto2() {
		super();
	}

	public FactureDto2(Long id, String ref, Chantier chantier) {
		super();
		this.id = id;
		this.ref = ref;
		this.chantier = chantier;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public Chantier getChantier() {
		return chantier;
	}

	public void setChantier(Chantier chantier) {
		this.chantier = chantier;
	}
	
}
