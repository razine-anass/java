package org.sid.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class Test3Controller {
	
	@GetMapping("/")
	public String test(){
		return "user ";
	}
}