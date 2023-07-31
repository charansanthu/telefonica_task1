package com.wipro.telefonica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = "com.wipro.telefonica")
public class Task1Application {
	//hello
	public static void main(String[] args) {
		SpringApplication.run(Task1Application.class, args);
	}

}
