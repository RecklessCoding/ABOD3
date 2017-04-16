package com.recklesscoding.abode.gui.views.diagramview.diagram.graphviewer;

import com.recklesscoding.abode.core.plan.nodes.PlanElementNode;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 * @see PlanElementNode
 * </p>
 */
public class GraphNodesLayer extends Pane {

    Map<String[], double[]> nodesLocation;

    public GraphNodesLayer() {
        setScaleShape(true);
        setBorder(Border.EMPTY);
        setBackground(Background.EMPTY);
//        this.setStyle("-fx-background-color:yellow");
    }

    public void saveLocationsInMemory(){
        nodesLocation = getNodesLocation();
    }

    public void loadLocationsFromMemory(){
        setNodesLocation(nodesLocation);
    }
    public void clearAll() {
        getChildren().removeAll(getChildren());
    }

    public Map<String[], double[]> getNodesLocation() {
        Map<String[], double[]> positions = new LinkedHashMap<>();
        for (Node node : getChildren()) {
            double layoutX = node.getLayoutX();
            double layoutY = node.getLayoutY();
            if (node instanceof PlanElementNode) {
                PlanElementNode planElement = (PlanElementNode) node;
                positions.put(new String[]{planElement.getPlanElement().getNameOfElement(), getPath(planElement, "")}, new double[]{layoutX, layoutY});
            }
        }
        return positions;
    }

    private String getPath(PlanElementNode planElementNode, String path) {
        if (planElementNode.getNodeParent() != null) {
            return getPath(planElementNode.getNodeParent(), path + planElementNode.getPlanElement().getNameOfElement());
        }
        return path;
    }

    public void setNodesLocation(Map<String[], double[]> positions) {
        for (Node node : getChildren()) {
            if (node instanceof PlanElementNode) {
                PlanElementNode planElement = (PlanElementNode) node;
                for (String[] strings : positions.keySet()) {
                    String nameOfElement = strings[0];
                    String path = strings[1];
                    double[] nodePosition = positions.get(strings);
                    if (planElement.getPlanElement().getNameOfElement().equals(nameOfElement)) {
                        if (getPath(planElement, "").equals(path)) {
                            if (nodePosition != null && nodePosition.length > 0) {
                                node.relocate(nodePosition[0], nodePosition[1]);
                            }
                        }
                    }
                }
            }
        }
    }
}
