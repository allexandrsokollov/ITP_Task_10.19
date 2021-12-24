package vsu.cs.sokolov.Entities;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TriangleTest {


    @Test
    public void groupTrianglesListByCongruence() {

        List<Triangle> triangles = new ArrayList<>();

        triangles.add(new Triangle(-1, 1, -1,4, -3, 1)); // group - 1
        triangles.add(new Triangle(1, 1, 1,4, 3, 1)); // group - 1
        triangles.add(new Triangle(0, 1, 2,1, 1, 4)); // group - 2

        triangles.add(new Triangle(-6, 4, -6,9, -9, 6)); // group - 4
        triangles.add(new Triangle(1, 11, 5,5, 5, 11)); // group - 1
        triangles.add(new Triangle(0, 1, 2,1, 1, 4)); // group - 2

        triangles.add(new Triangle(-5, 3, -5,13, 1, 9)); // group - 4
        triangles.add(new Triangle(6, 6, 8,12, 10, 6)); // group - 2
        triangles.add(new Triangle(10, 3, 16,3, 16, 5)); // group - 3

        triangles.add(new Triangle(18, 1, 18,5, 30, 5)); // group - 3
        triangles.add(new Triangle(-1, -4, -1,-5, -3, -8)); // group - 5
        triangles.add(new Triangle(2, -3, 2,-1, 6, 5)); // group - 5

        triangles.add(new Triangle(2, 2, 5,0, -12, 2)); // group - 6

        List<List<Triangle>> expected = new ArrayList<>();

        expected.add(new ArrayList<>());
        expected.get(0).add(new Triangle(-1, 1, -1,4, -3, 1));
        expected.get(0).add(new Triangle(1, 1, 1,4, 3, 1));
        expected.get(0).add(new Triangle(1, 11, 5,5, 5, 11));

        expected.add(new ArrayList<>());
        expected.get(1).add(new Triangle(0, 1, 2,1, 1, 4));
        expected.get(1).add(new Triangle(0, 1, 2,1, 1, 4));
        expected.get(1).add(new Triangle(6, 6, 8,12, 10, 6));

        expected.add(new ArrayList<>());
        expected.get(2).add(new Triangle(-6, 4, -6,9, -9, 6));
        expected.get(2).add(new Triangle(-5, 3, -5,13, 1, 9));

        expected.add(new ArrayList<>());

        expected.get(3).add(new Triangle(10, 3, 16,3, 16, 5));
        expected.get(3).add(new Triangle(18, 1, 18,5, 30, 5));

        expected.add(new ArrayList<>());
        expected.get(4).add(new Triangle(-1, -4, -1,-5, -3, -8));
        expected.get(4).add(new Triangle(2, -3, 2,-1, 6, 5));

        expected.add(new ArrayList<>());
        expected.get(5).add(new Triangle(2, 2, 5,0, -12, 2));




        List<List<Triangle>> actual;
        actual = new ArrayList<>(Triangle.groupTrianglesListByCongruence(triangles));

        Assert.assertArrayEquals(listOfListOfDoublesTo2Array(expected),
                listOfListOfDoublesTo2Array(actual));

    }

    public static Triangle[][] listOfListOfDoublesTo2Array(List<List<Triangle>> list) {
        Triangle[][] finalArray = new Triangle[list.size()][];

        for (int i = 0; i < list.size(); i++) {
            finalArray[i] = new Triangle[list.get(i).size()];

            for (int j = 0; j < list.get(i).size(); j++) {
                finalArray[i][j] = list.get(i).get(j);
            }
        }

        return finalArray;
    }
}