package com.recklesscoding.abode.gui.nodemenu.popups.newelement;

import com.recklesscoding.abode.core.plan.Plan;
import com.recklesscoding.abode.core.plan.nodes.PlanElementNode;
import com.recklesscoding.abode.core.plan.planelements.ElementWithTrigger;
import com.recklesscoding.abode.core.plan.planelements.PlanElement;
import com.recklesscoding.abode.core.plan.planelements.competence.Competence;
import com.recklesscoding.abode.core.plan.planelements.competence.CompetenceElement;
import com.recklesscoding.abode.core.plan.planelements.drives.DriveCollection;
import com.recklesscoding.abode.core.plan.planelements.drives.DriveElement;
import com.recklesscoding.abode.gui.nodemenu.popups.panes.GoalsPane;
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
public class NewCompetence extends NewElementPopup {

    private SelectTimePane timePane;

    private GoalsPane goalsPane;

    private static final String TITLE_LABEL = "New Competence";

    private static final String TIME_LABEL = "Timeout";

    private static final String GOALS = "Goals";

    public NewCompetence(PlanElementNode planElementNode, GraphWindow graphWindow) {
        super(TITLE_LABEL, planElementNode, graphWindow);
    }

    @Override
    void addContent() {
        goalsPane = new GoalsPane();
        timePane = new SelectTimePane();
        addContentItem(new Label(TIME_LABEL));
        addContentItem(timePane);
        addContentItem(new Label(GOALS));
        addContentItem(goalsPane);
    }


    @Override
    protected void addSaveButton(Button saveButton, PlanElement fatherElement) {
        saveButton.setOnAction(event -> {
                    if (isTextNotEmpty(getName())) {
                        Competence competence = new Competence(getName());
                        setTriggeredElement(fatherElement, competence);
                        setTime(competence);
                        saveActions(competence);
                    }
                }
        );
    }

    private void saveActions(Competence competence) {
        Plan.getInstance().addCompetence(competence);
        refresh();
    }

    private void setTriggeredElement(PlanElement fatherElement, Competence competence) {
        if (fatherElement instanceof CompetenceElement || fatherElement instanceof DriveElement || fatherElement instanceof DriveCollection) {
            ElementWithTrigger elementWithTrigger = (ElementWithTrigger) fatherElement;
            elementWithTrigger.setTriggeredElement(competence);
        }
    }

    private void setTime(Competence competence) {
            competence.setTimeout(timePane.getTime());
            competence.setTimeUnits(timePane.getTimeUnit());
    }
}
