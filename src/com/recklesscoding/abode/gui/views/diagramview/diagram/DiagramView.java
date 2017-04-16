package com.recklesscoding.abode.gui.views.diagramview.diagram;

import com.recklesscoding.abode.gui.views.diagramview.diagram.graphviewer.layout.PlanLayoutType;
import javafx.application.Platform;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import java.util.Map;

public class DiagramView extends GraphView {

    private static DiagramView instance;

    public GraphWindow getGraphWindow() {
        return graphWindow;
    }

    private GraphWindow graphWindow;

    private GraphUpdater graphUpdater;

    public DiagramView(Stage primaryStage, PlanLayoutType diagramViewLayoutType) {
        super();
        graphWindow = new GraphWindow(primaryStage, diagramViewLayoutType);
        graphUpdater = new GraphUpdater(graphWindow);

        Platform.runLater(() -> {
            graphUpdater = new GraphUpdater(graphWindow);
            graphUpdater.startUp();
        });
    }

    public static DiagramView getInstance() {
        if (instance != null) {
            return instance;
        } else {
            instance = new DiagramView(null, PlanLayoutType.VERTICAL);
            return instance;
        }
    }

    public ScrollPane getDiagramViewPane() {
        return graphWindow.getScrollPane();
    }

    public void refreshDiagram() {
        graphWindow.refresh();
    }

    public Map<String[], double[]> getNodesLocation() {
        return graphWindow.getNodesLocation();
    }

    public void switchLayoutOrientation() {
        graphWindow.switchLayoutOrientation();
    }

    public void setNodesLocation(Map<String[], double[]> nodesLocation) {
        graphWindow.setNodesLocation(nodesLocation);
    }

    public void startGraphUpdater() {
        graphUpdater.startUp();
    }

    public void stopGraphUpdater() {
        graphUpdater.stop();
    }
}