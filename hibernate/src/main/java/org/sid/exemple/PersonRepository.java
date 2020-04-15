package org.sid.exemple;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *  select_statement :: =
    [select_clause]
    from_clause
    [where_clause]
    [groupby_clause]
    [having_clause]
    [orderby_clause]
 *
 */

public interface PersonRepository extends JpaRepository<Person, Long>{
	//la requete retourn null si on fait pas left dans le cas ou aucun persone n'a au moins un telephone
	@Query("select distinct p from Person p left join fetch p.phones phones join fetch p.addresses adresses  where p.name like ?1"
			+ " and p.createdOn = ?2")
	Person chercherParName(String name,Date date);
	
	List<Person> chercherNamedQuerie(@Param("name") String name);
	
	@Modifying
	@Transactional
	@Query("Update Person p set p.nickName = ?2 where p.id = ?1 ")
	int modifier(Long id, String nom);
	
	@Query("select distinct p from Person p join fetch p.phones ph join fetch p.addresses adr where ph.type = ?1")
	List<Person> chercherParTypePhone(PhoneType type);
	
	@Query("select distinct p from Person p left join fetch p.phones ph join fetch p.addresses adr where ph is null")
	List<Person> chercherSansPhone();
	
   // une relation ManytoOne ou oneToOne n'a pas pas besoin de jointure ph.person n'est pas une collection donc on peut faire ph.person.address
   //par contre oneToMany ManyToMany a fortement besoin de join
	@Query("select ph from Phone ph where ph.person.address = ?1")
	List<Phone> cherchPhoneParAdress(String adress);
	
	@Query("select distinct ph from Phone ph join fetch ph.calls cl where cl.duration = ?2 and ph.person.address = ?1")
	List<Phone> cherchPhoneAdressDuration(String adress,int duration);
	
	// select all the calls (the map value) for a given Phone
//	"select ch from Phone ph join ph.callHistory ch where ph.id = :id"
	
	@Query("select cl from Phone ph join ph.calls cl where ph = ?1")
	List<Call> chercherCallsPhone(Phone phone);
	
	@Query("SELECT new map(ch) from Phone ph join ph.callHistory ch where ph = ?1")
	List<Map.Entry<Date, Call>> chercherHistoriqueCalls(Phone phone);
	
}
