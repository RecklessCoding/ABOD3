package com.recklesscoding.abode.gui.nodemenu.popups.newelement;

import com.recklesscoding.abode.core.plan.Plan;
import com.recklesscoding.abode.core.plan.nodes.PlanElementNode;
import com.recklesscoding.abode.core.plan.planelements.ElementWithTrigger;
import com.recklesscoding.abode.core.plan.planelements.PlanElement;
import com.recklesscoding.abode.core.plan.planelements.competence.CompetenceElement;
import com.recklesscoding.abode.core.plan.planelements.drives.DriveCollection;
import com.recklesscoding.abode.core.plan.planelements.drives.DriveElement;
import com.recklesscoding.abode.gui.nodemenu.popups.panes.SelectRTPane;
import com.recklesscoding.abode.gui.nodemenu.popups.panes.SensesPane;
import com.recklesscoding.abode.gui.views.diagramview.diagram.GraphWindow;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class NewDriveElement extends NewElementPopup {

    private SelectRTPane rtPane;

    private SensesPane sensesPane;

    private static final String TITLE_LABEL = "New Drive Element";

    private static final String SENSES_LABEL = "Senses";

    public NewDriveElement(PlanElementNode planElementNode, GraphWindow graphWindow) {
        super(TITLE_LABEL, planElementNode, graphWindow);
    }

    @Override
    void addContent() {
        sensesPane = new SensesPane();
        addContentItem(new Label(SENSES_LABEL));
        addContentItem(new SensesPane());
    }

    @Override
    protected void addSaveButton(Button saveButton, PlanElement fatherElement) {
        saveButton.setOnAction(event -> {
                    if (isTextNotEmpty(getName())) {
                        DriveElement driveElement = new DriveElement(getName());
                        driveElement.setDriveElementSenses(sensesPane.getSenses());
                        saveDE(driveElement, fatherElement);
                    }
                }
        );
    }

    private void saveDE(DriveElement driveElement, PlanElement fatherElement) {
        Plan.getInstance().addDriveElement(driveElement);
        DriveCollection driveCollection = (DriveCollection) fatherElement;
        driveCollection.addDriveElement(driveElement);
        refresh();
    }

    private void setTriggeredElement(PlanElement fatherElement, DriveCollection driveCollection) {
        if (fatherElement instanceof CompetenceElement || fatherElement instanceof DriveElement || fatherElement instanceof DriveCollection) {
            ElementWithTrigger elementWithTrigger = (ElementWithTrigger) fatherElement;
            elementWithTrigger.setTriggeredElement(driveCollection);
        }
    }

    private void setTime(DriveCollection driveCollection) {

    }
}
