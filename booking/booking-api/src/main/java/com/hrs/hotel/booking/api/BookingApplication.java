package com.hrs.hotel.booking.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
/**
 * Main application class for the Booking API.
 * This class is responsible for bootstrapping the Spring Boot application.
 */
@SpringBootApplication
public class BookingApplication {

	private final Logger logger = LoggerFactory.getLogger(BookingApplication.class);

	/**
	 * Main method to run the Spring Boot application.
	 *
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(BookingApplication.class, args);
	}

	/**
	 * CommandLineRunner bean to inspect the beans provided by Spring Boot.
	 *
	 * @param ctx the application context
	 * @return a CommandLineRunner instance
	 */
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			logger.debug("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				logger.debug(beanName);
			}
		};
	}
}