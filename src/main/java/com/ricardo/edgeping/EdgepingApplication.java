package com.ricardo.edgeping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EdgepingApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdgepingApplication.class, args);
	}

}
