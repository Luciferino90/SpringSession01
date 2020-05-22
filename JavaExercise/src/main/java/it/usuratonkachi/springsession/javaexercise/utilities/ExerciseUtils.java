package it.usuratonkachi.springsession.javaexercise.utilities;

import lombok.experimental.UtilityClass;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@UtilityClass
public class ExerciseUtils {

    // Fibonacci
    public void fibonacciSeries(int limit) {
        String series = IntStream.rangeClosed(1, limit)
                //.forEach(el -> System.out.println(nextFibonacci(el)));
                .mapToObj(ExerciseUtils::nextFibonacci)
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        System.out.println(series);

    }

    private int nextFibonacci(int n) {
        return (n < 3) ? 1 : nextFibonacci(n-1) + nextFibonacci(n-2);
    }

    // Prime Number
    public void isPrime(int number) {
        List<Integer> completeList = IntStream.rangeClosed(2, number).boxed().collect(Collectors.toList());
        IntStream.range(2, number).forEach(el -> completeList.removeIf(n -> n % el == 0));
        System.out.println(String.format("%s %s", number, completeList.size() == 0 ? "is not prime" : "is prime"));
    }

    // Palindrome String, no utils
    public void reverseString(String value) {
        LinkedList<String> characters = new LinkedList<>();
        value.chars().mapToObj(a -> String.valueOf((char)a)).forEach(characters::addFirst);
        System.out.println(
                String.format("%s reversed is %s",
                        value,
                        String.join("", characters)
                )
        );
    }

    public void isReversed(String valueOne, String valueTwo){
        if (valueOne.length() != valueTwo.length()) {
            System.out.println(String.format("%s is not the reversed of %s", valueOne, valueTwo));
            return;
        }
        int size = valueOne.length() - 1;
        for(int counter = 0; counter <= size; counter++) {
            if (valueOne.charAt(counter) != valueTwo.charAt(size - counter)){
                System.out.println(String.format("%s is not the reversed of %s", valueOne, valueTwo));
                return;
            }
        }
        System.out.println(String.format("%s is the reversed of %s", valueOne, valueTwo));
    }

    public void isPalindrome(String tocheck) {
        int size = tocheck.length() - 1;
        for (int counter = 0; counter <= size; counter++) {
            if (tocheck.toLowerCase().charAt(counter) != tocheck.toLowerCase().charAt(size - counter)) {
                System.out.println(String.format("%s is not a palindrome", tocheck));
                return;
            }
        }
        System.out.println(String.format("%s is a palindrome", tocheck));
    }

    public void isPalindrome(int number) {
        isPalindrome(String.valueOf(number));
    }

    public void isArmstrongNumber(int number){
        int boxed = String.valueOf(number).chars().boxed().map(Character::getNumericValue).mapToInt(a -> a * a * a).sum();
        boolean isArm = boxed == number;
        System.out.println(String.format("%d %s", number, isArm ? "is an Armstrong number" : "is not an Armstrong number"));
    }

    public void factorial(int number) {
        long factorial = IntStream.rangeClosed(1, number)
                .boxed()
                .mapToLong(a -> a)
                .reduce(1, (a, b)-> a * b);
        System.out.println(String.format("Factorial for %s is %s", number, factorial));
    }

}
