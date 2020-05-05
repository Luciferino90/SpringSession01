package it.arubapec.esecurity.springbootstarterapp;

import it.arubapec.esecurity.SpringBootStarterDemo.configuration.ClientTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootStarterAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootStarterAppApplication.class, args);
	}

	@Autowired(required = false)
	private ClientTemplate clientTemplate;

	@Bean
	CommandLineRunner run(){
		return args -> {
			clientTemplate.login();
		};
	}

}
