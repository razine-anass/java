package org.sid;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Anass
 *
 *
 */
@Service
public class MonUserDetailsService implements UserDetailsService {
	
	@Autowired
    UserRepository userRepository;


	@Override
	public MyUserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		    Optional<Users> user = userRepository.findByUserName(userName);

	        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));

	        return user.map(MyUserDetails::new).get();
	}

}
