package it.usuratonkachi.trial.solutions.marsrover;

import it.usuratonkachi.trial.solutions.GenericRunner;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RoverRunner implements GenericRunner {

    @Override
    public void run(String... args) {
        args = new String[] {"0", "0", "0", "f", "l", "f", "b", "r", "b"};
        if (args.length < 3)
            throw new RuntimeException("Not enough elements");
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        int dir = Integer.parseInt(args[2]);
        Position position = new Position(x, y);
        Direction direction = Direction.values()[dir];
        Rover rover = new Rover(position, direction);

        rover.move(Arrays.stream(args).skip(3).toArray(String[]::new));

    }

}
