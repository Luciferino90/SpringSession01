package it.usuratonkachi.trial.solutions.fizzbuzz;

import it.usuratonkachi.trial.solutions.GenericRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

/**
 * Write a program that prints one line for each number from 1 to 100
 * For multiples of three print Fizz instead of the number
 * For the multiples of five print Buzz instead of the number
 * For numbers which are multiples of both three and five print FizzBuzz instead of the number
 */
@Service
public class FizzBuzz implements GenericRunner {

    @Override
    public void run(String... args) {
        IntStream.rangeClosed(1, 100)
                .mapToObj(counter -> {
                    boolean isFizz = counter%3 == 0;
                    boolean isBuzz = counter%5 == 0;
                    String toPrint;
                    if (isBuzz && isFizz)
                        toPrint = "FizzBuzz";
                    else if (isFizz)
                        toPrint = "Fizz";
                    else if (isBuzz)
                        toPrint = "Buzz";
                    else
                        toPrint = String.valueOf(counter);
                    return toPrint;
                })
                .forEach(System.out::println);
    }

}
