package vsu.cs.sokolov.Entities;

public class Coordinates {
    private final double x;
    private final double y;

    public Coordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates(Coordinates coordinates) {
        this.x = coordinates.getX();
        this.y = coordinates.getY();
    }

    @Override
    public boolean equals(Object o) {
        final double EPS = 0.0000001;

        if (this == o) return true;
        if (!(o instanceof Coordinates that)) return false;
        return Math.abs(this.getX() - ((Coordinates) o).getX()) < EPS &&
                Math.abs(this.getY() - ((Coordinates) o).getY()) < EPS;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public String toStringDataOnly() {
        return (x + " " + y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public static double getLengthBetweenCoordinates(Coordinates a, Coordinates b) {
        return Math.sqrt(Math.pow((a.getX() - b.getX()), 2) + Math.pow(a.getY() - b.getY(), 2));
    }

}
