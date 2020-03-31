package org.sid.services;

import java.util.List;

import org.sid.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService extends IOperations<Student> {
	
	List<Student> getStudent(String nom);
	
	List<Student> findAll();
	
	Student addStudent(Student s);
	
	Student updateStudent(Student s);
	
	Page<Student> findPaginated(Pageable pageable);

}
