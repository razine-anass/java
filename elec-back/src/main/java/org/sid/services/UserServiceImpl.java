package org.sid.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sid.entities.Users;
import org.sid.repositories.UsersRepository;
import org.sid.services.common.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends AbstractService<Users> implements UserService {

	@PersistenceContext
	EntityManager entityManager;
	 
	@Autowired
	UsersRepository usersRepository;
	
	@Override
    protected JpaRepository<Users, Long> getDao() {
        return usersRepository;
    }

}
