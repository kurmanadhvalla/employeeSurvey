package com.ideas.survey.empSurvey;

import java.io.Serializable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.ideas")
@EntityScan("com.ideas")
@EnableJpaRepositories(basePackages= "com.ideas")

public class EmpSurveyApplication implements Serializable {

	public static void main(String[] args) {
		SpringApplication.run(EmpSurveyApplication.class, args);
	}
}
