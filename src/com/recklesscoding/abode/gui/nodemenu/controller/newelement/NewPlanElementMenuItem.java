package com.recklesscoding.abode.gui.nodemenu.controller.newelement;

import com.recklesscoding.abode.core.plan.nodes.PlanElementNode;
import com.recklesscoding.abode.gui.nodemenu.controller.PlanElementMenuItem;
import com.recklesscoding.abode.gui.views.diagramview.diagram.GraphWindow;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public abstract class NewPlanElementMenuItem extends PlanElementMenuItem {

    public NewPlanElementMenuItem(Stage primaryStage, String label, PlanElementNode planElementNode, GraphWindow graphWindow) {
        super(primaryStage,label,planElementNode,graphWindow);

        initButtonAction(primaryStage, planElementNode, graphWindow);
    }

    abstract void initButtonAction(Stage stage, PlanElementNode planElementNode, GraphWindow graphWindow);
}
