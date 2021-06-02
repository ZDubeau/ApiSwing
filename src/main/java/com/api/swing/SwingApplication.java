package com.api.swing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.*")
@EnableJpaRepositories("com.api.swing.repository")
@EntityScan("com.api.swing.model")
public class SwingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwingApplication.class, args);
	}
}