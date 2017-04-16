package com.recklesscoding.abode.gui.nodemenu.popups.editelement;

import com.recklesscoding.abode.core.plan.Plan;
import com.recklesscoding.abode.core.plan.nodes.PlanElementNode;
import com.recklesscoding.abode.core.plan.planelements.PlanElement;
import com.recklesscoding.abode.core.plan.planelements.competence.Competence;
import com.recklesscoding.abode.core.plan.planelements.drives.DriveCollection;
import com.recklesscoding.abode.gui.nodemenu.popups.panes.GoalsPane;
import com.recklesscoding.abode.gui.nodemenu.popups.panes.SelectRTPane;
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
public class EditDriveCollection extends EditElementPopup {

    private SelectRTPane rtPane;

    private GoalsPane goalsPane;

    private static final String REAL_TIME = "Real-time";

    private static final String GOALS = "Goals";

    public EditDriveCollection(PlanElementNode planElementNode, GraphWindow graphWindow) {
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
                        DriveCollection driveCollection = (DriveCollection) planElement;
                        driveCollection.setNameOfElement(getName());
//                        driveCollection.setGoals(goalsPane.getGoals());
                        setRT(driveCollection);
                    }
                }
        );
    }

    private void saveActions(Competence competence) {
        Plan.getInstance().addCompetence(competence);
        refresh();
    }

    private void setRT(DriveCollection driveCollection) {
        driveCollection.setRealTime(rtPane.isRT());
    }
}
