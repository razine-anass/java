package org.sid.config.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sid.exception.ElecException;
import org.sid.utils.SecurityParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;



/**
 response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers,authorization");
        response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Credentials, authorization");
        if(request.getMethod().equals("OPTIONS")){
            response.setStatus(HttpServletResponse.SC_OK);
        }
        else if(request.getRequestURI().equals("/login")) {
            filterChain.doFilter(request, response);
            return;
        }
 */



//Filter  pour verifier l'autheticité du Token
public class JwtAuthorizationFilter extends OncePerRequestFilter {
	
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// on authorise tout les domaine d'acceder à notre serveur
		//response.addHeader("Access-Control-Allow-Origin", "*");
		
		// on autorise les navigateurs  d'envoyer des requtes possedant les entetes suivant 
		response.addHeader("Access-Control-Allow-Headres", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method,Access-Control-Request-Headres,Authorization");
		
		//on permtes les application clients à lire les entites suivant 
		response.addHeader("Access-Control-Expose-Headers","Access-Control-Allow-Origin, Access-Control-Allow-Credentials,Authorization");

		//response.addHeader("Access-Control-Allow-Methods", "OPTIONS, HEAD, GET, POST, PUT, DELETE, PATCH");
		
		// si le navigatuer envoi une requtes avec la methode OPTIONS c'est pas la peind de verifier le jwt parce qu'il s'agit 
		     // d'une premier connexion
		if(request.getMethod().equals("OPTIONS")){
			
		    response.setStatus(HttpServletResponse.SC_OK);
			
		} 
		  else if(request.getRequestURI().equals("/login")) {
			
			filterChain.doFilter(request, response);
			
		} 
		  else {
			
			//on recupere la valeur du token Jwt stockée dans la variable Authorization
			final String jwt = request.getHeader(SecurityParams.JWT_HEADER);
			//si le jwt est null ou ne contient le prefiw Bareer
			if(jwt==null || !jwt.startsWith(SecurityParams.TOKEN_PREFIX)){
				//l'utilisateur ne peut pas ne pas executer les requets
				filterChain.doFilter(request, response);
				return;
			}
			//on recuper l'algorthime pour decripter le secret du jwt
			JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SecurityParams.PRIVATE_SECRET)).build();
			
			DecodedJWT decodedJWT = null;
			//ou cas le client envoi un jwt falsifié
			try {
				
				decodedJWT = verifier.verify((jwt.substring(SecurityParams.TOKEN_PREFIX.length(),jwt.length())));
				
			} catch (JWTVerificationException e) {
				
				log.error("le token est invalide");
				
				//Don’t Log and Throw
//				throw new ElecException.InvalidJwtException("le token est invalide",e);
			}
			
			if (decodedJWT!=null) {
				//on recuper le usename
				String username = decodedJWT.getSubject();
				//on recuper les roles
				List<String> roles = decodedJWT.getClaims().get("roles").asList(String.class);
				System.out.println(roles);
				Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				roles.forEach(role -> {
					authorities.add(new SimpleGrantedAuthority(role));
				});
				//on cree le user
				UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(username, null,
						authorities);
				//on fait authentifier l'utilisateur//on verifier si cet utilisateur est dans le contexte de spring security c-a-d si cet user est déjà authentifieé
				SecurityContextHolder.getContext().setAuthentication(user);
				//je passe
				filterChain.doFilter(request, response);
			}
		}
	}

}
