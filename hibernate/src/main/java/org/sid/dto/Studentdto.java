package org.sid.dto;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Studentdto implements Serializable{
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	private Long id;
	private String nom;
	private String prenom;
	private String dateNaissance;
	@JsonProperty("books")
	private Collection<BookDTO> books;
	
	public Studentdto() {
		super();
	}
	
	public Studentdto(String nom, String prenom, String dateNaissance, Collection<BookDTO> books) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.books = books;
	}

	public Date getSubmissionDateConverted() throws ParseException {
        return dateFormat.parse(this.dateNaissance);
    }

    public void setSubmissionDate(Date date) {
        this.dateNaissance = dateFormat.format(date);
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
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public Collection<BookDTO> getBooks() {
		return books;
	}
	public void setBooks(Collection<BookDTO> books) {
		this.books = books;
	}
	
}
