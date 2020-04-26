package org.sid.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class Test2Controller {
	
		
		@GetMapping("/user")
		public String test(){
			return "public ";
		}
		
		@GetMapping("/public/admin")
		public String teste(){
			return "public admin";
		}

	}
