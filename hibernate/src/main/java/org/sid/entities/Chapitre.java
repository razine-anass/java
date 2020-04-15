package org.sid.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Chapitre {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String titre;
	
	public Chapitre() {
		super();
	}
	public Chapitre(Long id, String titre) {
		super();
		this.id = id;
		this.titre = titre;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	@Override
	public String toString() {
		return "Chapitre [id=" + id + ", titre=" + titre + "]";
	}
	
}
