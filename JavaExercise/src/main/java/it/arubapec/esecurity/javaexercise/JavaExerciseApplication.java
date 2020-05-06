package it.arubapec.esecurity.javaexercise;

import it.arubapec.esecurity.javaexercise.utilities.ExerciseUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JavaExerciseApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaExerciseApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ApplicationContext context) {
		return args -> {
			System.out.println("===== Fibonacci Series ===== ");
			ExerciseUtils.fibonacciSeries(10);

			System.out.println("\n\n\n");

			System.out.println("===== Is prime ===== ");
			ExerciseUtils.isPrime(255);

			System.out.println("\n\n\n");
			System.out.println("===== Reversed ===== ");
			ExerciseUtils.reverseString("palindromone");

			System.out.println("\n\n\n");
			System.out.println("===== Reversed Check ===== ");
			ExerciseUtils.isReversed("uno", "due");
			ExerciseUtils.isReversed("trentasette", "due");
			ExerciseUtils.isReversed("uno", "trentasette");
			ExerciseUtils.isReversed("palindromone", "enomordnilap");

			System.out.println("\n\n\n");
			System.out.println("===== Is Palindrome Check ===== ");
			ExerciseUtils.isPalindrome("Bob");
			ExerciseUtils.isPalindrome("Jack");
			ExerciseUtils.isPalindrome(101);
			ExerciseUtils.isPalindrome(1002);

			System.out.println("\n\n\n");
			System.out.println("===== Is Armstrong Number ===== ");
			ExerciseUtils.isArmstrongNumber(153);
			ExerciseUtils.isArmstrongNumber(154);

			System.out.println("\n\n\n");
			System.out.println("===== Is Factorial Number ===== ");
			ExerciseUtils.factorial(7);
			ExerciseUtils.factorial(20);

			SpringApplication.exit(context, () -> 0);
		};
	}

}
