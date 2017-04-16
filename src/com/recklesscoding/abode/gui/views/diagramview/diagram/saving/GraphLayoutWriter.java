package com.recklesscoding.abode.gui.views.diagramview.diagram.saving;

import com.recklesscoding.abode.util.IWriter;

import java.io.*;
import java.util.Map;

/**
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class GraphLayoutWriter implements IWriter {

    private Map<String[], double[]> nodesLocation;

    public GraphLayoutWriter(Map<String[], double[]> nodesLocation) {
        this.nodesLocation = nodesLocation;
    }

    @Override
    public void writeFile(String file) {
        generateCsvFile(file);
    }

    private void generateCsvFile(String file) {
        try {
            FileWriter writer = new FileWriter(file);
            for (String[] node : nodesLocation.keySet()) {
                double[] position = nodesLocation.get(node);
                writer.append(node[0]);
                writer.append(",");
                writer.append(node[1]);
                writer.append(",");
                writer.append(String.valueOf(position[0]));
                writer.append(",");
                writer.append(String.valueOf(position[1]));
                writer.append('\n');
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
