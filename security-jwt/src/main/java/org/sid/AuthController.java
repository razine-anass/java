package org.sid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
	
	@Autowired
	private AuthenticationManager AuthenticationManager;
	
	@Autowired
	private MonUserDetailsService monUserDetailsService;
	
	@Autowired
	private JsonWebToken jsonWebToken;
	
	@GetMapping("/")
	public String index(){
		
		return "succes";
	}
	
	@GetMapping("/welcome")
	public String welcome(){
		
		return "succes";
	}
	
	
	@GetMapping("/user")
	public String authUser(){
		
		return "user authentifié";
		
	}
	
	@GetMapping("/admin")
	public String authAdmin(){
		
		return "admin authentifié";
		
	}
	
	@PostMapping(value = "/login", produces = "application/json")
	public ResponseEntity<?>  token(@RequestBody Authentification authenticationRequest){
		Authentication authentication = null;
		UserDetails userDetails = null;
		String token = null;
		
		final HttpHeaders httpHeaders= new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		
	    try{
			//on envoie la demande d'autentification
	    	 authentication =  AuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			new Exception("authentificatioin echouée");
		}
	    //Si  l'authentification passe on genere le jeton
	    if(authentication!=null && authentication.isAuthenticated()){
	    	
	    	   userDetails = monUserDetailsService
 		             .loadUserByUsername(authenticationRequest.getUserName());
 
               token = jsonWebToken.generatToken(userDetails);
               
//              return ResponseEntity.status(HttpStatus.OK).body(new GeneratToken(token));
//               return ResponseEntity.status(HttpStatus.OK).body(new GeneratToken(token));
               return new ResponseEntity<GeneratToken>(new GeneratToken(token), httpHeaders, HttpStatus.OK);

	    	
	    } else {
	    	
	    }
	    
        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
	}
	
	@PostMapping(value = "/create/jeton", produces = "application/json")
	public ResponseEntity<?>  createToken(@RequestBody Authentification authenticationRequest){
		Authentication authentication = null;
		UserDetails userDetails = null;
		String token = null;
		
		final HttpHeaders httpHeaders= new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
	    try{
			//on envoie la demande d'autentification
	    	 authentication =  AuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			new Exception("authentificatioin echouée");
		}
	    //Si  l'authentification passe on genere le jeton
	    if(authentication!=null && authentication.isAuthenticated()){
	    	
	    	   userDetails = monUserDetailsService
 		             .loadUserByUsername(authenticationRequest.getUserName());
 
               token = jsonWebToken.generatToken(userDetails);
               
             return new ResponseEntity<GeneratToken>(new GeneratToken(token), httpHeaders, HttpStatus.OK);
	    	
	    } else {
	    	
	    }
	    
        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
	}

}
