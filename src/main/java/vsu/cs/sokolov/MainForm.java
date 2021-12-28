package vsu.cs.sokolov;

import vsu.cs.sokolov.Entities.Triangle;
import vsu.cs.sokolov.FileHandler.FileReader;
import vsu.cs.sokolov.FileHandler.FileWriter;
import vsu.cs.sokolov.util.JTableUtils;
import vsu.cs.sokolov.util.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MainForm extends JFrame {
    private JScrollPane planeInput;
    private JTable tableInput;
    private JButton buttonLoadDataFromFile;
    private JButton buttonExecuteTask;
    private JButton buttonSaveOutputDataToFile;
    private JScrollPane planeOutput;
    private JPanel panelMain;
    private JTextPane textPane1;

    private final JFileChooser fileChooserOpen;
    private final JFileChooser fileChooserSave;

    public MainForm() {
        this.setContentPane(panelMain);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        panelMain.updateUI();

        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        JMenuBar menuBarMain = new JMenuBar();
        setJMenuBar(menuBarMain);

        JMenu menuLookAndFeel = new JMenu();
        menuLookAndFeel.setText("Вид");
        menuBarMain.add(menuLookAndFeel);
        SwingUtils.initLookAndFeelMenu(menuLookAndFeel);
        JTableUtils.initJTableForArray(tableInput,80,
                true, false, true, false);
        String[][] defaultTri =  new String[][] {
                {"1.0 -1.0", "-1.0 1.0", "1.0 1.0"},
                {"1.0 -1.0", "-1.0 1.0", "1.0 1.0"},
                {"1.0 -1.0", "-1.0 1.0", "1.0 1.0"}};
        JTableUtils.writeArrayToJTable(tableInput, defaultTri);


        buttonLoadDataFromFile.addActionListener(e -> {

            if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                List<Triangle> triangleList;

                String[] stringsFromFile;

                try {
                    stringsFromFile = FileReader.getFileData(fileChooserOpen.getSelectedFile().getPath());
                    triangleList = Triangle.getListTriangleFromStringArr(stringsFromFile);

                    String[][] triangles = new String[triangleList.size()][3];

                    for (int i = 0; i < triangles.length; i++) {
                        triangles[i][0] = triangleList.get(i).getA().toStringDataOnly();
                        triangles[i][1] = triangleList.get(i).getB().toStringDataOnly();
                        triangles[i][2] = triangleList.get(i).getC().toStringDataOnly();
                    }


                    JTableUtils.writeArrayToJTable(tableInput, triangles);
                    panelMain.updateUI();

                } catch (IOException ex) {
                    SwingUtils.showErrorMessageBox(ex);
                }
            }

        });


        buttonExecuteTask.addActionListener(e -> {
            panelMain.updateUI();

            String[][] strMatrix = JTableUtils.readStringMatrixFromJTable(tableInput);
            String[] triangles = new String[0];
            StringBuilder t = new StringBuilder();

            if (strMatrix != null) {
                triangles = new String[strMatrix.length];

                for (int i = 0; i < triangles.length; i++) {
                    triangles[i] = (String.valueOf(
                            t.append(strMatrix[i][0]).append(
                                    " ").append(strMatrix[i][1]).append(" ").append(strMatrix[i][2])));
                    t.replace(0, t.length(), "");
                }
            }


            List<List<Triangle>> sortedTriangles;

            try {
                StringBuilder temp = new StringBuilder();
                List<Triangle> triangleList = Triangle.getListTriangleFromStringArr(triangles);

                sortedTriangles = Triangle.groupTrianglesListByCongruence(triangleList);

                for (List<Triangle> triangles1 : sortedTriangles) {
                    for (Triangle tri : triangles1) {
                        temp.append(tri.toStringDataOnly()).append('\n');
                    }
                    temp.append('\n').append("------------------").append('\n');
                }

                textPane1.setText(String.valueOf(temp));
                panelMain.updateUI();
                textPane1.updateUI();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });


        buttonSaveOutputDataToFile.addActionListener(e -> {

            try {
                if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    String dataFromPanel  = textPane1.getText();
                    FileWriter.writeStringToFile(fileChooserSave.getSelectedFile(),dataFromPanel);

                }
            } catch (Exception exception) {
                SwingUtils.showErrorMessageBox(exception);
            }

        });
    }
}
