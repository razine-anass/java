package org.sid.repository;

import org.sid.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
	
	@Query("from Etudiant e join fetch e.modules where e.id = :id")
	Etudiant chercherEtudiant(@Param("id") Long id);

}
