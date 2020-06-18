package org.sid;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.sid.entities.Chantier;
import org.sid.entities.Facture;
import org.sid.entities.Tache;
import org.sid.enums.FactureStatut;
import org.sid.repositories.FactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//http://localhost:8082/actuator/refresh

// Spring GatWay utilise le spring Web flax single thread entré sortie non bloquante
//loqrsqu'on utilise service register signifie qu'on utilise la redirection dynamque -> le proxy utilise la non de service 
     //  et non l'adress ip 

@EnableDiscoveryClient
@SpringBootApplication
@EnableCaching
public class ElecBackApplication implements CommandLineRunner{
	
	@Autowired
	FactureRepository factureRepository;

	public static void main(String[] args) {
		SpringApplication.run(ElecBackApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
				.allowedOrigins("http://localhost:4200")
//				.allowedHeaders("*")
//				//preflight request
				.allowedMethods("OPTIONS", "HEAD", "GET", "POST", "PUT", "DELETE", "PATCH");
			}
		};
	}
	
	@Override
    public void run(String... args) throws Exception {
		
		Facture facture = new Facture();
		
		Chantier chantier = new Chantier();
		
		Tache tache1 = new Tache("changement inter-phone",50,chantier);
		//Tache tache2 = new Tache("installation climatiseur",500,chantier);
		//Tache tache3 = new Tache("Interepteur",30,chantier);
		//Tache tache4 = new Tache("installation Plaque",150,chantier);
		
		
		Set<Tache> taches = new HashSet<>();
		taches.add(tache1);
		//taches.add(tache2);
		//taches.add(tache3);
		//taches.add(tache4);
		
		chantier.setAdress("03 Allée Édouard Troia");
		chantier.setTache(taches);
		
		
		facture.setChantier(chantier);
		
		LocalDate currentdate = LocalDate.now();
		facture.setRef(currentdate+"");
		facture.setDate(currentdate);
		facture.setStatut(FactureStatut.NON_TRAITE);
    	
		//factureRepository.save(facture);
		
    	if(factureRepository.count() == 0) {
    		// save a list of Customers
    		
    		factureRepository.save(facture);
//    		factureRepository.saveAll(Arrays.asList(new Customer("Jack", "Smith"), 
//    										new Customer("Adam", "Johnson"), 
//    										new Customer("Kim", "Smith"),
//    										new Customer("David", "Williams"), 
//    										new Customer("Peter", "Davis")));
    	}

    }
}
