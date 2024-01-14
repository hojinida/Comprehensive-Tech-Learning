package com.demo.initial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.TimeZone;

@EnableJpaAuditing
@SpringBootApplication
public class InitialApplication {
	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("KST"));
		SpringApplication.run(InitialApplication.class, args);


	}
}
