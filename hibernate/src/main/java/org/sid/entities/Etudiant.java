package org.sid.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

//@ManyToMany sans @ManyToMany dans l'autre classe: il cree un table de jointure.
//@ManyToMany dans les deux classes: il cree deux table de jointure
//@ManyToMany dans les deux classes avec MappedBy dans une seul classe: il cree un table de jointure.

@Entity
public class Etudiant {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nom;
	@ManyToMany (mappedBy="etudiants")
	private List<Module> modules;
	
	public Etudiant() {
		super();
	}

	public Etudiant(Long id, String nom, List<Module> modules) {
		super();
		this.id = id;
		this.nom = nom;
		this.modules = modules;
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

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	@Override
	public String toString() {
		return "Etudiant [id=" + id + ", nom=" + nom + "]";
	}
	
	
}
