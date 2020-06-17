package org.sid.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Chantier implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String nom;
	
	@Column(nullable = false)
	private String adress;
	
	@OneToMany(mappedBy="chantier",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private Set<Tache> taches = new HashSet<Tache>();
	
	
	public Chantier() {
		super();
	}
	
	
	public Chantier(Long id, String nom, String adresse, Set<Tache> tache) {
		super();
		this.id = id;
		this.nom = nom;
		this.adress = adresse;
		this.taches = tache;
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
	public Set<Tache> getTache() {
		return taches;
	}
	public void setTache(Set<Tache> tache) {
		this.taches = tache;
	}
	

}
