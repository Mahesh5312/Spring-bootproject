package com.hcl.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.apache.logging.log4j.LogManager;

/**
 * The Class Application.
 */



@SpringBootApplication
@EnableAspectJAutoProxy
public class Application {

private static final Logger log = LogManager.getLogger(Application.class);
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	
}