package com.recklesscoding.abode.gui.menu.mainmenu.filemenu;

import com.recklesscoding.abode.core.Editor;
import javafx.scene.control.SeparatorMenuItem;
import javafx.stage.Window;
import com.recklesscoding.abode.gui.menu.wrappers.MenuWrapper;

/**
 * Athor: Andreas
 * Date: 17/02/2016.
 */
public class FileMenu extends MenuWrapper {
    
    public FileMenu(Window primaryStage, Editor editor) {
        super("File", true);

        init(primaryStage, editor);
    }

    private void init(Window primaryStage, Editor editor) {
        addMenuItem(new MenuButtonOpenPlan(primaryStage, editor));
        addMenuItem(new MenuButtonSavePlan(primaryStage, editor));
        addMenuItem(new SeparatorMenuItem());
        addMenuItem(new MenuButtonOpenLayout(primaryStage, editor));
        addMenuItem(new MenuButtonSaveLayout(primaryStage, editor));
        addMenuItem(new SeparatorMenuItem());
        addMenuItem(new MenuButtonExit());
    }
}