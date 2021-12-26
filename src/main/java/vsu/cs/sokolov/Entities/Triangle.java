package vsu.cs.sokolov.Entities;


import vsu.cs.sokolov.FileHandler.FileWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Triangle {
    private Coordinates a;
    private Coordinates b;
    private Coordinates c;

    public Triangle() {}

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

    public static List<Triangle> getListTriangleFromStringArr (String[] strArr) throws IOException {

        List<Triangle> triangles = new ArrayList<>();

        for (String str : strArr) {
            triangles.add(getTriangleFromString(str));
        }

        return triangles;
    }

    public static Triangle getTriangleFromString (String str) throws IOException {
        Scanner scanner = new Scanner(str);
        Triangle triangle = new Triangle();
        List<Double> doubles = new ArrayList<>();

        while (scanner.hasNextLine()) {
            if (scanner.hasNextDouble()) {
                doubles.add(scanner.nextDouble());
                continue;
            }
            if (scanner.hasNextInt()) {
                doubles.add((double) scanner.nextInt());
            }
        }

        if (doubles.size() != 6) {
            throw new IOException("Can not convert String to Triangle");
        }

        triangle.setA(new Coordinates(doubles.get(0), doubles.get(1)));
        triangle.setB(new Coordinates(doubles.get(2), doubles.get(3)));
        triangle.setC(new Coordinates(doubles.get(4), doubles.get(5)));

        return triangle;

    }

    public static String[] listToStringArr (List<Triangle> triangles) {
        int trianglesAmount = triangles.size();
        String[] result = new String[trianglesAmount];

        for (int i = 0; i < trianglesAmount; i++) {
            result[i] = (triangles.get(i).getA().toStringDataOnly() + " " +
                    triangles.get(i).getB().toStringDataOnly() + " " +
                    triangles.get(i).getC().toStringDataOnly());
        }

        return result;
    }

    public static void writeListOfListsOfTrianglesToFile (File file, List<List<Triangle>> listListTriangles) throws IOException {
        String divider = ("------------------------------------------------------");
        for (List<Triangle> triangles : listListTriangles) {
            for (Triangle t : triangles) {
                FileWriter.writeStringToFile(file, t.toStringDataOnly());
            }
            FileWriter.writeStringToFile(file, divider);
        }

    }

    public static void writeListOfListsOfTrianglesToFile (String fileName, String filePath,
                                                          List<List<Triangle>> listListTriangles) throws IOException {
        String divider = ("------------------------------------------------------");
        for (List<Triangle> triangles : listListTriangles) {
            for (Triangle t : triangles) {
                FileWriter.writeStringToFile(fileName, filePath, t.toStringDataOnly());
            }
            FileWriter.writeStringToFile(fileName, filePath, divider);
        }

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

    public void setA(Coordinates a) {
        this.a = a;
    }

    public void setB(Coordinates b) {
        this.b = b;
    }

    public void setC(Coordinates c) {
        this.c = c;
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

    public String toStringDataOnly() {
        return getA().toStringDataOnly() + " " +
                getB().toStringDataOnly() + " " +
                getC().toStringDataOnly();
    }

    private ArrayList<Double> getRatios () {
        ArrayList<Double> ratios = new ArrayList<>();

        ratios.add(Coordinates.getLengthBetweenCoordinates(a, b) / Coordinates.getLengthBetweenCoordinates(b, c));
        ratios.add(Coordinates.getLengthBetweenCoordinates(a, b) / Coordinates.getLengthBetweenCoordinates(c, a));
        ratios.add(Coordinates.getLengthBetweenCoordinates(b, c) / Coordinates.getLengthBetweenCoordinates(c, a));
        ratios.add(Coordinates.getLengthBetweenCoordinates(b, c) / Coordinates.getLengthBetweenCoordinates(a, b));
        ratios.add(Coordinates.getLengthBetweenCoordinates(c, a) / Coordinates.getLengthBetweenCoordinates(a, b));
        ratios.add(Coordinates.getLengthBetweenCoordinates(c, a) / Coordinates.getLengthBetweenCoordinates(b, c));

        return ratios;
    }
}
