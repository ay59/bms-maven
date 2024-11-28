package com.theatres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.theatres"})
public class TheatresApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheatresApplication.class, args);
	}

}
