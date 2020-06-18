package org.sid.web;

import java.util.ArrayList;
import java.util.List;

import org.sid.entities.Facture;
import org.sid.services.FactureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/donnee")
public class FactureController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public FactureService factureService;
	
	@GetMapping("/facture")
	public ResponseEntity<?> getFactureAll(){
		List<Facture> factures=new ArrayList<Facture>();
		
		try {
			factures = factureService.findAll();
		} catch (Exception e) {
			log.error("problème lors du chargment des factures",e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("problème lors du chargment des factures");
		}
		return ResponseEntity.status(HttpStatus.OK).body(factures);
	}
	
	@GetMapping("/facture/{id}")
	public ResponseEntity<?> getFactureById(@PathVariable Long id){
		
		Facture f = new Facture();
	
	    try {
			factureService.findById(id);
		} catch (Exception e) {
			log.error("problème lors du chargment de la facture",e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("problème lors du chargment de la facture");
		}
	    return ResponseEntity.status(HttpStatus.OK).body(f);
	}

}
