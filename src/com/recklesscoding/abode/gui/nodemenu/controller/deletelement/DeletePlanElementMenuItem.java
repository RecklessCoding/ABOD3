package com.recklesscoding.abode.gui.nodemenu.controller.deletelement;

import com.recklesscoding.abode.core.plan.Plan;
import com.recklesscoding.abode.core.plan.nodes.PlanElementNode;
import com.recklesscoding.abode.core.plan.nodes.plannodes.RouteElementNode;
import com.recklesscoding.abode.core.plan.planelements.ElementWithTrigger;
import com.recklesscoding.abode.core.plan.planelements.PlanElement;
import com.recklesscoding.abode.core.plan.planelements.action.ActionEvent;
import com.recklesscoding.abode.core.plan.planelements.action.ActionPattern;
import com.recklesscoding.abode.core.plan.planelements.competence.Competence;
import com.recklesscoding.abode.core.plan.planelements.competence.CompetenceElement;
import com.recklesscoding.abode.core.plan.planelements.drives.DriveCollection;
import com.recklesscoding.abode.core.plan.planelements.drives.DriveElement;
import com.recklesscoding.abode.gui.views.diagramview.diagram.DiagramView;
import com.recklesscoding.abode.gui.views.diagramview.diagram.GraphWindow;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class DeletePlanElementMenuItem extends MenuItem {

    private static final String EDIT = "Remove";

    public DeletePlanElementMenuItem(Stage primaryStage, PlanElementNode planElementNode, GraphWindow graphWindow) {
        super(EDIT);

        initButtonAction(primaryStage, planElementNode, graphWindow);
    }

    private void initButtonAction(Stage stage, PlanElementNode planElementNode, GraphWindow graphWindow) {
        setOnAction(t -> {
            PlanElement planParent = planElementNode.getNodeParent().getPlanElement();
            PlanElement planElement = planElementNode.getPlanElement();
            if (planParent instanceof ElementWithTrigger) {
                ElementWithTrigger planParentTriggered = (ElementWithTrigger) planParent;
                planParentTriggered.removeTriggeredElement();
            } else if (planParent instanceof ActionPattern) {
                ActionPattern planParentAP = (ActionPattern) planParent;
                planParentAP.removeActionEvent((ActionEvent) planElement);
            } else if (planParent instanceof Competence) {
                Competence planParentC = (Competence) planParent;
                planParentC.removeCompetenceElement((CompetenceElement) planElement);
            } else if (planParent instanceof DriveCollection) {
                Competence planParentC = (Competence) planParent;
                planParentC.removeCompetenceElement((CompetenceElement) planElement);
            }else if (planElementNode.getNodeParent() instanceof RouteElementNode) {
                Plan.getInstance().removeDriveCollection((DriveCollection) planElement);
            }

            planElementNode.getNodeParent().removeChild(planElementNode);
            graphWindow.refresh();
        });

    }
}
