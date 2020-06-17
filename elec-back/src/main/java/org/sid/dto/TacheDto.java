package org.sid.dto;

import java.io.Serializable;

import org.sid.entities.Chantier;
import org.sid.utils.DTOEntity;

public class TacheDto implements Serializable,DTOEntity {


	private static final long serialVersionUID = 1L;

    private Long id;
	
	private String descriptif;
	
	private Integer prix;
	
	private Chantier chantier;
	

	public TacheDto() {
		super();
	}

	public TacheDto(Long id, String descriptif, Integer prix, Chantier chantier) {
		super();
		this.id = id;
		this.descriptif = descriptif;
		this.prix = prix;
		this.chantier = chantier;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescriptif() {
		return descriptif;
	}

	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}

	public Chantier getChantier() {
		return chantier;
	}

	public void setChantier(Chantier chantier) {
		this.chantier = chantier;
	}

	public Integer getPrix() {
		return prix;
	}

	public void setPrix(Integer prix) {
		this.prix = prix;
	}
}
