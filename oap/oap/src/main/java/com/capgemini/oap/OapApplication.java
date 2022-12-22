package com.capgemini.oap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")

@SpringBootApplication
public class OapApplication {
	public static void main(String[] args) {
		SpringApplication.run(OapApplication.class, args);
	}

}
