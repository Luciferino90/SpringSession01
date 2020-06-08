package it.usuratonkachi.trial.solutions.outerparity;

import it.usuratonkachi.trial.solutions.GenericRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class OuterParityRunner implements GenericRunner {

    public static void main(String[] args) {
        int[] exampleTest1 = {2,6,8,-10,3};
        int resTest1 = find(exampleTest1);
        System.out.println("Test1 " + resTest1);
        assert 3 == resTest1;

        int[] exampleTest2 = {206847684,1056521,7,17,1901,21104421,7,1,35521,1,7781};
        int resTest2 = find(exampleTest2);
        System.out.println("Test1 " + resTest2);
        assert 206847684 == find(exampleTest2);

        int[] exampleTest3 = {Integer.MAX_VALUE, 0, 1};
        int resTest3 = find(exampleTest3);
        System.out.println("Test1 " + resTest3);
        assert 0 == find(exampleTest3);
    }

    @Override
    public void run(String... args) {
        //System.out.println(find(Arrays.stream(args).map(Integer::parseInt).toArray(Integer[]::new)));
    }

    private static int find(int[] integers){
        return Arrays.stream(integers)
                .boxed()
                .collect(Collectors.groupingBy(i -> i % 2 == 0))
                .values()
                .parallelStream()
                .filter(elem -> elem.size() == 1)
                .flatMap(Collection::stream)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Input not valid!"));
    }
}
