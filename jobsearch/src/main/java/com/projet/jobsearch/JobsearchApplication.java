package com.projet.jobsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@ComponentScan
@SpringBootApplication
public class JobsearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobsearchApplication.class, args);
	}
}
