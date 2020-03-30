package org.sid.services;

import java.util.List;

import org.sid.entities.Student;

public interface studentService {
	
	List<Student> getStudent(String nom);
	
	Student addStudent(Student s);
	
	Student updateStudent(Student s);

}
