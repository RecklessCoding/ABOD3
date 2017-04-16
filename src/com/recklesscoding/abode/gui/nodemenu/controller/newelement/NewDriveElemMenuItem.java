package com.recklesscoding.abode.gui.nodemenu.controller.newelement;

import com.recklesscoding.abode.core.plan.nodes.PlanElementNode;
import com.recklesscoding.abode.gui.views.diagramview.diagram.GraphWindow;
import javafx.stage.Stage;

/**
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class NewDriveElemMenuItem extends NewPlanElementMenuItem {
    private static final String ADD_DRIVE_ELEMENT = "New Drive Element";

    public NewDriveElemMenuItem(Stage primaryStage, PlanElementNode planElementNode, GraphWindow graphWindow) {
        super(primaryStage, ADD_DRIVE_ELEMENT, planElementNode, graphWindow);
    }

    @Override
    void initButtonAction(Stage primaryStage, PlanElementNode planElementNode, GraphWindow graphWindow) {

    }
}