package com.recklesscoding.abode.gui.nodemenu.popups.newelement;

import com.recklesscoding.abode.core.plan.Plan;
import com.recklesscoding.abode.core.plan.nodes.PlanElementNode;
import com.recklesscoding.abode.core.plan.planelements.ElementWithTrigger;
import com.recklesscoding.abode.core.plan.planelements.PlanElement;
import com.recklesscoding.abode.core.plan.planelements.action.ActionEvent;
import com.recklesscoding.abode.core.plan.planelements.action.ActionPattern;
import com.recklesscoding.abode.core.plan.planelements.competence.CompetenceElement;
import com.recklesscoding.abode.core.plan.planelements.drives.DriveCollection;
import com.recklesscoding.abode.core.plan.planelements.drives.DriveElement;
import com.recklesscoding.abode.gui.views.diagramview.diagram.GraphWindow;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class NewAction extends NewElementPopup {

    private static final String LABEL = "New Action";

    public NewAction(PlanElementNode planElementNode, GraphWindow graphWindow) {
        super(LABEL, planElementNode, graphWindow);
    }

    @Override
    void addContent() {
        // Nothing extra needs to be added
    }

    @Override
    protected void addSaveButton(Button saveButton, PlanElement planElement) {
        saveButton.setOnAction(event -> {
                    if (isTextNotEmpty(getName())) {
                        ActionEvent actionEvent = Plan.getInstance().createAction(getName());
                        if (planElement instanceof ActionPattern) {
                            ActionPattern actionPattern = (ActionPattern) planElement;
                            actionPattern.addAction(actionEvent);
                        } else if (planElement instanceof CompetenceElement || planElement instanceof DriveElement || planElement instanceof DriveCollection) {
                            ElementWithTrigger elementWithTrigger = (ElementWithTrigger) planElement;
                            elementWithTrigger.setTriggeredElement(actionEvent);
                        }
                       refresh();
                    }
                }
        );
    }
}
