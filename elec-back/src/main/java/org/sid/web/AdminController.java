package org.sid.web;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.sid.entities.Role;
import org.sid.entities.Users;
import org.sid.repositories.UsersRepository;
import org.sid.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@PostMapping("/users")
	ResponseEntity<?> createUser(@RequestBody Users user){
		
		String pswd = bCryptPasswordEncoder.encode(user.getPassword());
	
		
		Set<Role> roles = new HashSet<Role>();
		for(Role r : user.getRoles()){
			Role role = new Role();
			role.setNom(r.getNom());
			role.setUser(user);
			roles.add(role);
			}
//		Role role = new Role();
//		role.setNom(user.getRoles().iterator().next().getNom());
//		role.setUser(user);
//		roles.add(role);
		
		user.setRoles(roles);
		
		user.setPassword(pswd);
		
		Users us = userService.save(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(us);
	}

}
