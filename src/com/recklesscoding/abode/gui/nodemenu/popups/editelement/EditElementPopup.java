package com.recklesscoding.abode.gui.nodemenu.popups.editelement;

import com.recklesscoding.abode.core.plan.nodes.PlanElementNode;
import com.recklesscoding.abode.core.plan.planelements.PlanElement;
import com.recklesscoding.abode.gui.views.diagramview.diagram.GraphWindow;
import com.recklesscoding.abode.util.wrappers.VBoxWrapper;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.PopOver;

/**
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public abstract class EditElementPopup extends PopOver {

    private VBoxWrapper vBox = new VBoxWrapper(5);

    private Button saveButton = new Button("Save");

    private TextField nameTextField = new TextField();

    private static final String NAME_LABEL = "Name";

    private GraphWindow graphWindow;

    public EditElementPopup(PlanElementNode planElementNode, GraphWindow graphWindow) {
        this.graphWindow = graphWindow;
        String nameOfElement = planElementNode.getPlanElement().getNameOfElement();
        setTitle(nameOfElement);
        addNameTextfield(nameOfElement);
        addContent(planElementNode.getPlanElement());
        addSaveButton(saveButton, planElementNode.getPlanElement());

        init(planElementNode);
    }

    private void addNameTextfield(String nameOfElement) {
        addContentItem(new Label(NAME_LABEL));
        nameTextField = new TextField(nameOfElement);
        addContentItem(nameTextField);
    }

    private void init(PlanElementNode planElementNode) {
        addButtons();
        setContentNode(vBox);
        setHideOnEscape(true);
        show(planElementNode);
    }

    private void addButtons() {
        addContentItem(saveButton);
    }

    abstract void addSaveButton(Button saveButton, PlanElement planElement);

    abstract void addContent(PlanElement planElement);

    void addContentItem(Node node) {
        vBox.addItem(node);
    }

    boolean isTextNotEmpty(String stringToBeChecked) {
        return stringToBeChecked != null && !stringToBeChecked.equals("") && !stringToBeChecked.isEmpty();
    }

    public String getName() {
        return nameTextField.getText();
    }

    void refresh(){
        hide();
        graphWindow.refresh();
    }
}
