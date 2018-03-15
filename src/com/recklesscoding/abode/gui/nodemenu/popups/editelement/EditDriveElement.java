package com.recklesscoding.abode.gui.nodemenu.popups.editelement;

import com.recklesscoding.abode.core.plan.nodes.PlanElementNode;
import com.recklesscoding.abode.core.plan.planelements.PlanElement;
import com.recklesscoding.abode.core.plan.planelements.drives.DriveElement;
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
public class EditDriveElement extends EditElementPopup {

    private SensesPane sensesPane;

    private static final String SENSES_LABEL = "Senses";

    public EditDriveElement(PlanElementNode planElementNode, GraphWindow graphWindow) {
        super(planElementNode, graphWindow);
    }

    @Override
    void addContent(PlanElement planElement) {
        DriveElement driveElement = (DriveElement) planElement;

        sensesPane = new SensesPane(driveElement.getSenses());

        addContentItem(new Label(SENSES_LABEL));
        addContentItem(sensesPane);
    }


    @Override
    protected void addSaveButton(Button saveButton, PlanElement planElement) {
        saveButton.setOnAction(event -> {
                    if (isTextNotEmpty(getName())) {
                        DriveElement driveElement = (DriveElement) planElement;
                        driveElement.setNameOfElement(getName());
                        driveElement.setDriveElementSenses(sensesPane.getSenses());
                        refresh();
                    }
                }
        );
    }
}