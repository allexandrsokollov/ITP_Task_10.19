package vsu.cs.sokolov;

import vsu.cs.sokolov.Entities.Triangle;
import vsu.cs.sokolov.FileHandler.FileReader;
import vsu.cs.sokolov.argsHeandler.CmdParams;
import vsu.cs.sokolov.argsHeandler.ParamsReader;

import java.io.IOException;
import java.util.List;

public class Program {

    public static void main(String[] args) {

        task(args);

    }

    public static void task (String[] args) {
        CmdParams params = ParamsReader.parseArgs(args);

        List<List<Triangle>> groupedTriangles;

        List<Triangle> triangleList;

        try {
            String[] stringsFromFile = FileReader.getFileData(params.inputFile);
            triangleList = Triangle.getListTriangleFromStringArr(stringsFromFile);

            groupedTriangles = Triangle.groupTrianglesListByCongruence(triangleList);

            printListOfListsOfTriangles(groupedTriangles);
            Triangle.writeListOfListsOfTrianglesToFile( "out.txt", params.outputFile, groupedTriangles);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printListOfListsOfTriangles(List<List<Triangle>> groupedTriangles) {

        for (List<Triangle> t : groupedTriangles) {
            System.out.println(t.toString());
        }
    }

}
