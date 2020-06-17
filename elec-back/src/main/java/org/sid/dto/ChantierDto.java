package org.sid.dto;

import java.io.Serializable;
import java.util.Set;

import org.sid.entities.Tache;
import org.sid.utils.DTOEntity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChantierDto implements Serializable,DTOEntity {

	private static final long serialVersionUID = 1L;

    private Long id;
	
	private String nom;
	
	private String adress;
	
	@JsonProperty("taches")
	private Set<Tache> taches;
	
	public ChantierDto() {
		super();
	}

	public ChantierDto(Long id, String nom, String adress, Set<Tache> taches) {
		super();
		this.id = id;
		this.nom = nom;
		this.adress = adress;
		this.taches = taches;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public Set<Tache> getTaches() {
		return taches;
	}

	public void setTaches(Set<Tache> taches) {
		this.taches = taches;
	}
}
