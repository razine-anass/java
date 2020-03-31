package org.sid.services.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.sid.entities.Student;
import org.sid.repository.StudentRepository;
import org.sid.services.StudentService;
import org.sid.services.common.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Razine Anass
 *
 */
@Service
@Transactional
public class studentServiceImpl extends AbstractService<Student> implements StudentService{
	
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

//	@Override
//	public List<Student> findAll() {
//		
//		List<Student> students = studentRepository.findAll();
//		
//		return students;
//	}
	
	
	/*---------------------------------------------------------------*/

	@Override
    protected PagingAndSortingRepository<Student, Long> getDao() {
        return studentRepository;
    }

    // custom methods

    @Override
    public Page<Student> findPaginated(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }
	
}
