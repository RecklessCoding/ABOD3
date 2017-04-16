package com.recklesscoding.abode.gui.menu.mainmenu.debugmenu;

import com.recklesscoding.abode.core.Editor;
import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;

/**
 * Created by Andreas on 17/01/2016.
 */
public class MenuButtonStartDebug extends MenuItem {

    private static final String NAME_LABEL = "Start Debugging";

    public MenuButtonStartDebug(Editor editor) {
        super(NAME_LABEL);
        initButton(editor);
    }

    private void initButton(Editor editor) {
        setMnemonicParsing(true);
        setDisable(true);
        initAction(editor);
    }

    private void initAction(Editor editor) {
        setOnAction((ActionEvent actionEvent) -> {
            editor.getDebugger().startLogDebugger();
            for (MenuItem menuItem : this.getParentMenu().getItems()) {
                if (menuItem instanceof MenuButtonOpenLog) {
                    menuItem.setDisable(true);
                }
                if (menuItem instanceof MenuButtonStopDebug) {
                    menuItem.setDisable(false);
                    menuItem.setVisible(true);
                }
            }
            setVisible(false);
            setDisable(false);
        });
    }
}