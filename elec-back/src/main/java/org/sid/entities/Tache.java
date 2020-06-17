package org.sid.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Tache implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String descriptif;
	
	private Integer prix;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private Chantier chantier;
	

	public Tache() {
		super();
	}

	public Tache(String descriptif, Integer prix, Chantier chantier) {
		super();
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

	@Override
	public String toString() {
		return "Tache [id=" + id + ", descriptif=" + descriptif + ", prix=" + prix + "]";
	}
	
}
