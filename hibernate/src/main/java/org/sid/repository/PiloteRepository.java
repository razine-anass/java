package org.sid.repository;

import org.sid.entities.Pilote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PiloteRepository extends JpaRepository<Pilote, Long>{
	
	@Query("from Pilote p join p.avion where p.id = :id")
	Pilote chercherPilote(@Param("id") Long id);

}
