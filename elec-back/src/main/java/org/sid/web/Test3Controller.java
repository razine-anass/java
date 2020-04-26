package org.sid.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acces")
public class Test3Controller {
	
		
		@GetMapping("/acces")
		public String test(){
			return "public ";
		}
	}