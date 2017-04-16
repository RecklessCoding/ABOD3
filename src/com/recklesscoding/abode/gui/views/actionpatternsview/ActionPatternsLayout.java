package com.recklesscoding.abode.gui.views.actionpatternsview;

import com.recklesscoding.abode.core.plan.planelements.action.ActionPattern;
import com.recklesscoding.abode.gui.layout.TabLayout;
import com.recklesscoding.abode.core.plan.planelements.action.ActionEvent;
import com.recklesscoding.abode.util.wrappers.ListViewWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;
import com.recklesscoding.abode.gui.trees.TreeItemWrapper;

/**
 * Creates the graphviewer of the items inside the action patterns view.
 * <p>
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class ActionPatternsLayout extends TabLayout {

    private final static Label NAME_LABEL = new Label("Action Patterns:");

    private ActionPatternsTree actionPatternsTree;

    private ObservableList<ActionEvent> actionsList = FXCollections.observableArrayList();

    public ActionPatternsLayout(Stage primaryStage) {
        super(primaryStage, NAME_LABEL);
        addActions();
    }

    private void addActions() {
        this.getChildren().add(new Label("Actions:"));
        ListViewWrapper actions = new ListViewWrapper(actionsList);
        actions.setScaleShape(true);
        this.getChildren().add(actions);
    }

    @Override
    protected void addTree(Window primaryStage) {
        actionPatternsTree = new ActionPatternsTree(primaryStage);
        this.getChildren().add(actionPatternsTree);
    }

    @Override
    protected void addTreeViewListener() {
        actionPatternsTree.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            if (newValue != null) {
                TreeItemWrapper treeItem = (TreeItemWrapper) newValue;
                if (treeItem.getValue() instanceof ActionPattern) {
                    ActionPattern actionPattern = (ActionPattern) treeItem.getValue();
                    this.getTextName().setText(actionPattern.getNameOfElement());
                    this.getTextTime().setText(String.valueOf(actionPattern.getTimeValue()));
                    this.actionsList.clear();
                    this.actionsList.addAll(actionPattern.getActionEvents());
                }
            }
        });
    }
}