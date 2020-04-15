package org.sid.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Avion {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nom;

	@OneToOne (fetch = FetchType.LAZY,mappedBy="avion")
	private Pilote pilote;
	
	public Avion() {
		super();
	}
	public Avion(Long id, String nom, Pilote pilote) {
		super();
		this.id = id;
		this.nom = nom;
		this.pilote = pilote;
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
	public Pilote getPilote() {
		return pilote;
	}
	public void setPilote(Pilote pilote) {
		this.pilote = pilote;
	}
	
	
	//affiche en boucle avion et ça depend pas du mode de chargement
//	@Override
//	public String toString() {
//		return "Avion [id=" + id + ", nom=" + nom + ", pilote=" + pilote + "]";
//	}
	@Override
	public String toString() {
	return "Avion [id=" + id + ", nom=" + nom +
			"]";
    }
	
}
