package org.sid.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.sid.enums.FactureStatut;

@Entity
public class Facture  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String ref;
	
	private LocalDate date;
	
	@Enumerated(EnumType.STRING)
	private FactureStatut statut;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Chantier chantier;

	public Facture() {
		super();
	}

	public Facture(Long id, String ref, LocalDate date, FactureStatut statut, Chantier chantier) {
		super();
		this.id = id;
		this.ref = ref;
		this.date = date;
		this.statut = statut;
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}



	public FactureStatut getStatut() {
		return statut;
	}

	public void setStatut(FactureStatut statut) {
		this.statut = statut;
	}
	
}
