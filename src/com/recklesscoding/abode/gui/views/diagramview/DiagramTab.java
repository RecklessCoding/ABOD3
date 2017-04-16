package com.recklesscoding.abode.gui.views.diagramview;

import com.recklesscoding.abode.gui.layout.EditorViewTab;
import com.recklesscoding.abode.gui.views.diagramview.diagram.GraphWindow;
import com.recklesscoding.abode.gui.views.diagramview.diagram.graphviewer.layout.PlanLayoutType;
import com.recklesscoding.abode.gui.views.diagramview.diagram.DiagramView;
import javafx.stage.Stage;

import java.util.Map;

/**
 * @see {@link EditorViewTab}
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class DiagramTab extends EditorViewTab {

    private static final String DIAGRAM_REPRESENTATION = "Diagram representation";

    private DiagramView diagramView;

    public DiagramTab(Stage primaryStage) {
        super(DIAGRAM_REPRESENTATION, null);

        diagramView = new DiagramView(primaryStage, PlanLayoutType.VERTICAL);
        setContent(diagramView.getDiagramViewPane());
    }

    public void refreshContent() {
        diagramView.refreshDiagram();
    }

    public GraphWindow getGraphWindow(){
        return diagramView.getGraphWindow();
    }

    public Map<String[], double[]> getNodesLocation(){
        return diagramView.getNodesLocation();
    }

    public void switchOrientation(){
        diagramView.switchLayoutOrientation();
    }

    @Override
    public void refresh(Stage primaryStage) {
        setContent(diagramView.getDiagramViewPane());
    }
}