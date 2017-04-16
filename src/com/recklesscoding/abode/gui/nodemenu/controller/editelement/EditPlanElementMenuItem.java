package com.recklesscoding.abode.gui.nodemenu.controller.editelement;

import com.recklesscoding.abode.core.plan.nodes.PlanElementNode;
import com.recklesscoding.abode.gui.nodemenu.controller.PlanElementMenuItem;
import com.recklesscoding.abode.gui.views.diagramview.diagram.GraphWindow;
import javafx.stage.Stage;

/**
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public abstract class EditPlanElementMenuItem extends PlanElementMenuItem {

    private static final String EDIT = "Browse";

    public EditPlanElementMenuItem(Stage primaryStage, PlanElementNode planElementNode, GraphWindow graphWindow) {
        super(primaryStage,EDIT,planElementNode,graphWindow);

        initButtonAction(primaryStage, planElementNode, graphWindow);
    }

    abstract void initButtonAction(Stage stage, PlanElementNode planElementNode, GraphWindow graphWindow);
}
