package com.recklesscoding.abode.gui.views.actionpatternsview;

import com.recklesscoding.abode.gui.layout.EditorViewTab;
import javafx.stage.Stage;

/**
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 * @see {@link EditorViewTab}
 * <p>
 */
public class ActionPatternsViewTab extends EditorViewTab {

    private static final String ACTION_PATTERN_TAB = "Action Patterns";

    public ActionPatternsViewTab(Stage primaryWindow) {
        super(ACTION_PATTERN_TAB, new ActionPatternsLayout(primaryWindow));
    }

    @Override
    public void refresh(Stage primaryStage) {
        setContent(new ActionPatternsLayout(primaryStage));
    }
}