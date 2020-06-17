package org.sid.dto;

import org.sid.entities.Chantier;

import lombok.Value;


@Value
public class ChantierDto3 {

    private Long id;
	
	private String ref;
	
	private Chantier chantier;
}
