package org.sid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//configuration web security
@EnableWebSecurity
public class SecurityConfigure extends WebSecurityConfigurerAdapter{

	@Autowired
	MonUserDetailsService monUserDetailsService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	//gere l'authentification
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //on precise le type de la securité
		
		auth.userDetailsService(monUserDetailsService);
		//TODO
//		auth.userDetailsService(monUserDetailsService).passwordEncoder(bCryptPasswordEncoder());
		super.configure(auth);
	}
	
//	 @Override
//	    protected void configure(HttpSecurity http) throws Exception {
//	        http.authorizeRequests()
//	                .antMatchers("/admin").hasRole("ADMIN")
//	                .antMatchers("/user").hasAnyRole("ADMIN", "USER")
//	                .antMatchers("/").permitAll()
//	                .and().formLogin();
//	    }
	
	//gere l'autorisation
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable().authorizeRequests()
//		                                        .antMatchers("/admin").hasRole("ADMIN")
//                                                .antMatchers("/user").hasAnyRole("ADMIN","USER")
//		                                        .antMatchers("/autentification").permitAll()
//		                                        .anyRequest().authenticated().and()
//	   .exceptionHandling().and().sessionManagement()
//       .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		//.anyRequest().authenticated()
		 http.csrf().disable().authorizeRequests()
        .antMatchers("/admin").hasAuthority("ADMIN")
        .antMatchers("/user").hasAnyAuthority("ADMIN","USER")
        .antMatchers("/create/jeton","/login","static/css","static/js").permitAll()
        .and().formLogin()
        .defaultSuccessUrl("/welcome");
		 
			//loginPage("/login"). permet de d'acceder à la page login
//		 http.csrf().disable().authorizeRequests()
//        .antMatchers("/admin").hasAuthority("ADMIN")
//        .antMatchers("/user").hasAnyAuthority("ADMIN","USER")
//        .antMatchers("/login","static/css","static/js").permitAll()
//        .anyRequest().authenticated()
//		 .and().formLogin().defaultSuccessUrl("/welcome").failureUrl("/login?error=true").permitAll()
//		 .and().logout().deleteCookies("JSESSIONID").logoutUrl("/logout").logoutSuccessUrl("/login"); 
		 
		
		//authentification avec jeton
//		http.csrf().disable().authorizeRequests()
//        .and()
//        .exceptionHandling().and().sessionManagement()
//        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		
//		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	
	@Bean
	PasswordEncoder passwordEncoder() {
		// il faut pas utiliser: Spring n'utilise pas de fonction hachage pour lire le password
		//parce que le password n'est pas encode, dans cas reel il faut l'encode et utiliser une fontion de hachage
		return NoOpPasswordEncoder.getInstance();
	}
	
	//TODO
//	@Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
