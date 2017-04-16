package com.recklesscoding.abode.gui.views.logicalview;

import com.recklesscoding.abode.core.plan.planelements.action.ActionEvent;
import com.recklesscoding.abode.util.wrappers.FlowPaneWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Window;

/**
 * Creates the graphviewer of the items inside the action patterns view.
 * <p>
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class LogicalViewLayout extends FlowPaneWrapper {

    private LogicalViewTree logicalViewTree;

    private ObservableList<ActionEvent> actionsList = FXCollections.observableArrayList();

    public LogicalViewLayout(Window primaryStage) {
        addTree(primaryStage);
    }

    protected void addTree(Window primaryStage) {
//        Text text = new Text("Action Patterns: ");
//        text.setX(50);
//        this.getChildren().add(text);
        logicalViewTree = new LogicalViewTree(primaryStage);
        this.getChildren().add(logicalViewTree);
    }

}