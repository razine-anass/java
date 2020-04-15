package org.sid;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.sid.exemple.AddressType;
import org.sid.exemple.Call;
import org.sid.exemple.CallRepository;
import org.sid.exemple.Person;
import org.sid.exemple.PersonRepository;
import org.sid.exemple.Phone;
import org.sid.exemple.PhoneType;
import org.sid.repository.AvionRepository;
import org.sid.repository.BookRepository;
import org.sid.repository.EtudiantRepository;
import org.sid.repository.ModuleRepositroy;
import org.sid.repository.PiloteRepository;
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
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	AvionRepository avionRepository;
	
	@Autowired
	PiloteRepository piloteRepository;
	
	@Autowired
	EtudiantRepository etudiantRepository;
	
	@Autowired
	ModuleRepositroy moduleRepositroy;
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	CallRepository callRepository;
	

	public static void main(String[] args) {
		SpringApplication.run(HibernateApplication.class, args);
	}
	
	@Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

	@Override
	public void run(String... args) throws Exception {

	//-----------------------------------------------Pagination------------------------------------------------------	
//        Pageable triByNom = null;
//        Page<Student> studentPage = null;
//        List<Student> listStudent = null;
////
//        Pageable triByPrenom = PageRequest.of(0, 2, Sort.by("prenom"));
//
//        Pageable triByPrenomeDescNomAsc = PageRequest.of(0, 2, Sort.by("nom").descending().and(Sort.by("prenom")).ascending());
//        
//        for(int i = 0;i<2;i++){
//        	triByNom = PageRequest.of(i, 2, Sort.by("nom"));
//        	studentPage = studentRepository.chercherStudentPage(triByNom);
//            
//            listStudent = studentPage.getContent();
//    		
//    		listStudent.forEach(
//    				item->{
//    					System.out.println(item);
//    					 System.out.println("version: " + SpringVersion.getVersion());
//    				});
//        }
//        
//        List<Object[]> a = studentRepository.groupBy(1L);
//        
//        for(int i=0;i<a.size();i++){
//        	System.out.println("nom : "+a.get(i)[0]+" nombre : "+a.get(i)[1]);
//        }
//      Page<Student> s = studentRepository.findAll(triByPrenom);
//     List<Student> lisStudent = s.getContent();
//     lisStudent.forEach(
//				item->{System.out.println(item);});
        
//--------------------------------Example-------------------------------------------------- 
		Person p = new Person();
		
		String date1 = "22/06/2009";
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    Date date = null;
	    date = simpleDateFormat.parse(date1);
	    
		
		Map<AddressType, String> addresses = new  HashMap<>();
		addresses.put(AddressType.HOME, "home2");
		addresses.put(AddressType.OFFICE, "office2");
		
		List<Phone> phones = new ArrayList<>();
		
		Phone phone =new Phone();
		phone.setNumber("0689");
		phone.setPerson(p);
		
		Phone phone2 =new Phone();
		phone2.setNumber("0625");
		phone2.setPerson(p);
		
		phones.add(phone2);
		phones.add(phone);
		
		
		
		p.setName("allal");
		p.setNickName("lkhobza");
		p.setCreatedOn(date);
		p.setAddresses(addresses);
		p.setPhones(phones);
		
//		personRepository.save(p);
//		Person person = personRepository.chercherParName("ji%",date);
//		List<Person> person = personRepository.chercherParTypePhone(PhoneType.LAND_LINE);
//		Integer i = personRepository.modifier(11L, "lmango");
		
		phone2.setId(14L);

//		List<Call> calls = personRepository.chercherCallsPhone(phone2);
//		calls.forEach(item->{
//			System.out.println(item);
//			});
//		{bleu=0000FF, vert=00FF00, rouge=FF0000}
		List<Map.Entry<Date, Call>> lit = personRepository.chercherHistoriqueCalls(phone2);
		final Map<String, String> map = new HashMap<String, String>();

		map.put("rouge", "FF0000");
		map.put("vert", "00FF00");
		map.put("bleu", "0000FF");
		for(final Entry<String, String> entry : map.entrySet()) {
		    final String key =  entry.getKey();
		    final String value =  entry.getValue();

		    // Traitement key-value
		   
		}
		
		
//		Set map = ((Map) lit.get(0)[0]).;
	
		System.out.println(map.size());
		Call c = (Call)lit.get(0).getValue();
		System.out.println(c);
//		for (Entry<Date, Call> entry: lit){
//		   
//			System.out.println(entry.entrySet()+" "+ entry.getValue().toString()); 
//			}

		
	}

}
