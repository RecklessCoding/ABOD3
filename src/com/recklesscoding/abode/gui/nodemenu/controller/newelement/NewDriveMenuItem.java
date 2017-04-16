package com.recklesscoding.abode.gui.nodemenu.controller.newelement;

import com.recklesscoding.abode.core.plan.nodes.PlanElementNode;
import com.recklesscoding.abode.gui.views.diagramview.diagram.GraphWindow;
import com.recklesscoding.abode.gui.nodemenu.popups.newelement.NewDrive;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class NewDriveMenuItem extends NewPlanElementMenuItem {
    private static final String ADD_DRIVE = "New Drive";

    public NewDriveMenuItem(Stage stage, PlanElementNode planElementNode, GraphWindow graphWindow) {
        super(stage, ADD_DRIVE, planElementNode, graphWindow);
    }

    @Override
    void initButtonAction(Stage stage, PlanElementNode planElementNode, GraphWindow graphWindow) {
        setOnAction(t -> Platform.runLater(() -> new NewDrive(planElementNode, graphWindow)));
    }
}