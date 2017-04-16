package com.recklesscoding.abode.gui.views.diagramview.diagram.saving;

import com.recklesscoding.abode.gui.views.diagramview.diagram.GraphWindow;
import com.recklesscoding.abode.util.IReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class GraphLayoutReader implements IReader {

    private GraphWindow graphWindow;

    private Map<String[], double[]> nodesLocation;

    public GraphLayoutReader(GraphWindow graphWindow) {
        this.graphWindow = graphWindow;
    }

    @Override
    public void readFile(String file) {
        nodesLocation = new LinkedHashMap<>();
        BufferedReader br = null;
        String line;
        try {
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                getNode(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void getNode(String line) {
        String[] lineAsArray = getSplittedString(line);
        nodesLocation.put(new String[]{lineAsArray[0], lineAsArray[1]},
                new double[]{Double.parseDouble(lineAsArray[2]), Double.parseDouble(lineAsArray[3])});
    }

    private String[] getSplittedString(String line) {
        return line.split(",");
    }

    public Map<String[], double[]> getNodesLocation() {
        return nodesLocation;
    }
}
