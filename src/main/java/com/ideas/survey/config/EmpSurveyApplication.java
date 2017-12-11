package com.ideas.survey.config;

import java.io.Serializable;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication

@ComponentScan("com.ideas")
@EntityScan("com.ideas")
@EnableJpaRepositories(basePackages= "com.ideas")

public class EmpSurveyApplication {





	public static void main(String[] args)
	{
		SpringApplication.run(EmpSurveyApplication.class, args);
	}



}

