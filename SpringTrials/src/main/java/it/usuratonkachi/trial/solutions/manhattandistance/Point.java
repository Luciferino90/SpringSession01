package it.usuratonkachi.trial.solutions.manhattandistance;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Point {

    private final int x;
    private final int y;

    public int distance(Point other){
        return other.distance(this.x, this.y);
    }

    public int distance(int x, int y){
        return Math.abs(this.x - x) + Math.abs(this.y - y);
    }

}
