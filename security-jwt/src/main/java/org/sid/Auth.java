package org.sid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Auth {
	
	@Autowired
	private AuthenticationManager AuthenticationManager;
	
	@Autowired
	private MonUserDetailsService monUserDetailsService;
	
	@Autowired
	private JsonWebToken jsonWebToken;
	
	@GetMapping("/auto")
	public String sidentifier(){
		
		return "succes";
	}
	
	@PostMapping("/autentification")
	public ResponseEntity<?>  token(@RequestBody Authentification authentification){
	    try{
			//s'authentifier
		  AuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authentification.getUserName(), authentification.getPassword()));
		} catch (BadCredentialsException e) {
			new Exception("authentificatioin echouée");
		}
	    final UserDetails userDetails = monUserDetailsService
	    		             .loadUserByUsername(authentification.getUserName());
	    
	    final String token = jsonWebToken.generatToken(userDetails);
	    
        return  ResponseEntity.ok(new GeneratToken(token));
	}

}
