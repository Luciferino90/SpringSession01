package it.usuratonkachi.trial.solutions.marsrover;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Position {

    private int x;
    private int y;

    public Position moveForward(Direction direction){
        switch (direction){
            case N -> y = + 1;
            case S -> y = y - 1;
            case E -> x = x + 1;
            case W -> x = x - 1;
        }
        return this;
    }

    public Position moveBackward(Direction direction){
        switch (direction){
            case N -> y = - 1;
            case S -> y = y + 1;
            case E -> x = x - 1;
            case W -> x = x + 1;
        }
        return this;
    }

}
