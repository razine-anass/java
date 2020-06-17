package org.sid.dto;

import java.io.Serializable;

import org.sid.utils.DTOEntity;

public class FactureDto implements Serializable,DTOEntity {

	private static final long serialVersionUID = 1L;

    private Long id;
	
	private String ref;
	
	private ChantierDto chantier;

	public FactureDto() {
		super();
	}

	public FactureDto(Long id, String ref, ChantierDto chantier) {
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

	public ChantierDto getChantier() {
		return chantier;
	}

	public void setChantier(ChantierDto chantier) {
		this.chantier = chantier;
	}
	
}
