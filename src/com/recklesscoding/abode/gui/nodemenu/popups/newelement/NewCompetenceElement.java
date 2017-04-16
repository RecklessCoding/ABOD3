package com.recklesscoding.abode.gui.nodemenu.popups.newelement;

import com.recklesscoding.abode.core.plan.nodes.PlanElementNode;
import com.recklesscoding.abode.core.plan.planelements.PlanElement;
import com.recklesscoding.abode.core.plan.planelements.competence.Competence;
import com.recklesscoding.abode.core.plan.planelements.competence.CompetenceElement;
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
public class NewCompetenceElement extends NewElementPopup {

    private static final String TITLE_LABEL = "New Competence Element";

    private static final String RETRIES_LABEL = "Num. of retries";

    private RetriesPane retriesPane;

    public NewCompetenceElement(PlanElementNode planElementNode, GraphWindow graphWindow) {
        super(TITLE_LABEL, planElementNode, graphWindow);
    }

    @Override
    void addContent() {
        retriesPane = new RetriesPane(0);
        addContentItem(new Label(RETRIES_LABEL));
        addContentItem(retriesPane);
    }


    @Override
    protected void addSaveButton(Button saveButton, PlanElement fatherElement) {
        saveButton.setOnAction(event -> {
                    if (isTextNotEmpty(getName())) {
                        CompetenceElement competenceElement = new CompetenceElement(getName());
                        setRetries(competenceElement);
                        saveCompetenceElement(fatherElement, competenceElement);
                    }
                }
        );
    }

    private void saveCompetenceElement(PlanElement fatherElement, CompetenceElement competenceElement) {
        Competence competence = (Competence) fatherElement;
        competence.addCompetenceElement(competenceElement);
        refresh();
    }


    private void setRetries(CompetenceElement competenceElement) {
        if (isTextNotEmpty(String.valueOf(retriesPane.getRetries()))) {
            competenceElement.setRetries(retriesPane.getRetries());
        }
    }
}