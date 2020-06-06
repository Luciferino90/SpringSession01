package it.usuratonkachi.trial.solutions.marsrover;

public enum Direction {

    N(0, "North"),
    S(1, "South"),
    E(2, "East"),
    W(3, "West");

    private final int val;
    private final String desc;

    Direction(int val, String desc){
        this.val = val;
        this.desc = desc;
    }

    Direction goLeft(){
        int nval = (4 + (val - 1)) % 4;
        return Direction.values()[nval];
    }

    Direction goRight(){
        int nval = (4 + (val + 1)) % 4;
        return Direction.values()[nval];
    }

}
