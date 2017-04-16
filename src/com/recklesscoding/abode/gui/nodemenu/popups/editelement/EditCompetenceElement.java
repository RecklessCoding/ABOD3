package com.recklesscoding.abode.gui.nodemenu.popups.editelement;

import com.recklesscoding.abode.core.plan.Plan;
import com.recklesscoding.abode.core.plan.nodes.PlanElementNode;
import com.recklesscoding.abode.core.plan.planelements.PlanElement;
import com.recklesscoding.abode.core.plan.planelements.competence.Competence;
import com.recklesscoding.abode.core.plan.planelements.competence.CompetenceElement;
import com.recklesscoding.abode.gui.nodemenu.popups.newelement.NewElementPopup;
import com.recklesscoding.abode.gui.nodemenu.popups.panes.RetriesPane;
import com.recklesscoding.abode.gui.views.diagramview.diagram.GraphWindow;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class EditCompetenceElement extends EditElementPopup {

    private static final String RETRIES_LABEL = "Num. of retries";

    private RetriesPane retriesPane;

    public EditCompetenceElement(PlanElementNode planElementNode, GraphWindow graphWindow) {
        super(planElementNode, graphWindow);
    }

    @Override
    void addContent(PlanElement planElement) {
        CompetenceElement competenceElement = (CompetenceElement) planElement;
        retriesPane = new RetriesPane(competenceElement.getRetries());
        addContentItem(new Label(RETRIES_LABEL));
        addContentItem(retriesPane);
    }


    @Override
    protected void addSaveButton(Button saveButton, PlanElement planElement) {
        saveButton.setOnAction(event -> {
                    if (isTextNotEmpty(getName())) {
                        CompetenceElement competenceElement = (CompetenceElement) planElement;
                        competenceElement.setNameOfElement(getName());
                        competenceElement.setRetries(retriesPane.getRetries());
                        refresh();
                    }
                }
        );
    }
}