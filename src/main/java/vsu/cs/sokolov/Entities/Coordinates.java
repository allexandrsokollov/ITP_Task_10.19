package vsu.cs.sokolov.Entities;

import java.util.Objects;

public class Coordinates {
    private final double x;
    private final double y;

    public Coordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public static double getLengthBtwn(Coordinates a, Coordinates b) {
        return Math.sqrt(Math.pow(2, (a.getX() - b.x)) + Math.pow(2, a.y - b.getY()));
    }

}
