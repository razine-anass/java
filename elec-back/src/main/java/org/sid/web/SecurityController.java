package org.sid.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class SecurityController {
	
	
	//Action / c'est l'action par default ca veut lorsqu'on tape http://localhost:8080
	@RequestMapping(value="/")
	public String home(){
		return "redirect:/donnees/chantiers";
	}
	
	@GetMapping(value="/login")
	public String login(){
		return "Vous etes connectès";
	}

}

