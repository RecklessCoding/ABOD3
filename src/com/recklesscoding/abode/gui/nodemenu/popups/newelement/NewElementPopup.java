package com.recklesscoding.abode.gui.nodemenu.popups.newelement;

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
public abstract class NewElementPopup extends PopOver {

    private VBoxWrapper vBox = new VBoxWrapper(5);

    private Button saveButton = new Button("Save");

    private TextField nameTextField = new TextField();

    private static final String NAME_LABEL = "Name";

    private GraphWindow graphWindow;

    public NewElementPopup(String label, PlanElementNode planElementNode, GraphWindow graphWindow) {
        this.graphWindow = graphWindow;
        setTitle(label);
        addNameTextfield();
        addContent();
        addSaveButton(saveButton, planElementNode.getPlanElement());

        init(planElementNode);
    }

    private void addNameTextfield() {
        addContentItem(new Label(NAME_LABEL));
        addContentItem(nameTextField);
    }

    private void init(PlanElementNode planElementNode) {
        addButtons();
        setContentNode(vBox);
        setHideOnEscape(true);
        show(planElementNode);
    }

    private void addButtons() {
        GridPane gridPane = new GridPane();
        gridPane.add(saveButton, 1, 1);
        gridPane.setScaleShape(true);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(50);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(50);
        gridPane.getColumnConstraints().addAll(column1, column2); // each get 50% of width
//        addContentItem(gridPane);

        addContentItem(saveButton);
    }

    abstract void addSaveButton(Button saveButton, PlanElement planElement);

    abstract void addContent();

    void addContentItem(Node node) {
        vBox.addItem(node);
    }

    boolean isTextNotEmpty(String stringToBeChecked) {
        return stringToBeChecked != null && !stringToBeChecked.equals("") && !stringToBeChecked.isEmpty();
    }

    void refresh(){
        hide();
        graphWindow.refresh();
    }

    public String getName() {
        return nameTextField.getText();
    }
}
