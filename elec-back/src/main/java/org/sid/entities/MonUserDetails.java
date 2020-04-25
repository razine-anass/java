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
	
	private List<GrantedAuthority> authorities;
	
	public MonUserDetails() {
		super();
	}

	public MonUserDetails(Users user) {
		
//		List<String> list = new ArrayList<String>();
//		
//		for(Role r :user.getRoles() ){
//			list.add(r.getNom());
//		}
//		
		
        this.user.setUsername(user.getUsername()); 
        this.user.setPassword(user.getPassword());
        this.authorities = Arrays.stream(user.getRoles().iterator().next().getNom().split(","))
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
    }
	
	 @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        return authorities;
	    }
	
	

//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		
//		List<GrantedAuthority> users= user.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role))
//				.collect(Collectors.toList());
//		return users;
//	}


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
}
