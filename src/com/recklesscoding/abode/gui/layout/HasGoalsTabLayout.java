package com.recklesscoding.abode.gui.layout;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import com.recklesscoding.abode.util.wrappers.ListViewWrapper;
import com.recklesscoding.abode.util.wrappers.VBoxWrapper;
import com.recklesscoding.abode.core.plan.planelements.Sense;

/**
 * Creates the graphviewer of views which include elements with goals.
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public abstract class HasGoalsTabLayout extends TabLayout {

    private TextField textGoalName;

    private TextField textGoalValue;

    private TextField textGoalPredicate;

    private ObservableList<Sense> senses = FXCollections.observableArrayList();

    private Label sensesLabel;

    public HasGoalsTabLayout(Stage primaryStage, Label nameLabel) {
        super(primaryStage, nameLabel);
        sensesList();
        addSensesComponent();
    }

    private void sensesList() {
        sensesLabel = new Label("Goals: ");
        this.getChildren().add(sensesLabel);
        ListViewWrapper goals = new ListViewWrapper(senses);
        goals.setScaleShape(true);
        goals.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue instanceof Sense) {
                    Sense goal = (Sense) newValue;
                    this.textGoalName.setText(goal.getNameOfElement());
                    this.textGoalPredicate.setText(goal.getPredicate());
                    this.textGoalValue.setText(goal.getValue());
                }
            }
        });
        this.getChildren().add(goals);
    }

    private void addSensesComponent() {
        textGoalName = new TextField();
        textGoalValue = new TextField();
        textGoalPredicate = new TextField();

        VBoxWrapper vbox = new VBoxWrapper();
        vbox.setScaleShape(true);
        Label labelName = new Label("Name:");
        vbox.addItem(labelName);
        vbox.addItem(textGoalName);
        Label labelPredicate = new Label("Predicate:");
        vbox.addItem(labelPredicate);
        vbox.addItem(textGoalPredicate);
        Label labelValue = new Label("Value:");
        vbox.addItem(labelValue);
        vbox.addItem(textGoalValue);
        vbox.setScaleShape(true);

        this.getChildren().add(vbox);
    }

    protected ObservableList<Sense> getSensesList() {
        return senses;
    }

    protected Label getSensesLabel() {
        return sensesLabel;
    }
}
