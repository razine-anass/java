package org.sid.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MonUserDetails implements UserDetails {

	private static final long serialVersionUID = -4119511463693740583L;
	
	private Users user = new Users();
	
//	private List<GrantedAuthority> authorities;
	
	public MonUserDetails() {
		super();
	}

//	public MonUserDetails(Users user) {
//
//	   this.user.setId(user.getId());	
//       this.user.setUsername(user.getUsername()); 
//       this.user.setPassword(user.getPassword());
//       this.user.setRoles(user.getRoles());
//       this.authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getNom()))
//				.collect(Collectors.toList());
//    }
	
//	 @Override
//	    public Collection<? extends GrantedAuthority> getAuthorities() {
//	        return authorities;
//	    }
//	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
//très important
//pour utiliser les hasAnyRole      il faut rajouter "ROLE_" ----> new SimpleGrantedAuthority("ROLE_" + role.getNom())) ou l'enregister dans la base de données
		                                    
//pour utiliser les hasAnyAuthority il faut enlever  "ROLE_" ----> new SimpleGrantedAuthority(role.getNom()))
		return user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getNom()))
				.collect(Collectors.toList());

	}


	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "MonUserDetails [user=" + user + "]";
	}

	
}
