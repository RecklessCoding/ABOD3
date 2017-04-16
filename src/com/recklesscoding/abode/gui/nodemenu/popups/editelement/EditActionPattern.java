package com.recklesscoding.abode.gui.nodemenu.popups.editelement;

import com.recklesscoding.abode.core.plan.nodes.PlanElementNode;
import com.recklesscoding.abode.core.plan.planelements.ElementWithTrigger;
import com.recklesscoding.abode.core.plan.planelements.PlanElement;
import com.recklesscoding.abode.core.plan.planelements.action.ActionPattern;
import com.recklesscoding.abode.core.plan.planelements.competence.CompetenceElement;
import com.recklesscoding.abode.core.plan.planelements.drives.DriveCollection;
import com.recklesscoding.abode.core.plan.planelements.drives.DriveElement;
import com.recklesscoding.abode.gui.nodemenu.popups.panes.ActionsPane;
import com.recklesscoding.abode.gui.nodemenu.popups.panes.SelectTimePane;
import com.recklesscoding.abode.gui.views.diagramview.diagram.GraphWindow;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class EditActionPattern extends EditElementPopup {

    private SelectTimePane timePane;

    private ActionsPane actionsPane;

    private static final String TIME_LABEL = "Time";

    private static final String ACTIONS_LABEL = "Actions";

    public EditActionPattern(PlanElementNode planElementNode, GraphWindow graphWindow) {
        super(planElementNode, graphWindow);
    }

    @Override
    void addContent(PlanElement planElement) {
        ActionPattern actionPattern = (ActionPattern) planElement;
        actionsPane = new ActionsPane(actionPattern.getActionEvents());
        timePane = new SelectTimePane(actionPattern.getTimeValue(), actionPattern.getTimeUnits());

        addContent();
    }

    private void addContent() {
        addContentItem(new Label(TIME_LABEL));
        addContentItem(timePane);
        addContentItem(new Label(ACTIONS_LABEL));
        addContentItem(actionsPane);
    }

    @Override
    protected void addSaveButton(Button saveButton, PlanElement planElement) {
        saveButton.setOnAction(event -> {
                    if (isTextNotEmpty(getName())) {
                        ActionPattern actionPattern = (ActionPattern) planElement;
                        actionPattern.setNameOfElement(getName());
                        setTime(actionPattern);
                        setActions(actionPattern);
                        refresh();
                    }
                }
        );
    }

    private void setTriggeredElement(PlanElement fatherElement, ActionPattern actionPattern) {
        if (fatherElement instanceof CompetenceElement || fatherElement instanceof DriveElement || fatherElement instanceof DriveCollection) {
            ElementWithTrigger elementWithTrigger = (ElementWithTrigger) fatherElement;
            elementWithTrigger.setTriggeredElement(actionPattern);
        }
    }

    private void setActions(ActionPattern actionPattern) {
        if (!actionsPane.getSelectedActions().isEmpty()) {
            actionPattern.setActions(actionsPane.getSelectedActions());
        }
    }

    private void setTime(ActionPattern actionPattern) {
        if (isTextNotEmpty(String.valueOf(timePane.getTime()))) {
            actionPattern.setTimeValue(timePane.getTime());
            actionPattern.setTimeUnits(timePane.getTimeUnit());
        }
    }
}
