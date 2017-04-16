package com.recklesscoding.abode.gui.nodemenu.popups.editelement;

import com.recklesscoding.abode.core.plan.Plan;
import com.recklesscoding.abode.core.plan.nodes.PlanElementNode;
import com.recklesscoding.abode.core.plan.planelements.ElementWithTrigger;
import com.recklesscoding.abode.core.plan.planelements.PlanElement;
import com.recklesscoding.abode.core.plan.planelements.action.ActionEvent;
import com.recklesscoding.abode.core.plan.planelements.action.ActionPattern;
import com.recklesscoding.abode.core.plan.planelements.competence.CompetenceElement;
import com.recklesscoding.abode.core.plan.planelements.drives.DriveCollection;
import com.recklesscoding.abode.core.plan.planelements.drives.DriveElement;
import com.recklesscoding.abode.gui.nodemenu.popups.newelement.NewElementPopup;
import com.recklesscoding.abode.gui.views.diagramview.diagram.GraphWindow;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class EditAction extends EditElementPopup {

    public EditAction(PlanElementNode planElementNode, GraphWindow graphWindow) {
        super(planElementNode, graphWindow);
    }

    @Override
    void addContent(PlanElement planElement) {
    }

    @Override
    protected void addSaveButton(Button saveButton, PlanElement planElement) {
        saveButton.setOnAction(event -> {
                    if (isTextNotEmpty(getName())) {
                        ActionEvent actionEvent = (ActionEvent) planElement;
                        actionEvent.setNameOfElement(getName());
                        refresh();
                    }
                }
        );
    }
}