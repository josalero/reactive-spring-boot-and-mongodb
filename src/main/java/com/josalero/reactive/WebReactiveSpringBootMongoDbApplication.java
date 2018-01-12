package com.josalero.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
//@EnableWebFluxSecurity
public class WebReactiveSpringBootMongoDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebReactiveSpringBootMongoDbApplication.class, args);
	}
}
