package com.recklesscoding.abode.gui.menu.mainmenu.viewmenu;

import com.recklesscoding.abode.gui.EditorLayout;
import com.recklesscoding.abode.gui.menu.wrappers.MenuWrapper;
import javafx.scene.control.SeparatorMenuItem;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * Athor: Andreas
 * Date: 17/02/2016.
 */
public class ViewMenu extends MenuWrapper {

    public ViewMenu(Stage primaryStage, EditorLayout editorLayout) {
        super("View", true);

        init(primaryStage, editorLayout);
    }

    private void init(Stage primaryStage, EditorLayout editorLayout) {
        addMenuItem(new MenuButtonToggleFullScreen(primaryStage));
        addMenuItem(new SeparatorMenuItem());
        addMenuItem(new MenuButtonDiagramOrientation(editorLayout));
        addMenuItem(new SeparatorMenuItem());
        addMenuItem(new MenuButtonOpenConsole(primaryStage));

    }
}
