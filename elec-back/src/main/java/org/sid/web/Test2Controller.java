package org.sid.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//permet de rafraichir les les attribus annoté avec @value
@RefreshScope
@RestController
@RequestMapping("/api/public")
public class Test2Controller {
       //variable d'envirenement
		@Value("${xParam}")
		int xParam;
	
		@GetMapping("/user")
		public String test(){
			return "public ";
		}
		
		
		 
		@GetMapping("/param")
		public Map<String,Object> getParam(){
			Map<String,Object> params = new HashMap<>(); 
			params.put("xParam", xParam);
			//on recupere le thread cree par tomcat au lancement tomcat cree 10 thread qui permet d'etablir une connexion en 10 client et le serveur
			//NodeJs cree un seul Thread pour repondre à tte les requetes 
			//Spring 5 a introduit Spring-Web-Flax: programation réactive->cree un seul thread
			params.put("thread Name", Thread.currentThread().getName());
			return params;
		}
		
		@GetMapping("/public/admin")
		public String teste(){
			return "public admin";
		}

	}
