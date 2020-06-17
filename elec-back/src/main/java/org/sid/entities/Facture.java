package org.sid.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Facture {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String ref;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Chantier chantier;

	public Facture() {
		super();
	}

	public Facture(Long id, String ref, Chantier chantier) {
		super();
		this.id = id;
		this.ref = ref;
		this.chantier = chantier;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String string) {
		this.ref = string;
	}

	public Chantier getChantier() {
		return chantier;
	}

	public void setChantier(Chantier chantier) {
		this.chantier = chantier;
	}
	
	
}
