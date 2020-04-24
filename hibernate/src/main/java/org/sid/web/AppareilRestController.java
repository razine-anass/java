package org.sid.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appareils")
@CrossOrigin(origins = "http://localhost:4200")
public class AppareilRestController {
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/appareil")
	ResponseEntity<?> saveAppareil(@RequestBody List<AppareilDto> appareilDto){
		
		List<AppareilDto> ap = new ArrayList<AppareilDto>();
		AppareilDto appareilDto2 = new AppareilDto();
		appareilDto2.setName("Lampe");
		ap.add(appareilDto2);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(ap);
		
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/appareil")
	ResponseEntity<?> getAppareil(){
		AppareilDto appareilDto = new AppareilDto();
		appareilDto.setId(1L);
		appareilDto.setName("Lampe");
		appareilDto.setStatus("eteint");
		
		AppareilDto appareilDto2 = new AppareilDto();
		appareilDto.setId(2L);
		appareilDto2.setName("Four");
		appareilDto2.setStatus("eteint");
		
		List<AppareilDto> ap = new ArrayList<AppareilDto>();
		ap.add(appareilDto);
		ap.add(appareilDto2);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(ap);
		
	}
	
	
	@GetMapping("/appareilParam")
	ResponseEntity<?> getAppareilParam(@RequestParam("name") String name,@RequestParam("status") String status){
		AppareilDto appareilDto = new AppareilDto();
		appareilDto.setId(1L);
		appareilDto.setName(name);
		appareilDto.setStatus(status);
		
		List<AppareilDto> ap = new ArrayList<AppareilDto>();
		ap.add(appareilDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(ap);
		
	}

}
