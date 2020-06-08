package it.usuratonkachi.trial.solutions.missingletter;

import java.util.concurrent.atomic.AtomicInteger;

public class MissingLetter {

    public static void main(String[] args) {
        char e = findMissingLetter(new char[] { 'a','b','c','d','f' });
        boolean isMatchingE = 'e' == e;
        System.out.println(String.format("Awaited %s Result %s Matching %s", 'e', e, isMatchingE));
        char P = findMissingLetter(new char[] { 'O','Q','R','S' });
        boolean isMatchingP = 'P' == P;
        System.out.println(String.format("Awaited %s Result %s Matching %s", 'P', P, isMatchingP));
    }

    private static char findMissingLetter(char[] chars) {
        AtomicInteger firstAwaited = new AtomicInteger(chars[0] + 1);
        return String.valueOf(chars).chars().boxed().skip(1)
                .filter(c -> {
                    if (c!= firstAwaited.get()){
                        return true;
                    } else {
                        firstAwaited.getAndIncrement();
                        return false;
                    }
                })
                .map(e -> (char)firstAwaited.get())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Inut not valid"));
    }

}
