package com.recklesscoding.abode.gui.menu.mainmenu;

import com.recklesscoding.abode.core.Editor;
import com.recklesscoding.abode.gui.menu.mainmenu.debugmenu.DebugMenu;
import com.recklesscoding.abode.gui.menu.mainmenu.filemenu.FileMenu;
import javafx.stage.Stage;
import com.recklesscoding.abode.gui.menu.mainmenu.viewmenu.ViewMenu;
import com.recklesscoding.abode.gui.menu.wrappers.MenuBarWrapper;

/**
 * Athor: Andreas
 * Date: 17/02/2016.
 */
public class MainMenuBar extends MenuBarWrapper {

    public MainMenuBar(Stage primaryStage, Editor editor) {
        initFileMenu(primaryStage, editor);
        initDebugMenu(primaryStage, editor);
        initViewMenu(primaryStage, editor);
    }

    private void initViewMenu(Stage primaryStage, Editor editor) {
        addMenu(new ViewMenu(primaryStage, editor.getEditorLayout()));
    }

    private void initFileMenu(Stage primaryStage, Editor editor) {
        addMenu(new FileMenu(primaryStage, editor));
    }

    private void initDebugMenu(Stage primaryStage, Editor editor) {
        addMenu(new DebugMenu(primaryStage, editor));
    }
}
