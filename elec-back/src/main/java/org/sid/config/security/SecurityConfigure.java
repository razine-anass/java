package org.sid.config.security;

import org.sid.services.MonUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *  <li sec:authorize="hasRole('ROLE_ADMIN')">
 *  -----------------------------------------------------------------
1- le navigateur commence par effectuer un http GET l’URL protégée par une Basic Auth
2- on reçoit coté client un couple en-tête HTTP / code HTTP différent, mais le schéma 
   d’authentification est défini au niveau de ces en-têtes (ici Basic):
     Le serveur Web qui répond avec :
     code HTTP 401 (Unauthorized)
     en-tête HTTP : WWW-Authenticate: Basic realm="Acces restreint"
3- la pop-up d’authentification s’affiche alors dans le Navigateur
(l’authentification à travers l’URL http://login:pass@exemple.com est dépréciée)
4- le navigateur encode en base64 la chaîne: « login:pass » et la transmet via un GET (de la même ressource) 
   avec l’en-tête HTTP Authorization ex: Authorization: Basic bG9naW46cGFzcw==
5- le  Serveur Web qui gère l’authentification réponds après vérification, soit par une 200 (et tout roule), soit par une 403 (Forbidden)…
6- le navigateur garde en cache ces credentials et les envois lors de toutes les requêtes HTTP vers ce realm (ici la chaine Acces restreint)
7- lorsque l’onglet est fermé, le navigateur supprime ces credentials de son cache
 *
 *
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigure extends WebSecurityConfigurerAdapter{

	@Autowired
	MonUserDetailsService monUserDetailsService;
	
	
	//gere l'authentification
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //on passe notre userDetails ainsi que la methode d'encodage à spring securite afin qu'il verfie 
		 // la validité du password du user qui tente de s'authentifier
		auth.userDetailsService(monUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	//gere l'autorisation
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
        //--------------------------Authentification Basic--------------------------
		//pour permettre le cross origin
//	   	 http.cors();
//	   	//definit le type d'authentification ici il s'agit d'une authentification de type Basic
//	   	 http.httpBasic();
//		 http.csrf().disable();
//		 http
//		   .authorizeRequests()
//		          //pour accerder à toutes url contenant /test/ il faut se connecter et etre ADMIN ou USER
//		         .antMatchers("/test/**").hasAnyRole("USER")
//		         .and()
//		   .authorizeRequests()
//		          //pour accerder à toutes url contenant /donnees/ il faut se connecter et etre ADMIN
//	             .antMatchers("/donnees/**").hasAnyRole("ADMIN")
//	             .and()
//	       .authorizeRequests()
//	              //toutes les urls contenant /acces/ ne requeirt pas ni authentification ni roles
//	              .antMatchers("/donnees/photos").permitAll()
//	              .and()
//	       .authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**","/public/**").permitAll()
//	              //pour accerder aux restes des url contenant il faut juste  se connecter
//	              // anyResuest doit etre toujout à la fin
//		          .anyRequest().authenticated();
       //----------------------------------------------------
	  
	   //----------------------------Authentification by Token------------------------
		
		// on désactive la création du token de spring security
		http.cors();
		
		http.csrf().disable();
		
		// on désactive la créatioin des session
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http
		 .authorizeRequests()
		   .antMatchers("/api/admin**/**").hasAnyRole("ADMIN","USER")
		   .and()
		 .authorizeRequests()
		   .antMatchers("api/user**/**").hasRole("USER")
		   .and()
		 .authorizeRequests()
		   .antMatchers("/login","/signIn","/public**/**","/donnees**/**","/private**/**").permitAll()
		 .anyRequest().authenticated();
		
		// après l'authentification ce filter qui verfie si les requete provenant du client ont le bon Jwt
		http.addFilterBefore(new JwtAuthorizationFilter(),UsernamePasswordAuthenticationFilter.class);
		
		//puisque on utilise pas une authentification Basic (où springScurité receuper le username et le password 
	                    	//à laide du header  Authorization: Basic bG9naW46cGFzcw== qui contient le username et le password)
		//on utilise ce filter pour recuper par nous meme le username et le password
		// et generer le Jwt
		http.addFilter(new JwtAuthentificationFilter(authenticationManager()));
		
	   //----------------------------------------------------

	
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
		
}
