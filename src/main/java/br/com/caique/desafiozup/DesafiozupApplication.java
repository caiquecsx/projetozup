package br.com.caique.desafiozup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class DesafiozupApplication {

	private static Logger logger = LoggerFactory.getLogger(DesafiozupApplication.class);

	public static void main(String[] args) {
		logger.info("Iniciando API.");
		SpringApplication.run(DesafiozupApplication.class, args);
		logger.info("API iniciada.");
	}

}
