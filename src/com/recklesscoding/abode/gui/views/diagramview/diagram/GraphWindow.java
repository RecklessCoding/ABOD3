package com.recklesscoding.abode.gui.views.diagramview.diagram;

import com.recklesscoding.abode.gui.views.diagramview.diagram.controller.MouseGestures;
import com.recklesscoding.abode.gui.views.diagramview.diagram.graphviewer.GraphNodesLayer;
import com.recklesscoding.abode.core.plan.nodes.NodesHolder;
import com.recklesscoding.abode.core.plan.nodes.PlanElementNode;
import com.recklesscoding.abode.core.plan.planelements.PlanElement;
import com.recklesscoding.abode.gui.views.diagramview.diagram.graphviewer.layout.PlanHorizontalLayout;
import com.recklesscoding.abode.gui.views.diagramview.diagram.graphviewer.layout.PlanLayout;
import com.recklesscoding.abode.gui.views.diagramview.diagram.graphviewer.layout.PlanLayoutType;
import com.recklesscoding.abode.gui.views.diagramview.diagram.graphviewer.layout.PlanVerticalLayout;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class GraphWindow {

    private NodesHolder nodesHolder;

    private ZoomableScrollPane scrollPane;

    private MouseGestures mouseGestures;

    private GraphNodesLayer graphNodesLayer = new GraphNodesLayer();

    private PlanLayout diagramViewLayout;

    public GraphWindow(Stage stage, PlanLayoutType diagramViewLayoutType) {
        init(stage);
        initDiagramView(diagramViewLayoutType);
    }

    public void refresh() {
//        graphNodesLayer.saveLocationsInMemory();
        graphNodesLayer.clearAll();
        nodesHolder.refresh();
        updateGraph();
        diagramViewLayout.execute();
//        graphNodesLayer.loadLocationsFromMemory();
    }

    public synchronized void update(boolean decreaseGlow) {
        for (PlanElementNode planElementNode : nodesHolder.getAllPlanElementNodes()) {
            planElementNode.updateCell(decreaseGlow);
        }
    }

    public void switchLayoutOrientation() {
        if (diagramViewLayout instanceof PlanHorizontalLayout) {
            this.diagramViewLayout = new PlanVerticalLayout(this);
        } else if (diagramViewLayout instanceof PlanVerticalLayout) {
            this.diagramViewLayout = new PlanHorizontalLayout(this);
        }
        diagramViewLayout.execute();
    }

    public void setNodesLocation(Map<String[], double[]> positions) {
        graphNodesLayer.setNodesLocation(positions);
    }

    public Map<String[], double[]> getNodesLocation() {
        return graphNodesLayer.getNodesLocation();
    }

    public ScrollPane getScrollPane() {
        return this.scrollPane;
    }

    public NodesHolder getNodesHolder() {
        return nodesHolder;
    }

    public double getScale() {
        return this.scrollPane.getScaleValue();
    }

    private void init(Stage stage) {
        nodesHolder = new NodesHolder(new PlanElement("Drives"));
        mouseGestures = new MouseGestures(stage, this);
        initScrollPane();
        updateGraph();
    }

    private void initDiagramView(PlanLayoutType diagramViewLayoutType) {
        switch (diagramViewLayoutType) {
            case HORIZONTAL:
                diagramViewLayout = new PlanHorizontalLayout(this);
                break;
            case VERTICAL:
                diagramViewLayout = new PlanVerticalLayout(this);
                break;
        }
        diagramViewLayout.execute();
    }

    private void initScrollPane() {
        BorderPane pane = new BorderPane();
        pane.setCenter(graphNodesLayer);
        pane.setScaleShape(true);
        this.scrollPane = new ZoomableScrollPane(new Group(graphNodesLayer));
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
    }

    private void removeComponents() {
        graphNodesLayer.getChildren().removeAll(nodesHolder.getRemovedPlanElementNodes());
        graphNodesLayer.getChildren().removeAll(nodesHolder.getRemovedEdges());
    }

    private void addComponents() {
        graphNodesLayer.getChildren().addAll(nodesHolder.getAddedEdges());
        graphNodesLayer.getChildren().addAll(nodesHolder.getAddedPlanElementNodes());
    }

    private void updateGraph() {
        // add components to graph pane
        addComponents();
        // remove components from graph pane
        removeComponents();
        // enable dragging of nodes
        for (PlanElementNode planElementNode : nodesHolder.getAddedPlanElementNodes()) {
            mouseGestures.makeDraggable(planElementNode);
        }
        nodesHolder.disconnectFromGraphParent(nodesHolder.getRemovedPlanElementNodes());
        nodesHolder.merge();
    }
}
