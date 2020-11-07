package br.com.caique.desafiozup;

import io.jaegertracing.Configuration;
import io.jaegertracing.internal.samplers.ConstSampler;
import io.opentracing.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class DesafiozupApplication {

	private static Logger logger = LoggerFactory.getLogger(DesafiozupApplication.class);

	@Bean
	public Tracer tracer() {
		Configuration.SamplerConfiguration samplerConfig = Configuration.SamplerConfiguration.fromEnv()
				.withType(ConstSampler.TYPE)
				.withParam(1);

		Configuration.ReporterConfiguration reporterConfig = Configuration.ReporterConfiguration.fromEnv()
				.withLogSpans(true);

		Configuration config = new Configuration("zup-desafio")
				.withSampler(samplerConfig)
				.withReporter(reporterConfig);

		return config.getTracer();
	}

	public static void main(String[] args) {
		logger.info("Iniciando API.");
		SpringApplication.run(DesafiozupApplication.class, args);
		logger.info("API iniciada.");


	}

}
