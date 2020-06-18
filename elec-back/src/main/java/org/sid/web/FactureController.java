package org.sid.web;

import java.util.ArrayList;
import java.util.List;

import org.sid.entities.Facture;
import org.sid.services.FactureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/donnee")
public class FactureController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public FactureService factureService;
	
	@GetMapping("/factures")
    ResponseEntity<?> getFactureAll(){
		List<Facture> factures=new ArrayList<Facture>();
		
		try {
			factures = factureService.findAll();
		} catch (Exception e) {
			log.error("problème lors du chargment des factures",e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("problème lors du chargment des factures");
		}
		return ResponseEntity.status(HttpStatus.OK).body(factures);
	}
	
	@GetMapping("/factures/{id}")
    ResponseEntity<?> getFactureById(@PathVariable Long id){
		
		Facture f = new Facture();
	
	    try {
			factureService.findById(id);
		} catch (Exception e) {
			log.error("problème lors du chargment de la facture",e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("problème lors du chargment de la facture");
		}
	    return ResponseEntity.status(HttpStatus.OK).body(f);
	}
	
	@GetMapping("/factures/pageable")
	ResponseEntity<?> getFacturePeagable(@RequestParam(name="page",defaultValue="0")int page,
			                              @RequestParam(name="size",defaultValue="5")int size){
		
        Page<Facture> factures = factureService.findPaginated(page, size);
		
		return ResponseEntity.status(HttpStatus.OK).body(factures.getContent());
	 }
	
	@PostMapping("/factures")
	ResponseEntity<?> createFacture(@RequestBody Facture facture) {

		Facture f ;
		try {
			f = factureService.save(facture);
		} catch (Exception e) {
           log.error("problème lors d'enregistrement du facture",e);
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("problème lors d'enregistrement du facture");
		}

		return ResponseEntity.status(HttpStatus.OK).body(f);
	}

	
	@PutMapping("/factures")
	ResponseEntity<?> updateFacture(@RequestBody Facture facture) {
		Facture f;
		try {
			Facture newFacture = factureService.findById(facture.getId());
					
			newFacture.setRef(facture.getRef());
			newFacture.setChantier(facture.getChantier());
			newFacture.setDate(facture.getDate());

			f = factureService.update(newFacture);
		} catch (Exception e) {
		
			log.error("problème lors de mise ajour du chantier",e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("problème lors de mise ajour du chantier");
		}

		return ResponseEntity.status(HttpStatus.OK).body(f);
	}
	
	
	@DeleteMapping("/factures/{id}")
	ResponseEntity<?> deleteFacture(@PathVariable("id") Long id) {
        try {
        	factureService.deleteById(id);
        } catch(Exception e){
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("donnee non trouvée");
        }
        return ResponseEntity.status(HttpStatus.OK).body(id);
	}

}
