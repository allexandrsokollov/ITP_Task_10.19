package vsu.cs.sokolov.Entities;


import java.util.ArrayList;

public class Triangle {
    private final Coordinates a;
    private final Coordinates b;
    private final Coordinates c;

    public Triangle(Coordinates a, Coordinates b, Coordinates c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public boolean isTrianglesCongruent (Triangle triangle) {
        ArrayList<Double> thisRatios = getRatios();
        ArrayList<Double> ratios = triangle.getRatios();
        final double EPS = 0.0000001;

        int coefficientOfCongruence = 0;

        for (Double tr : thisRatios) {
            for (Double r : ratios) {
                if (Math.abs(tr) - Math.abs(r) < EPS) {
                    coefficientOfCongruence++;
                }
            }
        }

        return coefficientOfCongruence >= 6;
    }

    private ArrayList<Double> getRatios () {
        ArrayList<Double> ratios = new ArrayList<>();

        ratios.add(Coordinates.getLengthBtwn(a, b) / Coordinates.getLengthBtwn(b, c));
        ratios.add(Coordinates.getLengthBtwn(a, b) / Coordinates.getLengthBtwn(c, a));
        ratios.add(Coordinates.getLengthBtwn(b, c) / Coordinates.getLengthBtwn(c, a));
        ratios.add(Coordinates.getLengthBtwn(b, c) / Coordinates.getLengthBtwn(a, b));
        ratios.add(Coordinates.getLengthBtwn(c, a) / Coordinates.getLengthBtwn(a, b));
        ratios.add(Coordinates.getLengthBtwn(c, a) / Coordinates.getLengthBtwn(b, c));

        return ratios;
    }


}
