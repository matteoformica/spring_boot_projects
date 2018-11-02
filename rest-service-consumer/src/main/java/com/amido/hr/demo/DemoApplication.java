package com.amido.hr.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * This application consumes a 3rd party REST service, and shows the data retrieved from it. 
 * @author Matteo.Formica
 *
 */

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
