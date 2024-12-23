// This code represents a basic Spring Boot application using Keycloak for authentication. 
// The main method upon execution, runs the SpringBootKeycloakApplication class, which starts up the Spring Boot application.
package com.example.spring_boot_keycloak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootKeycloakApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootKeycloakApplication.class, args);
	}

}
