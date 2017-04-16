package com.recklesscoding.abode.gui.menu.mainmenu.debugmenu;

import com.recklesscoding.abode.core.Editor;
import javafx.scene.control.SeparatorMenuItem;
import javafx.stage.Stage;
import javafx.stage.Window;
import com.recklesscoding.abode.gui.menu.wrappers.MenuWrapper;

/**
 * Athor: Andreas
 * Date: 17/02/2016.
 */
public class DebugMenu extends MenuWrapper {

    public DebugMenu(Stage primaryStage, Editor editor) {
        super("Debugger", true);

        init(primaryStage, editor);
    }

    private void init(Stage primaryStage, Editor editor) {
        addMenuItem(new MenuButtonStartServer(editor));
        addMenuItem(new SeparatorMenuItem());

        addMenuItem(new MenuButtonOpenLog(primaryStage, editor));
        addMenuItem(new MenuButtonOpenVideo(primaryStage, editor));
        addMenuItem(new MenuButtonStartDebug(editor));
        addMenuItem(new MenuButtonStopDebug(editor));
    }
}
