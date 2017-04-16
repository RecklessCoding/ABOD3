package com.recklesscoding.abode.gui.nodemenu.popups.editelement;

import com.recklesscoding.abode.core.plan.nodes.PlanElementNode;
import com.recklesscoding.abode.core.plan.planelements.PlanElement;
import com.recklesscoding.abode.core.plan.planelements.competence.CompetenceElement;
import com.recklesscoding.abode.core.plan.planelements.drives.DriveCollection;
import com.recklesscoding.abode.gui.nodemenu.popups.panes.GoalsPane;
import com.recklesscoding.abode.gui.nodemenu.popups.panes.RetriesPane;
import com.recklesscoding.abode.gui.nodemenu.popups.panes.SelectRTPane;
import com.recklesscoding.abode.gui.views.diagramview.diagram.GraphWindow;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class EditDriveElement extends EditElementPopup {

    private SelectRTPane rtPane;

    private GoalsPane goalsPane;

    private static final String REAL_TIME = "Real-time";

    private static final String GOALS = "Goals";

    public EditDriveElement(PlanElementNode planElementNode, GraphWindow graphWindow) {
        super(planElementNode, graphWindow);
    }

    @Override
    void addContent(PlanElement planElement) {
        DriveCollection driveCollection = (DriveCollection) planElement;

        goalsPane = new GoalsPane(driveCollection.getGoals());
        rtPane = new SelectRTPane(driveCollection.getIsUsed());

        addContentItem(new Label(REAL_TIME));
        addContentItem(rtPane);
        addContentItem(new Label(GOALS));
        addContentItem(goalsPane);
    }


    @Override
    protected void addSaveButton(Button saveButton, PlanElement planElement) {
        saveButton.setOnAction(event -> {
                    if (isTextNotEmpty(getName())) {
                        CompetenceElement competenceElement = (CompetenceElement) planElement;
                        competenceElement.setNameOfElement(getName());
//                        competenceElement.setRetries(retriesPane.getRetries());
                        refresh();
                    }
                }
        );
    }
}