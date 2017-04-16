package com.recklesscoding.abode.gui.nodemenu.controller;

import com.recklesscoding.abode.core.plan.nodes.PlanElementNode;
import com.recklesscoding.abode.gui.views.diagramview.diagram.GraphWindow;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public abstract class PlanElementMenuItem extends MenuItem {

    public PlanElementMenuItem(Stage primaryStage, String label, PlanElementNode planElementNode, GraphWindow graphWindow) {
        super(label);
    }
}
