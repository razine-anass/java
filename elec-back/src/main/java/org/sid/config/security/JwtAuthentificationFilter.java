package org.sid.config.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sid.entities.MonUserDetails;
import org.sid.entities.Users;
import org.sid.utils.SecurityParams;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;


// Classe qui genere le Jwt
//Ce filter s'execute en premier au moment ou le user tente de s'authentifier
public class JwtAuthentificationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public JwtAuthentificationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	// s'execute en premier, recuper le username et password
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		//si les parametre sont envoyé en format 3w urlencoded
		//String username = request.getParameter("username");
		//String password = request.getParameter("password");
		
		Users user = null;
		
		//on utilise jakcsson pour desirialiser le user depuis le request
		try {
			 user = new ObjectMapper().readValue(request.getInputStream(), Users.class);
			// on retourne le user
				return authenticationManager.authenticate(
		                      new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@Override
	// s'execute arpès avoir verifier l'authenticité du user
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// authResult permet de recuper l'utilisateur qui a reussi l'authentification
		//User user = (User) authResult.getPrincipal();
		MonUserDetails monUserDetails = (MonUserDetails) authResult.getPrincipal();
		User user  = new User(monUserDetails.getUsername(),monUserDetails.getPassword(),monUserDetails.getAuthorities());
		List<String> roles = new ArrayList<String>();
		
		user.getAuthorities().forEach(role->{
			roles.add(role.getAuthority());
		});
		
		// jwt = Header.Payload.Signature
		String jwt = JWT.create()
				     //####################### PayLOAD ###################################
				      //url de l'application qui a generer le jwt
				     .withIssuer(request.getRequestURI())
				     .withSubject(user.getUsername())
				     .withArrayClaim("roles", roles.toArray(new String[roles.size()]))
				     // date d'exiparation jour*heur*minute
				     .withExpiresAt(new Date(System.currentTimeMillis()+SecurityParams.EXPIRATION))
				     // dans le header on definit l'algorithme pour calculer la signature
				     //####################### Header(Algorithm.HMAC256) + Signature ###################################
				     .sign(Algorithm.HMAC256(SecurityParams.PRIVATE_SECRET));
		
		//on envoie le jwt dans le header de la response
		response.addHeader(SecurityParams.JWT_HEADER, SecurityParams.TOKEN_PREFIX+jwt);
	}
}
