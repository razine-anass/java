package org.sid.repositories;

import java.util.Optional;

import org.sid.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long>{
	
//	@Query("select us from Users us  join fetch us.roles where us.username = ?1")
	 Optional<Users> findByUsername(String username);

}
