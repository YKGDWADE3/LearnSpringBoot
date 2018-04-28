package com.example.LearnSpringBoot;

import controller.EmployeeController;
import controller.HelloController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearnSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeController.class, args);
	}


}
