package com.ayadykin.message.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class MessageSystem extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MessageSystem.class, args);
	}
}
