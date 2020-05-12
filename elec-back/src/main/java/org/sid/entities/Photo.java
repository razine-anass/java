package org.sid.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Photo  implements Serializable{
	
	private static final long serialVersionUID = 9080362988271629535L;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nom;
	
	private String type;
	
	//image bytes can have large lengths so we specify a value
    //which is more than the default length for picByte column
	@Column(name = "picByte", length = 1000)
	private byte[] picByte;
	

	public Photo() {
		super();
	}
	
	public Photo(String nom, String type, byte[] picByte) {
		super();
		this.nom = nom;
		this.type = type;
		this.picByte = picByte;
	}
	
	public Photo(Long id, String nom, String type, byte[] picByte) {
		super();
		this.id = id;
		this.nom = nom;
		this.type = type;
		this.picByte = picByte;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return nom;
	}

	public void setFileName(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public byte[] getPicByte() {
		return picByte;
	}

	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
