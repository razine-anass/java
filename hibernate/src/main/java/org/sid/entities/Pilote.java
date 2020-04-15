package org.sid.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

//l'affichage en boucle infini ne depend pas du mode du chargement mais depend de la methode sting
//une relation bidirectionnelle entraine tjrs le probleme de boucle infini
//Le chargement FetchType.LAZY agit sur les methode JPA et aussi sur les requetes, pour chager entierment l'objet il faut utiliser les jointure

// OneToOne sans OneToOne dans l'autre table: il cree un columne de jointure dans la table qui OneToOne. 
// OneToOne dans les 2 tables sans MappedBy:il cree colonne de jointure dans les deux tables.
//OneToOne dans les 2 tables avec  MappedBy: il cree colonne de jointue dans la table Avion
//Cas spécial:Pilote possed MappedBy donc il maitre et vue que c'est une relation OneToOne il charge pilote avec l'avion meme si c'est du Lazy
@Entity
public class Pilote {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nom;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Avion avion;
	
	public Pilote() {
	}
	public Pilote(Long id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
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
	
	
	
	public Avion getAvion() {
		return avion;
	}
	public void setAvion(Avion avion) {
		this.avion = avion;
	}
	@Override
	public String toString() {
		return "Pilote [id=" + id + ", nom=" + nom + ", avion=" + avion + "]";
	}

}
