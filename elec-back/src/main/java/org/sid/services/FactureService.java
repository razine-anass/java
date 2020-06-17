package org.sid.services;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.sid.dto.ChantierDto3;
import org.sid.dto.FactureDto;
import org.sid.dto.FactureDto2;
import org.sid.entities.Facture;
import org.sid.entities.Tache;
import org.sid.repositories.FactureRepository;
import org.sid.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FactureService {
	
	@Autowired
	public FactureRepository factureRepository;
	
	@Autowired
	private Mapper<Facture> mapper;
	
	public FactureDto findFacturById(Long id){
		
		FactureDto facturDto = null;
		
		FactureDto2 facturDto2 = factureRepository.findChantierDto(id);
		
		for(Tache t:facturDto2.getChantier().getTache()){
			t.toString();
		}
		
		Collection<ChantierDto3> ch = factureRepository.findById(id,ChantierDto3.class);
		
		ch.toString();
		
		Facture facture = factureRepository.chercher(id);
		
		Object[] total = factureRepository.chercherFactTotal(id);
		
		Object[] plusChereTache = factureRepository.tacheGrandPrix(id);
		
		
		for(int i=0;i<plusChereTache.length;i++){
			Object[] table = (Object[]) plusChereTache[i];
			for(int j=0;j<table.length;j++){
				System.out.println(table[j]);
			}
		}
		
//		for(Object obj: plusChereTache){
//			List<Object> list = Arrays.asList(obj);
//			Object[] table = (Object[]) list.get(0);
//			for(int i=0; i<table.length;i++){
//				System.out.println(table[i]);
//		      }
//			list.size();
//		}
		
		if(facture!=null){
			
    		facturDto = (FactureDto) mapper.convertToDto(facture, new FactureDto());
    		facturDto.getChantier().setTaches(facture.getChantier().getTache());
			
		} else {
			facturDto = new FactureDto();
		}
		
		return facturDto;
	}

}
