package it.usuratonkachi.trial.solutions.consecutivestring;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConsecutiveStrings {

    public static void main(String[] args){
        System.out.println("longestConsec Fixed Tests");
        testing(longestConsec(new String[] {"it","wkppv","ixoyx", "3452", "zzzzzzzzzzzz"}, 3), "ixoyx3452zzzzzzzzzzzz");
        testing(longestConsec(new String[] {"zone", "abigail", "theta", "form", "libe", "zas", "theta", "abigail"}, 2), "abigailtheta");
        testing(longestConsec(new String[] {"ejjjjmmtthh", "zxxuueeg", "aanlljrrrxx", "dqqqaaabbb", "oocccffuucccjjjkkkjyyyeehh"}, 1), "oocccffuucccjjjkkkjyyyeehh");
        testing(longestConsec(new String[] {}, 3), "");
        testing(longestConsec(new String[] {"itvayloxrp","wkppqsztdkmvcuwvereiupccauycnjutlv","vweqilsfytihvrzlaodfixoyxvyuyvgpck"}, 2), "wkppqsztdkmvcuwvereiupccauycnjutlvvweqilsfytihvrzlaodfixoyxvyuyvgpck");
        testing(longestConsec(new String[] {"wlwsasphmxx","owiaxujylentrklctozmymu","wpgozvxxiu"}, 2), "wlwsasphmxxowiaxujylentrklctozmymu");
        testing(longestConsec(new String[] {"zone", "abigail", "theta", "form", "libe", "zas"}, -2), "");
        testing(longestConsec(new String[] {"it","wkppv","ixoyx", "3452", "zzzzzzzzzzzz"}, 3), "ixoyx3452zzzzzzzzzzzz");
        testing(longestConsec(new String[] {"it","wkppv","ixoyx", "3452", "zzzzzzzzzzzz"}, 15), "");
        testing(longestConsec(new String[] {"it","wkppv","ixoyx", "3452", "zzzzzzzzzzzz"}, 0), "");
    }

    private static String longestConsec(String[] strarr, int k) {
        if (k <= 0 || strarr.length == 0 || k > strarr.length) return "";
        return IntStream.rangeClosed(0, strarr.length - k)
                .mapToObj(counter -> Arrays.stream(strarr).skip(counter).limit(k).collect(Collectors.joining()))
                .max(Comparator.comparingInt(String::length))
                .orElseThrow(() -> new RuntimeException("Input not valid"));
    }

    private static void testing(String actual, String expected) {
        boolean isOk = expected.equalsIgnoreCase(actual);
        System.out.println(String.format("%s is equal to %s: %s", actual, expected, isOk));
    }
    
}
