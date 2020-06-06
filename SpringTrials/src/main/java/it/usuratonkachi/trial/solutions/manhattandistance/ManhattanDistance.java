package it.usuratonkachi.trial.solutions.manhattandistance;

import it.usuratonkachi.trial.solutions.GenericRunner;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

@Service
public class ManhattanDistance implements GenericRunner {

    @Override
    public void run(String... args) {
        args = new String[] {"10", "10", "4", "4"};
        if (args.length != 4)
            throw new RuntimeException("Not enough elements");
        int x1 = Integer.parseInt(args[0]);
        int y1 = Integer.parseInt(args[1]);
        int x2 = Integer.parseInt(args[2]);
        int y2 = Integer.parseInt(args[3]);
        Point pointOne = new Point(x1, y1);
        Point pointTwo = new Point(x2, y2);
        int manhattanDistance = manhattanDistance(pointOne, pointTwo);
        System.out.println(manhattanDistance);
    }

    private int manhattanDistance(Point pointOne, Point pointTwo) {
        // |x1-x2| + |y1-y2|
        return pointOne.distance(pointTwo);
    }

}
