package it.usuratonkachi.trial;

import it.usuratonkachi.trial.solutions.statemachine.medium.StateMachineApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class TrialApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrialApplication.class, args);
	}

	private final StateMachineApplication stateMachineRunner;

	@Bean
	CommandLineRunner runner() {
		return stateMachineRunner::run;
	}

}
