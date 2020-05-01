package it.arubapec.esecurity.springbootbuilder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class SpringBootBuilderApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(SpringBootBuilderApplication.class)
				.bannerMode(Banner.Mode.OFF)
				.logStartupInfo(false)
				.build()
				.run(args);
	}

}

@Slf4j
@Component
@RequiredArgsConstructor
class Runner {

	private final ApplicationContext applicationContext;

	@Bean public CommandLineRunner run() {
		return args -> {
			log.info("Starter");
			SpringApplication.exit(applicationContext, () -> 0);
		};
	}

}