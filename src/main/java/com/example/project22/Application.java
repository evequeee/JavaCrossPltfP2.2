package com.example.project22;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.project22", "com.example.user", "com.example.config", "ua.edu.chnu"})
@EntityScan(basePackages = {"com.example.project22.model", "com.example.user", "ua.edu.chnu"})
@EnableJpaRepositories(basePackages = {"com.example.project22.repository", "com.example.user", "ua.edu.chnu"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
