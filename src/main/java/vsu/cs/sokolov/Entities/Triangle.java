package vsu.cs.sokolov.Entities;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Triangle {
    private final Coordinates a;
    private final Coordinates b;
    private final Coordinates c;

    public Triangle(Coordinates a, Coordinates b, Coordinates c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Triangle (double x1, double y1, double x2, double y2, double x3, double y3) {
        this.a = new Coordinates(x1, y1);
        this.b = new Coordinates(x2, y2);
        this.c = new Coordinates(x3, y3);
    }

    public Triangle (Triangle triangle) {
        this.a = triangle.getA();
        this.b = triangle.getB();
        this.c = triangle.getC();
    }

    public static List<List<Triangle>> groupTrianglesListByCongruence(List<Triangle> list) {
        List<List<Triangle>> resultList = new ArrayList<>();
        List<Triangle> triangles = new ArrayList<>(list);
        Triangle toCompare;

        int cnt = 0;
        int listCnt = 0;
        int trianglesLength = triangles.size();

        while (triangles.size() > 0) {
            resultList.add(new ArrayList<>());
            toCompare = new Triangle(triangles.get(0));

            for (int i = 0; i < trianglesLength; i++) {
                if (toCompare.isTrianglesCongruent(triangles.get(i))) {
                    resultList.get(listCnt).add(new Triangle(triangles.get(i)));
                    triangles.remove(i);
                    i--;
                    trianglesLength--;
                }
            }
            listCnt++;
        }

        return resultList;
    }

    public boolean isTrianglesCongruent (Triangle triangle) {
        ArrayList<Double> thisRatios = this.getRatios();
        ArrayList<Double> ratios = triangle.getRatios();
        final double EPS = 0.0000001;

        int coefficientOfCongruence = 0;

        for (Double tr : thisRatios) {
            for (Double r : ratios) {
                if (Math.abs(tr - r) < EPS) {
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

    public Coordinates getA() {
        return a;
    }

    public Coordinates getB() {
        return b;
    }

    public Coordinates getC() {
        return c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Triangle triangle)) return false;
        return Objects.equals(a, triangle.a) && Objects.equals(b, triangle.b) && Objects.equals(c, triangle.c);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }
}
