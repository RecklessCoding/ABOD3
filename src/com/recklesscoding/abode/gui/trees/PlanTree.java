package com.recklesscoding.abode.gui.trees;

import com.recklesscoding.abode.core.plan.planelements.PlanElement;
import com.recklesscoding.abode.gui.TextFieldTreeCellImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Window;

/**
 * Creates the graphviewer of the .
 * <p>>
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public abstract class PlanTree extends TreeView {

    private Window primaryStage;

    public PlanTree(Window primaryStage) {
        this.primaryStage = primaryStage;
        createTree();

        init();
    }

    private void init() {
        this.setEditable(true);
        this.setShowRoot(false);

        setCellFactory(p -> new TextFieldTreeCellImpl(primaryStage));
    }

    public PlanTree getTree() {
        return this;
    }

    protected abstract void createTree();

    protected void addItem() {

    }
}
