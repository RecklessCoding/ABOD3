package com.recklesscoding.abode.gui.nodemenu.popups.editelement;

import com.recklesscoding.abode.core.plan.Plan;
import com.recklesscoding.abode.core.plan.nodes.PlanElementNode;
import com.recklesscoding.abode.core.plan.planelements.PlanElement;
import com.recklesscoding.abode.core.plan.planelements.competence.Competence;
import com.recklesscoding.abode.gui.nodemenu.popups.panes.SensesPane;
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
public class EditCompetence extends EditElementPopup {

    private SelectTimePane timePane;

    private SensesPane sensesPane;

    private static final String TIME_LABEL = "Timeout";

    private static final String GOALS = "Goals";

    public EditCompetence(PlanElementNode planElementNode, GraphWindow graphWindow) {
        super(planElementNode, graphWindow);
    }

    @Override
    void addContent(PlanElement planElement) {
        Competence competence = (Competence) planElement;
        sensesPane = new SensesPane(competence.getGoals());
        timePane = new SelectTimePane(competence.getTimeout(), competence.getTimeUnits());
        addContentItem(new Label(TIME_LABEL));
        addContentItem(timePane);
        addContentItem(new Label(GOALS));
        addContentItem(sensesPane);
    }


    @Override
    protected void addSaveButton(Button saveButton, PlanElement planElement) {
        saveButton.setOnAction(event -> {
                    if (isTextNotEmpty(getName())) {
                        Competence competence = (Competence) planElement;
                        competence.setNameOfElement(getName());
                        competence.setGoals(sensesPane.getSenses());
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

    private void setTime(Competence competence) {
        competence.setTimeout(timePane.getTime());
        competence.setTimeUnits(timePane.getTimeUnit());
    }
}
