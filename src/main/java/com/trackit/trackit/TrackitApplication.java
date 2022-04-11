package com.trackit.trackit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class TrackitApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrackitApplication.class, args);
	}

}
