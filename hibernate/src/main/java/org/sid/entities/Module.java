package org.sid.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Module {
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nom;
	
	@ManyToMany 
	private List<Etudiant> etudiants;
	
	

	public Module() {
		super();
	}

	public Module(Long id, String nom, List<Etudiant> etudiants) {
		super();
		this.id = id;
		this.nom = nom;
		this.etudiants = etudiants;
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

	public List<Etudiant> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(List<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}

	@Override
	public String toString() {
		return "Module [id=" + id + ", nom=" + nom + ", etudiants=" + etudiants + "]";
	}

}
