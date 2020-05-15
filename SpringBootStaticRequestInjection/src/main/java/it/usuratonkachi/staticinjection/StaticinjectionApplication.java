package it.usuratonkachi.staticinjection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class StaticinjectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(StaticinjectionApplication.class, args);
	}

}
