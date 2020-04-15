package org.sid.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;

import com.fasterxml.jackson.annotation.JsonIgnore;

//relation ManyToOne sans OneTOMany:cree une colonne de jointure dans la table book
//MappedBy n'exsite pas dans ManyToOne
//FetchType.EAGER lance 2 requetes:requete pour l'objet contenant et une requete pour l'objet contenu problème N+1
//join fetch: lance une seul requete et charge l'objet contenant et l'objet contenu  en meme temps


@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nom;
//	@JsonBackReference
	@JsonIgnore
	@ManyToOne (fetch =FetchType.LAZY)
	private Student student;
	
//	@OneToMany(fetch = FetchType.LAZY)
//	private List<Chapitre> chapitres;
	
	public Book() {
		super();
	}
	
	
	public Book(Long id, String nom, Student student, List<Chapitre> chapitres) {
		super();
		this.id = id;
		this.nom = nom;
		this.student = student;
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
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	


	@Override
	public String toString() {
		return "Book [id=" + id + ", nom=" + nom + "]";
	}


	

}
