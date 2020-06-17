package org.sid.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.sid.entities.Role;
import org.sid.entities.Users;
import org.sid.repositories.RoleRepository;
import org.sid.repositories.UsersRepository;
import org.sid.services.common.AbstractService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
//@CacheConfig(cacheNames = "user")
public class UserServiceImpl extends AbstractService<Users> implements UserService {

	@PersistenceContext
	EntityManager entityManager;
	 
	UsersRepository usersRepository;
	
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	RoleRepository roleRepository;
	
	
	@Override    
	//@Cacheable()
    public List<Users> findAlls() {
      return  usersRepository.findAll();
    }

	
	// injection par contructeur 
	public UserServiceImpl(UsersRepository usersRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
			RoleRepository roleRepository) {
		super();
		this.usersRepository = usersRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.roleRepository = roleRepository;
	}

	@Override
    protected JpaRepository<Users, Long> getDao() {
        return usersRepository;
    }

	@Override
	public Users saveUser(String username, String password, String confirmedPassword) {
		
		Users u = usersRepository.findByUsername(username);
		if(u != null) throw new RuntimeException("cet utilisateur exist déjà");
		if(!password.equals(confirmedPassword)) throw new RuntimeException("Confirmer votre mot de passe");
		Users user = new Users();
		user.setUsername(username);
		user.setPassword(bCryptPasswordEncoder.encode(password));
		usersRepository.save(user);
		addRoleToUser(username, "ROLE_USER");
		
		return user;
	}

	@Override
	public Role save(Role role) {
		return roleRepository.save(role);
	}

	@Override
	//@Cacheable()
	public Users loadUserByUsername(String usename) {
		return usersRepository.findByUsername(usename);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		Users u = usersRepository.findByUsername(username);
		Role r = new Role();
		r.setNom("ROLE_USER");
		r.setUser(u);
		u.getRoles().add(r);
	}

}
