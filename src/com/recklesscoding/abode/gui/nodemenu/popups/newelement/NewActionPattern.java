package com.recklesscoding.abode.gui.nodemenu.popups.newelement;

import com.recklesscoding.abode.core.plan.Plan;
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
public class NewActionPattern extends NewElementPopup {

    private SelectTimePane timePane;

    private ActionsPane actionsPane;

    private static final String TITLE_LABEL = "New Action Pattern";

    private static final String TIME_LABEL = "Time";

    private static final String ACTIONS_LABEL = "Actions";

    public NewActionPattern(PlanElementNode planElementNode, GraphWindow graphWindow) {
        super(TITLE_LABEL, planElementNode, graphWindow);
    }

    @Override
    void addContent() {
        actionsPane = new ActionsPane(null);
        timePane = new SelectTimePane();

        addContentItem(new Label(TIME_LABEL));
        addContentItem(timePane);
        addContentItem(new Label(ACTIONS_LABEL));
        addContentItem(actionsPane);
    }

    @Override
    protected void addSaveButton(Button saveButton, PlanElement fatherElement) {
        saveButton.setOnAction(event -> {
                    if (isTextNotEmpty(getName())) {
                        ActionPattern actionPattern = new ActionPattern(getName());
                        setTime(actionPattern);
                        setActions(actionPattern);
                        saveActions(fatherElement, actionPattern);
                    }
                }
        );
    }

    private void saveActions(PlanElement fatherElement, ActionPattern actionPattern) {
        if (fatherElement instanceof CompetenceElement || fatherElement instanceof DriveElement || fatherElement instanceof DriveCollection) {
            ElementWithTrigger elementWithTrigger = (ElementWithTrigger) fatherElement;
            elementWithTrigger.setTriggeredElement(actionPattern);
        }
        Plan.getInstance().addActionPattern(actionPattern);
        refresh();
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
