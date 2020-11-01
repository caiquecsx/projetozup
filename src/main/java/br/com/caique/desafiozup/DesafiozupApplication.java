package br.com.caique.desafiozup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafiozupApplication {

	private static Logger logger = LoggerFactory.getLogger(DesafiozupApplication.class);

	public static void main(String[] args) {
		logger.info("Iniciando API.");
		SpringApplication.run(DesafiozupApplication.class, args);
		logger.info("API iniciada.");
	}

}
