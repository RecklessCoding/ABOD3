package com.recklesscoding.abode.gui.nodemenu.controller.newelement;

import com.recklesscoding.abode.core.plan.nodes.PlanElementNode;
import com.recklesscoding.abode.gui.views.diagramview.diagram.GraphWindow;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class NewActionMenuItem extends NewPlanElementMenuItem {

    private static final String ADD_ACTION_LABEL = "New Action";

    public NewActionMenuItem(Stage stage, PlanElementNode planElementNode, GraphWindow graphWindow) {
        super(stage, ADD_ACTION_LABEL, planElementNode, graphWindow);
    }

    @Override
    void initButtonAction(Stage primaryStage, PlanElementNode planElementNode, GraphWindow graphWindow) {
        setOnAction(t -> {
            Platform.runLater(() -> {
                new com.recklesscoding.abode.gui.nodemenu.popups.newelement.NewAction(planElementNode, graphWindow);
            });
        });
    }
}
