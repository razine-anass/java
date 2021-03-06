package org.sid.services;


import java.util.Optional;

import org.sid.entities.MonUserDetails;
import org.sid.entities.Users;
import org.sid.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MonUserDetailsService implements UserDetailsService {
	
	@Autowired
	UsersRepository usersRepository;

	@SuppressWarnings("unused")
	@Override
	//recuper by username les données relatives à l'utilisateur qui tente de s'authentifier
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = usersRepository.findByUsername(username);
		MonUserDetails userDetails = null;
		if(user!=null){
		    userDetails = new MonUserDetails();
			userDetails.setUser(user);
		} else {
			throw new UsernameNotFoundException("cet utilisateur n'existe pas");
		}
		System.out.println(userDetails.getAuthorities());
		// on cree notre userDetails, spring security recuper ce dernier et comparere 
		//        son passowrd avec celui du user qui tente de s'authentifier
		return userDetails;
	}

}
