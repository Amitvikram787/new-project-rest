package com.cognizan.truyum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication

public class TruyumApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(TruyumApplication.class);

	public static void main(String[] args) {
	
		SpringApplication.run(TruyumApplication.class, args);
		LOGGER.info("loging from truyum application");
	}

}
