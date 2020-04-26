package org.sid.repositories;

import org.sid.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsersRepository extends JpaRepository<Users, Long>{
	
	
	@Query("select us from Users us left join fetch us.roles where us.username = ?1")
	 Users findByUsername(String username);

}
