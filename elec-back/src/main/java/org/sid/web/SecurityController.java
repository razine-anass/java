package org.sid.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
	
	
	//Action / c'est l'action par default ca veut lorsqu'on tape http://localhost:8080
	@RequestMapping(value="/")
	public String home(){
		return "redirect:/donnees/chantiers";
	}
	
	@RequestMapping(value="/login")
	public String login(){
		return "login";
	}

}

