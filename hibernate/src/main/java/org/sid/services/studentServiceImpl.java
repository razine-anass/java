package org.sid.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.sid.entities.Student;
import org.sid.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class studentServiceImpl implements studentService{
	
	 @PersistenceContext
	 EntityManager entityManager;
	 
	 @Autowired
	 StudentRepository studentRepository;
	 
	 
	 @Override
	public Student addStudent(Student s) {
		 
		Student student = studentRepository.save(s);
		return student;
	}

	@Override
	@Transactional(readOnly = true,isolation = Isolation.READ_UNCOMMITTED,timeout = 30)
	public List<Student> getStudent(String nom) {

        String strQuery = "select s from Student s where s.nom = :nom";

        Query globalQuery = entityManager.createQuery(strQuery);
        
        globalQuery.setParameter("nom", nom);
        
        System.out.println(strQuery);
        
        List<Student> students =  globalQuery.getResultList();
		
		return students;
	}

	@Override
	public Student updateStudent(Student s) {
		
		Student student = studentRepository.getOne(s.getId());
		
		student.setBooks(s.getBooks());
		student.setDateNaissance(s.getDateNaissance());
		student.setNom(s.getNom());
		student.setPrenom(s.getPrenom());
		
		Student studentUdated = studentRepository.save(student);
		
		return studentUdated;
	}
}
