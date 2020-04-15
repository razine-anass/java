package org.sid.exemple;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.annotation.Version;

@Entity
public class Partner {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	@Version
	private int version;
	

	public Partner() {
		super();
	}

	public Partner(Long id, String name, int version) {
		super();
		this.id = id;
		this.name = name;
		this.version = version;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}