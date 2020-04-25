package org.sid.config.security;

import org.sid.services.MonUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
//@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigure extends WebSecurityConfigurerAdapter{

	@Autowired
	MonUserDetailsService monUserDetailsService;
	
	
	//gere l'authentification
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //on precise le type de la securité
		auth.userDetailsService(monUserDetailsService).passwordEncoder(passwordEncoder());
//		super.configure(auth);
		
//		auth.inMemoryAuthentication().withUser("anass").password("razine").roles("ADMIN");
//		auth.inMemoryAuthentication().withUser("abass").password("kajma").roles("USER");
//		
//		super.configure(auth);
	}
	
	//gere l'autorisation
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
//		http.csrf().disable();
//
//		http.authorizeRequests().antMatchers("/test/**").permitAll().and()
//				.authorizeRequests().antMatchers("/donnees/**").authenticated().anyRequest().hasAnyRole("ADMIN").and()
//				.formLogin().permitAll();
		

		//formLogin() affiche le formulaire d'authentification
		 http.csrf().disable();
		 http
		   .authorizeRequests()
		         .antMatchers("/test/**").hasAnyAuthority("USER","ADMIN")
		         .and()
		   .authorizeRequests()
	             .antMatchers("/donnees/**").hasAnyAuthority("ADMIN")
	             .anyRequest().authenticated()
	             .and()
	     	.formLogin()
	     	    .permitAll();
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
	
}
