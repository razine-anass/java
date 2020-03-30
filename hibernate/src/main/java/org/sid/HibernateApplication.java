package org.sid;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.sid.entities.Book;
import org.sid.entities.Student;
import org.sid.repository.StudentRepository;
import org.sid.services.studentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HibernateApplication implements CommandLineRunner{
	
	@Autowired
	studentService studentService;
	
	@Autowired
	StudentRepository studentRepository;
	

	public static void main(String[] args) {
		SpringApplication.run(HibernateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
//		studentService.addStudent(new Student("anass", "razine", new Date(), null));
		List<Student> s =studentService.getStudent("razine");
		System.out.println("l'etudiant est"+s.toString());
		
        Book book = new Book();		
		book.setNom("science");
		book.setStudent(s.get(0));
		List<Book> books = new ArrayList<Book>();
		books.add(book);
		Student st = new Student(s.get(0).getId(), "razine", "elias", new Date(), books);
		
		Student st2 =studentService.updateStudent(st);
		System.out.println("l'etudiant est modifié"+st2.toString());
		
	}

}
