package org.sid.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class BookDTO implements Serializable {
	
	private Long id;
	private String nom;
	
	
	public BookDTO() {
		super();
	}
	public BookDTO(Long id, String nom) {
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
	
	

}
