package org.sid.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.sid.dto.Studentdto;
import org.sid.entities.Book;
import org.sid.entities.Student;
import org.sid.repository.StudentRepository;
import org.sid.services.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	StudentRepository studentRepository;
	
	@Autowired
    private ModelMapper modelMapper;
	
	private static final  Logger log = LoggerFactory.getLogger(StudentRestController.class);

	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	@GetMapping("/")
    @ResponseBody
    public List<Studentdto> getStudents() {
        
        List<Student>  students = studentService.findAll();
       
        return students.stream()
          .map(this::convertToDto)
          .collect(Collectors.toList());
    }
	
	@RequestMapping("/get")
	public ResponseEntity<Student> findByName(
			@RequestParam("nomSt") String nom,
			@RequestParam("prenomSt") String prenom){
		
		log.debug("GET received - serializing nom et prenom: " + nom + " " + prenom);
		
		ResponseEntity<Student> response = null;
		HttpStatus status = null;
		
		Student st = new Student();
		st.setNom(nom);st.setPrenom(prenom);
		
		response = new ResponseEntity<Student>(st,status.OK);
		
		return response;
	}
	
	@PostMapping("/create")
	public ResponseEntity<Student> createStudent(final @RequestBody Studentdto studentdto) throws JsonProcessingException, ParseException{
		
		log.debug("POST received - serializing Studentdto: " + studentdto.toString());
		
		ResponseEntity<Student> response = null;
		HttpStatus status =  null;
		
		Student studentAuto = convertToEntity(studentdto);

        for(Book b:studentAuto.getBooks()){
        	b.setStudent(studentAuto);
        }
		
		Student st = studentService.create(studentAuto);
		
		if(st!=null){
			response = new ResponseEntity<Student>(st,status.CREATED);
		} else {
			response = new ResponseEntity<Student>(new Student(),status.NOT_FOUND);
		}
		
		return response;
	}
	
	@GetMapping("/delete/{id}")
	public ResponseEntity<String> deleteById(final @PathVariable("id") Long id){
		
		ResponseEntity<String> response =  null;
		HttpStatus http = null;
		
		Student student = studentService.findById(id);
		if(student!=null) {
			studentService.deleteById(id);
			response = new ResponseEntity<String>("l'objet n'existe pas",http.NO_CONTENT);
		} else {
			response = new ResponseEntity<String>("l'objet n'existe pas",http.NOT_FOUND);
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
	
	private Student convertToEntity(Studentdto studentdto) throws ParseException {
		
		Student student = modelMapper.map(studentdto, Student.class);
      
        return student;
    }

	@GetMapping("/test")
	public ResponseEntity<Student> getTestStudent() {
		
		Student std = studentRepository.findById(1L).get();
		
		return ResponseEntity.status(HttpStatus.OK).body(std);
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
