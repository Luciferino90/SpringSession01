package it.arubapec.esecurity.springbootstarterapp;

import it.arubapec.esecurity.SpringBootStarterDemo.configuration.ClientTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "it.arubapec.esecurity")
public class SpringBootStarterAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootStarterAppApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ApplicationContext context, ClientTemplate client){
		return args -> {
			client.login();
			SpringApplication.exit(context, () -> 0);
		};
	}

}
