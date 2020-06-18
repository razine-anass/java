package org.sid.web;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.sid.entities.Chantier;
import org.sid.services.ChantierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
//endpoint
@RequestMapping("/donnees")
public class ChantierController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ChantierService chantierService;

	
//	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ADMIN')")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	@Secured({ "Role_ADMIN", "ADMIN" })
	
//	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ADMIN')")
	@GetMapping("/chantiers")
	ResponseEntity<?> getChantiers() {

		List<Chantier> chantiers = new ArrayList<Chantier>();
		try {
			chantiers = chantierService.findAll();
		} catch (Exception e) {
			log.error("problème lors du chargment des chantiers",e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("problème lors du chargment des chantiers");
		}

		return ResponseEntity.status(HttpStatus.OK).body(chantiers);
	}

	
	@GetMapping("/chantiers/{id}")
	ResponseEntity<?> getChantierById(@PathVariable("id") Long id) {

		Chantier chantier;
		try {
			chantier = chantierService.findById(id);
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("donnee non trouvée");
		}

		return ResponseEntity.status(HttpStatus.OK).body(chantier);

	}
	
	@GetMapping("/chantiers/pageable")
	ResponseEntity<?> getChantierPageable(@RequestParam(name="page",defaultValue="0")int page,
			                              @RequestParam(name="size",defaultValue="5")int size){
		
		Page<Chantier> chantiers = chantierService.findPaginated(page, size);
		
		return ResponseEntity.status(HttpStatus.OK).body(chantiers.getContent());
	}
	
	@PostMapping("/chantiers")
	ResponseEntity<?> createChantier(@RequestBody Chantier chantier) {

		Chantier cht ;
		try {
			cht = chantierService.save(chantier);
		} catch (Exception e) {
           log.error("problème lors d'enregistrement du chantier",e);
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("problème lors d'enregistrement du chantier");
		}

		return ResponseEntity.status(HttpStatus.OK).body(cht);
	}

	
	@PutMapping("/chantiers")
	ResponseEntity<?> updateChantier(@RequestBody Chantier chantier) {
		Chantier ch;
		try {
			Chantier chant = chantierService.findById(chantier.getId());
					
			chant.setAdress(chantier.getAdress());
			chant.setNom(chantier.getNom());

			ch = chantierService.update(chant);
		} catch (Exception e) {
		
			log.error("problème lors de mise ajour du chantier",e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("problème lors de mise ajour du chantier");
		}

		return ResponseEntity.status(HttpStatus.OK).body(ch);
	}
	
	
	@DeleteMapping("/chantiers/{id}")
	ResponseEntity<?> deleteChantier(@PathVariable("id") Long id) {
        try {
        	chantierService.deleteById(id);
        } catch(Exception e){
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("donnee non trouvée");
        }
        return ResponseEntity.status(HttpStatus.OK).body(id);
	}
	

}
