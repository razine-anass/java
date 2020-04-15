package org.sid.repository;

import org.sid.entities.Avion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AvionRepository extends JpaRepository<Avion, Long> {

	@Query("from Avion a where a.id = :id")
	Avion chercherAvion(@Param("id") Long id);
}
