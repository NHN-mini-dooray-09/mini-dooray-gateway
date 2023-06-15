package com.nhnacademy.minidooray;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class MinidoorayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinidoorayApplication.class, args);
	}

}
