package org.sid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

//http://localhost:8080/elec-back/public/param

//le proxy utilise le system d'equilbrage de charge quand il ya plusieur instance disponible
// il partage la charge sur  toute les instance

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceProxyApplication.class, args);
	}

}
