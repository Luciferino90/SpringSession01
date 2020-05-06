package it.arubapec.esecurity.springbootbuilder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class SpringBootBuilderApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(SpringBootBuilderApplication.class)
				.bannerMode(Banner.Mode.OFF)
				.logStartupInfo(false)
				.build()
				.run(args);
	}


	@Bean public CommandLineRunner run(ApplicationContext applicationContext) {
		return args -> {
			log.info("Started");
			SpringApplication.exit(applicationContext, () -> 0);
		};
	}

}
