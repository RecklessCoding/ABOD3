package com.recklesscoding.abode.gui.nodemenu;

import com.recklesscoding.abode.core.plan.nodes.PlanElementNode;
import com.recklesscoding.abode.core.plan.nodes.plannodes.*;
import com.recklesscoding.abode.core.plan.planelements.PlanElement;
import com.recklesscoding.abode.core.plan.planelements.drives.DriveCollection;
import com.recklesscoding.abode.core.plan.planelements.drives.DriveElement;
import com.recklesscoding.abode.gui.nodemenu.controller.deletelement.DeletePlanElementMenuItem;
import com.recklesscoding.abode.gui.nodemenu.controller.editelement.*;
import com.recklesscoding.abode.gui.nodemenu.controller.newelement.*;
import com.recklesscoding.abode.gui.views.diagramview.diagram.GraphWindow;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class NodeMenu extends ContextMenu {

    public NodeMenu(Stage primaryStage, GraphWindow graphWindow, PlanElementNode planElementNode, MouseEvent mouseEvent) {
        init();

        if (!(planElementNode instanceof RouteElementNode)) {
            addMenuItem(new DeletePlanElementMenuItem(primaryStage, planElementNode, graphWindow));
            addMenuItem(new SeparatorMenuItem());
            initOpenProperties(primaryStage, planElementNode, graphWindow);
            addMenuItem(new SeparatorMenuItem());
        }
        initAddNewElement(primaryStage, planElementNode, graphWindow);
        show(planElementNode, mouseEvent.getScreenX(), mouseEvent.getScreenY());
    }

    private void init() {
        setAutoHide(true);
    }

    private void initOpenProperties(Stage primaryStage, PlanElementNode planElementNode, GraphWindow graphWindow) {
        PlanElement planElement = planElementNode.getPlanElement();

        if (planElementNode instanceof ActionNode) {
            addMenuItem(new EditActionMenuItem(primaryStage, planElementNode, graphWindow));
        } else if (planElementNode instanceof ActionPatternNode) {
            addMenuItem(new EditAPMenuItem(primaryStage, planElementNode, graphWindow));
        } else if (planElementNode instanceof CompetenceNode) {
            addMenuItem(new EditCompetenceMenuItem(primaryStage, planElementNode, graphWindow));
        } else if (planElementNode instanceof CompetenceElementNode) {
            addMenuItem(new EditCompetenceElemMenuItem(primaryStage, planElementNode, graphWindow));
        } else if (planElement instanceof DriveCollection) {
            addMenuItem(new EditDriveCollectionMenuItem(primaryStage, planElementNode, graphWindow));
        } else if (planElement instanceof DriveElement) {
            addMenuItem(new EditDriveElemMenuItem(primaryStage, planElementNode, graphWindow));
        }
    }

    private void initAddNewElement(Stage primaryStage, PlanElementNode planElementNode, GraphWindow graphWindow) {
        PlanElement planElement = planElementNode.getPlanElement();

        if (planElementNode instanceof ActionPatternNode || planElementNode instanceof CompetenceElementNode
                || planElementNode instanceof DriveCollectionNode) {
            addMenuItem(new NewActionMenuItem(primaryStage, planElementNode, graphWindow));
        }
        if (planElementNode instanceof CompetenceNode) {
            addMenuItem(new NewCompetenceElemMenuItem(primaryStage, planElementNode, graphWindow));
        }
        if (planElementNode instanceof CompetenceElementNode || planElement instanceof DriveElement
                || planElementNode instanceof DriveCollectionNode) {
            addMenuItem(new NewAPMenuItem(primaryStage, planElementNode, graphWindow));
            addMenuItem(new NewCompetenceMenuItem(primaryStage, planElementNode, graphWindow));
        }
        if (planElementNode instanceof DriveCollectionNode) {
            addMenuItem(new NewDriveElemMenuItem(primaryStage, planElementNode, graphWindow));
        }
        if (planElementNode instanceof RouteElementNode) {
            addMenuItem(new NewDriveMenuItem(primaryStage, planElementNode, graphWindow));
        }
    }


    private void addMenuItem(MenuItem menuItem) {
        getItems().add(menuItem);
    }
}
