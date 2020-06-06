package it.usuratonkachi.trial.solutions;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

/**
 * Write a program that prints one line for each number from 1 to 100
 * For multiples of three print Fizz instead of the number
 * For the multiples of five print Buzz instead of the number
 * For numbers which are multiples of both three and five print FizzBuzz instead of the number
 */
public interface GenericRunner {

    void run(String... args);

}
