package it.arubapec.esecurity.SpringBootStarterDemo;

import it.arubapec.esecurity.SpringBootStarterDemo.configuration.ClientProperties;
import it.arubapec.esecurity.SpringBootStarterDemo.configuration.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
@ConfigurationPropertiesScan
public class SpringBootStarterDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootStarterDemoApplication.class, args);
	}

	@Autowired(required = false) private ClientService clientService;
	@Autowired(required = false) private ClientProperties clientProperties;
	@Bean CommandLineRunner run(){ return args -> {
		if (clientService == null)
			log.error("No client service initialized");
		else
			clientService.login();
	};}

}
