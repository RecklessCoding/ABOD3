package com.recklesscoding.abode.gui.layout;

import javafx.scene.control.Tab;
import javafx.stage.Stage;

/**
 * Extends {@link Tab} by adding an option to refreshLayout the content inside the tab.
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public abstract class EditorViewTab extends Tab {

    private TabLayout content;

    public EditorViewTab(String tabName, TabLayout content) {
        super(tabName);

        this.content = content;
        setContent(content);
    }

    public abstract void refresh(Stage primaryStage);
}
