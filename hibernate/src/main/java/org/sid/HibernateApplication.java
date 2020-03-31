package org.sid;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.sid.entities.Book;
import org.sid.entities.Student;
import org.sid.repository.StudentRepository;
import org.sid.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HibernateApplication implements CommandLineRunner{
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	StudentRepository studentRepository;
	

	public static void main(String[] args) {
		SpringApplication.run(HibernateApplication.class, args);
	}
	
	@Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

	@Override
	public void run(String... args) throws Exception {
		
//		Student student  = new Student(null, "anass", "razine", new Date(), null);
//		
//        Book book = new Book();		
//		book.setNom("science");
//		
//		Book book1 = new Book();		
//		book1.setNom("religion");
//		
//		book.setStudent(student);
//		book1.setStudent(student);
//		List<Book> books = new ArrayList<Book>();
//		books.add(book);
//		books.add(book1);
//		student.setBooks(books);
//		
//		Student st2 =studentService.create(student);
//		System.out.println("l'etudiant est modifié"+st2.toString());
//		
	}

}
