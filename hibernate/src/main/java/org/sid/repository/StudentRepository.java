package org.sid.repository;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.util.List;

import org.sid.dto.Studentdto;
import org.sid.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//dans une requete avec jointure il toujours utiliser distinct
//il faut pas utiliser fetche dans une requete ou on fait des calcul comme sum,count,.....
//il est conseillé d'utiliser le systeme de pagination dans les requetes pour charger progressivement les objets
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student>  {
	
	//select dto c'est plus rapide que de faire select d'une entité
	@Query("select distinct new org.sid.dto.Studentdto(s.id, s.nom, s.prenom) from Student s join s.books b where s.id = :id")
	List<Studentdto> chercherStudent(@Param("id") Long id);
	
//	"select distinct s from Student s,Book b where s.id = b.student,
//    countQuery ="select count(s) from Student s left join s.books".id"
	
	@EntityGraph(attributePaths = {"books"})//equivalent au fetch
	@Query(value="select s from Student s left join s.books")
	Page<Student> chercherStudentPage(Pageable pageable);
	
	//si on veut pas utilier @EntityGraph on doit utiliser countQuery il en a besoin pour compter
	@Query(value="select s from Student s left join s.books",
			countQuery ="select count(s) from Student s left join s.books")
	Page<Student> chercherStudentPageCount(Pageable pageable);
	
	//la pagination permet d'extraire progressivement les données
	@EntityGraph(attributePaths = {"books"})
	Page<Student> findAll(Pageable pageable);
//	
//	@Query(value = "select distinct s from Student s join fetch s.books",
//			countQuery ="select count(distinct s) from Student s join s.books")
//	Page<Student> chercherStudent(Pageable p);
	
	@Query("select count(distinct s) from Student s join s.books b where b.id is null")
	Long countStudent();
	
//	@Query("from Student s join fetch s.books book join fetch book.chapitres")
//	List<Student> chercherStudentFetch();
	
	@Query("from Student s join fetch s.books where s.id = :id")
	List<Student> chercherStudentSansFetch(@Param("id") Long id );
	
	
	@Query("SELECT nom, size(s.books) FROM Student s GROUP BY s.id")
	List<Object[]> groupBy(@Param("id") Long id );
	
}
