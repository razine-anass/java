package org.sid.config.security;

import java.util.Arrays;

import org.sid.services.MonUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 *  <li sec:authorize="hasRole('ROLE_ADMIN')">
<!--                  <a th:href="@{/getClients}">Clients</a> -->
                   <a href="#" class="dropdown-toggle" data-toggle="dropdown" 
                    role="button" aria-haspopup="true" aria-expanded="false">Clients <span class="caret"></span></a>
			          <ul class="dropdown-menu">
			            <li><a th:href="@{/getClients}">Visualiser</a></li>
			            <li role="separator" class="divider"></li>
			            <li><a href="#">Créer</a></li>
			          </ul>
                 </li>
 * @author Anass
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
        //on precise le type de la securité
		auth.userDetailsService(monUserDetailsService).passwordEncoder(passwordEncoder());
		
//		auth.inMemoryAuthentication().withUser("anass").password("razine").roles("ADMIN");
//		auth.inMemoryAuthentication().withUser("abass").password("kajma").roles("USER");
		
	}
	
	//gere l'autorisation
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	   	http.cors();
	   	http.httpBasic();
		 http.csrf().disable();
		 http
		   .authorizeRequests()
		          //pour accerder à toutes url contenant /test/ il faut se connecter et etre ADMIN ou USER
		         .antMatchers("/test/**").hasAnyRole("USER")
		         .and()
		   .authorizeRequests()
		          //pour accerder à toutes url contenant /donnees/ il faut se connecter et etre ADMIN
	             .antMatchers("/donnees/**").hasAnyRole("ADMIN")
	             .and()
	       .authorizeRequests()
	              //toutes les urls contenant /acces/ ne requeirt pas d'authentification
	              .antMatchers("/acces/**").permitAll()
	              //pour accerder aux restes des url contenant il faut juste  se connecter
	              // anyResuest doit etre toujout à la fin
		          .anyRequest().authenticated();
//	              .and()
//	         //formLogin() affiche le formulaire d'authentification     
//	     	.formLogin()
//	     	    .permitAll();
		
		//----------------------------------------------------------------------
//		http.cors();
//		http.csrf().disable();
//		  http.authorizeRequests().antMatchers("/**").fullyAuthenticated().and
//		 ().httpBasic(); 
		//----------------------------------------------------------------------
		
		
//		 hasAnyAuthority(("ADMIN"))
		 
//		    http.authorizeRequests().antMatchers("/donnees/**").authenticated().and().httpBasic().and().csrf().disable();

	   
	    
		 
//		 http.csrf().disable().authorizeRequests()
//        .antMatchers("donnees/**").hasAnyRole("ADMIN").anyRequest().fullyAuthenticated().and().formLogin();
		 
//		 http.csrf().disable().authorizeRequests()
//		 .antMatchers("/donnees/**").hasAnyRole("ADMIN","USER")
//         .antMatchers("/create/jeton","/login","static/css","static/js").permitAll()
//         .and().formLogin();
//        .defaultSuccessUrl("/welcome");
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//    CorsConfigurationSource corsConfigurationSource() 
//    {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
//        configuration.setAllowedMethods(Arrays.asList("OPTIONS", "HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
	
}
