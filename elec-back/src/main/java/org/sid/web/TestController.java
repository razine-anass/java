package org.sid.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@GetMapping("/user")
	public String test(){
		return "user";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/user/admin")
	public String teste(){
		return "admin";
	}

}
