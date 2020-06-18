package org.sid.web;

import java.util.List;
import java.util.NoSuchElementException;

import org.sid.entities.Chantier;
import org.sid.entities.Users;
import org.sid.services.ChantierService;
import org.sid.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@RestController
@RequestMapping("/private")
public class UserController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
	UserService userService;  
    
    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody UserForm user){
    	
    	Users u = new Users();
    	
    	 try {
			u =userService.saveUser(user.getUsername(), user.getPassword(), user.getConfirmedPassword());
		} catch (Exception e) {
			log.error("----------ERROR>>>>>>>>>>>>>>>"+e);
			return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}
    	
    	 return ResponseEntity.status(HttpStatus.OK).body(u);
    }
    
    @GetMapping("/users")
    public List<Users> getAll(){
    	return  userService.findAlls();
    }
    
//    @GetMapping("{/user/{id}")
//    public Users loadUserByUsername(@PathVariable("id") Long id) {
//		return userService.loadUserByUsername("dfdf");
//	}
    
    @GetMapping("/utilisateur/{id}")
    Users getUserById(@PathVariable("id") String id) {
    	String nom = Thread.currentThread().getName();
    	Users user =  userService.loadUserByUsername(id);
    	user.setPassword(nom);
    	return user;
	}
    
    @GetMapping("/utilisateur/pageable")
	ResponseEntity<?> getUserPageable(@RequestParam(name="page",defaultValue="0")int page,
			                              @RequestParam(name="size",defaultValue="5")int size){
		
		Page<Users> users = userService.findPaginated(page, size);
		
		return ResponseEntity.status(HttpStatus.OK).body(users.getContent());
	}
    
    @DeleteMapping("/utilisateur/{id}")
    ResponseEntity<?> deleteUser(@PathVariable Long id){
    	Users u;
		try {
			u = userService.findById(id);
			userService.delete(u);
		} catch (Exception e) {
			log.error("problème lors du suppression d'un utilisateur",e);
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("problème lors du suppression d'un utilisateur");
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("problème lors du suppression d'un utilisateur");
    }
    
}
@Data
class UserForm{
	
	private String username;
	private String password;
	private String confirmedPassword;
}
