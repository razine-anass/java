package org.sid.repository;

import java.util.List;

import org.sid.entities.Book;
import org.sid.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> , JpaSpecificationExecutor<Book> {
	
//	@Query("from Book b where b.student.id = :idB")
//	List<Book> chercherBook(@Param("idB") Long id);
	
//	@Query("from Book b where b.student.nom = :idB")
//	List<Book> chercherBook(@Param("idB") String id);

	@Query("from Book b join fetch b.student std where b.id = :idB")
	List<Book> chercherBook(@Param("idB") Long l);
}
