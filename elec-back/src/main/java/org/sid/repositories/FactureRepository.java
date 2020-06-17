package org.sid.repositories;

import java.util.Collection;
import java.util.List;

import org.sid.dto.FactureDto2;
import org.sid.entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FactureRepository extends JpaRepository<Facture, Long>{
	
	
	@Query("select distinct new org.sid.dto.FactureDto2(f.id,f.ref,f.chantier) from Facture f  left join f.chantier.taches t where f.id=:id")
	FactureDto2 findChantierDto(@Param("id") Long id);
	
	<T> Collection<T> findById(Long id, Class<T> type);
	
	@Query("select f from Facture f where f.id=:id")
	Facture chercher(@Param("id") Long id);
	
	@Query("select t.descriptif,sum(t.prix) as total from Facture f left join f.chantier.taches t where f.id=:id group by t.descriptif")
	Object[] chercherFactTotal(@Param("id") Long id);
	
	@Query("select t.descriptif,max(t.prix) as total from Facture f left join f.chantier.taches t where f.id=:id group by t.descriptif")
	Object[] tacheGrandPrix(@Param("id") Long id);
	
	@Query("select f.ref,t from Facture f left join f.chantier.taches t  where f.id=:id")
	List<?> chercher2(@Param("id") Long id);

}
