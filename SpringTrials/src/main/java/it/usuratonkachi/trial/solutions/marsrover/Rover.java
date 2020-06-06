package it.usuratonkachi.trial.solutions.marsrover;

import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public class Rover {

    private final Position position;
    private Direction direction;

    public void move(String[] commands){
        Arrays.stream(commands)
                .forEach(cmd -> {
                    switch (cmd) {
                        case "f" -> moveForward();
                        case "b" -> moveBackward();
                        case "l" -> direction = direction.goLeft();
                        case "r" -> direction = direction.goRight();
                    }
                });
        System.out.println(String.format("X at %s Y at %s facing %s", position.getX(), position.getY(), direction));
    }

    private void moveForward(){
        switch (direction){
            case N -> position.setY(position.getY() + 1);
            case S -> position.setY(position.getY() - 1);
            case E -> position.setX(position.getX() + 1);
            case W -> position.setX(position.getX() - 1);
        }
    }

    private void moveBackward(){
        switch (direction){
            case N -> position.setY(position.getY() - 1);
            case S -> position.setY(position.getY() + 1);
            case E -> position.setX(position.getX() - 1);
            case W -> position.setX(position.getX() + 1);
        }
    }

}
