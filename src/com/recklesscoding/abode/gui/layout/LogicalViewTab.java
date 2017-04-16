package com.recklesscoding.abode.gui.layout;

import com.recklesscoding.abode.gui.views.logicalview.LogicalViewLayout;
import com.recklesscoding.abode.gui.views.logicalview.LogicalViewTree;
import javafx.scene.control.Tab;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class LogicalViewTab extends EditorViewTab {

    public LogicalViewTab(Stage primaryStage) {
        super("Logical view", null);
        setContent(new LogicalViewLayout(primaryStage));
    }

    @Override
    public void refresh(Stage primaryStage) {
        setContent(new LogicalViewLayout(primaryStage));
    }
}
