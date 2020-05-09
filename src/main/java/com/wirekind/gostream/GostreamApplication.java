package com.wirekind.gostream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class GostreamApplication {
	
	@RequestMapping("/")
	public String index() {
		return "Welcome to GoStream Server...";
	}

	public static void main(String[] args) {
		SpringApplication.run(GostreamApplication.class, args);
	}

}
