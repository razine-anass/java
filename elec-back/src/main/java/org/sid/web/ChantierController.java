package org.sid.web;

import java.util.List;
import java.util.NoSuchElementException;

import org.sid.entities.Chantier;
import org.sid.services.ChantierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
@RequestMapping("/donnees")
public class ChantierController {

	@Autowired
	ChantierService chantierService;

	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ADMIN')")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	@Secured({ "Role_ADMIN", "ADMIN" })
	
//	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ADMIN')")
	@GetMapping("/chantiers")
	ResponseEntity<?> getChantiers() {

		List<Chantier> chantiers = chantierService.findAll();

		return ResponseEntity.status(HttpStatus.OK).body(chantiers);
	}

	
	@GetMapping("/chantiers/{id}")
	ResponseEntity<?> getChantierById(@PathVariable("id") Long id) {

		Chantier chantier = null;
		try {
			chantier = chantierService.findById(id);
		} catch (NoSuchElementException e) {

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

		Chantier cht = chantierService.save(chantier);

		return ResponseEntity.status(HttpStatus.CREATED).body(cht);
	}

	
	@PutMapping("/chantiers")
	ResponseEntity<?> updateChantier(@RequestBody Chantier chantier) {
		Chantier chant = chantierService.findById(chantier.getId());
				
		chant.setAdress(chantier.getAdress());
		chant.setNom(chantier.getNom());

		Chantier ch = chantierService.update(chant);

		return ResponseEntity.status(HttpStatus.OK).body(ch);
	}
	
	
	@DeleteMapping("/chantiers/{id}")
	ResponseEntity<?> deleteChantier(@PathVariable("id") Long id) {
        try {
        	chantierService.deleteById(id);
        } catch(Exception e){
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("donnee non trouvée");
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(id);
	}

}
