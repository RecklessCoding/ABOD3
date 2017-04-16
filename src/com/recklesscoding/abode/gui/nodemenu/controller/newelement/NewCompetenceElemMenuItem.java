package com.recklesscoding.abode.gui.nodemenu.controller.newelement;

import com.recklesscoding.abode.core.plan.nodes.PlanElementNode;
import com.recklesscoding.abode.gui.views.diagramview.diagram.GraphWindow;
import com.recklesscoding.abode.gui.nodemenu.popups.newelement.NewCompetenceElement;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class NewCompetenceElemMenuItem extends NewPlanElementMenuItem {
    private static final String ADD_COMPETENCE_ELEMENT = "New Competence Element";

    public NewCompetenceElemMenuItem(Stage stage, PlanElementNode planElementNode, GraphWindow graphWindow) {
        super(stage, ADD_COMPETENCE_ELEMENT, planElementNode, graphWindow);
    }

    @Override
    void initButtonAction(Stage stage, PlanElementNode planElementNode, GraphWindow graphWindow) {
        setOnAction(t -> Platform.runLater(() -> new NewCompetenceElement(planElementNode, graphWindow)));
    }
}
