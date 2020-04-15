package org.sid.repository;

import org.sid.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// dans le cas d'une relation ManyToMany pour charger un objet et son contenu avec une jointure il faut utiliser obligatoirement fetch sinon ça marche 
public interface ModuleRepositroy extends JpaRepository<Module, Long>{
	
	@Query("from Module m join fetch m.etudiants where m.id = :id")
	Module chercherModule(@Param("id") Long id);

}
