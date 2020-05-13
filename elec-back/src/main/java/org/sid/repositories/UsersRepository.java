package org.sid.repositories;

import org.sid.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsersRepository extends JpaRepository<Users, Long>{
	
	
	//il faut utilier fetche pour charger les roles et le left pour charger tout les users
	//(car certain user n'ont pas de roles et join unique charge uniquement les users ayant au moins un role) 
	//qui repond aux critere du where
	@Query("select us from Users us left join fetch us.roles where us.username = ?1")
	 Users findByUsername(String username);

}
