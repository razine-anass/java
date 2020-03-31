package org.sid.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.sid.dto.BookDTO;
import org.sid.dto.Studentdto;
import org.sid.entities.Book;
import org.sid.entities.Student;
import org.sid.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * 
 * @author Razine Anass
 *
 */
@RestController
@RequestMapping("/student")
public class StudentRestController {
	
	@Autowired
	StudentService studentService;
	
	@Autowired
    private ModelMapper modelMapper;
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	@GetMapping("/students")
    @ResponseBody
    public List<Studentdto> getStudents() {
        
        List<Student>  students = studentService.findAll();
       
        return students.stream()
          .map(this::convertToDto)
          .collect(Collectors.toList());
    }
	
//	@GetMapping("/students")
//	public ResponseEntity<List<Student>> findAll(){
//		
//		HttpStatus status = null;
//		
//		ResponseEntity<List<Student>> response = null;
//		
//		List<Student>  students = studentService.findAll(); 
//		
//		if(students != null) {
//			response = new ResponseEntity<List<Student>>(students, status.OK);
//		} else {
//			response = new ResponseEntity<List<Student>>(new ArrayList<Student>(), status.BAD_REQUEST);
//		}
//		
//		return response;
//	}
	@RequestMapping("/get")
	public ResponseEntity<Student> findByName(
			@RequestParam("nomSt") String nom,
			@RequestParam("prenomSt") String prenom){
		
		ResponseEntity<Student> response = null;
		HttpStatus status = null;
		
		Student st = new Student();
		st.setNom(nom);st.setPrenom(prenom);
		
		response = new ResponseEntity<Student>(st,status.OK);
		
		return response;
	}
	
	@PostMapping("/create")
	public ResponseEntity<Student> createStudent(@RequestBody Studentdto studentdto) throws JsonProcessingException, ParseException{
		
		ResponseEntity<Student> response = null;
		HttpStatus status =  null;
		
		Student student = new Student();
		student.setPrenom(studentdto.getNom());
		student.setNom(studentdto.getPrenom());
		student.setDateNaissance(dateFormat.parse(studentdto.getDateNaissance()));
		Collection<BookDTO> l= studentdto.getBooks();
		Collection<Book> books= new ArrayList<Book>();
		student.setBooks(books);
		for(BookDTO bookdto:l){
			if(bookdto!=null) {
				Book book =  new Book();
				book.setNom(bookdto.getNom());
				book.setStudent(student);
				student.getBooks().add(book);
			}
		}
		
		Student st = studentService.create(student);
		
		if(st!=null){
			response = new ResponseEntity<Student>(st,status.OK);
		} else {
			response = new ResponseEntity<Student>(new Student(),status.BAD_REQUEST);
		}
		
		return response;
	}
	
	@GetMapping("/delete/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") Long id){
		
		ResponseEntity<String> response =  null;
		HttpStatus http = null;
		
		Student student = studentService.findById(id);
		if(student!=null) {
			studentService.deleteById(id);
		} else {
			response = new ResponseEntity<String>("l'objet n'existe pas",http.BAD_REQUEST);
		}
		return response;
//		try {
//			studentService.deleteById(id);
//		} catch (Exception e) {
//		   System.err.println("error");
//		}
//		System.out.println("anass");
	}
	
	@GetMapping("/page")
	public Page<Student> findStudentPage(@RequestParam("size") int size, @RequestParam("page")int page) {
		
		return studentService.findPaginated(page,size);
	}
	
	
	private Studentdto convertToDto(Student student) {
		Studentdto studenttDto = modelMapper.map(student, Studentdto.class);
		try {
			studenttDto.setSubmissionDate(studenttDto.getSubmissionDateConverted());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return studenttDto;
    }
    
 
//    @GetMapping
//    @ResponseBody
//    public List<PostDto> getPosts(
//            @PathVariable("page") int page,
//            @PathVariable("size") int size, 
//            @PathVariable("sortDir") String sortDir, 
//            @PathVariable("sort") String sort) {
//        
//        List<Post> posts = postService.getPostsList(page, size, sortDir, sort);
//        return posts.stream()
//          .map(this::convertToDto)
//          .collect(Collectors.toList());
//    }

}
