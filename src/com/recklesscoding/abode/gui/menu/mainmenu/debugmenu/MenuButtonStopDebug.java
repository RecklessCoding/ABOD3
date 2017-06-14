package com.recklesscoding.abode.gui.menu.mainmenu.debugmenu;

import com.recklesscoding.abode.core.Editor;
import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;

/**
 * Created by Andreas on 17/01/2016.
 */
public class MenuButtonStopDebug extends MenuItem {

    private static final String NAME_LABEL = "Stop Debugging";

    public MenuButtonStopDebug(Editor editor) {
        super(NAME_LABEL);
        initButton(editor);

        setVisible(false);
        setDisable(true);
    }

    private void initButton(Editor editor) {
        setMnemonicParsing(true);
        initAction(editor);
    }

    private void initAction(Editor editor) {
        setOnAction((ActionEvent actionEvent) -> {
            editor.getIncinctDebugger().stopLogDebugger();
            for (MenuItem menuItem : this.getParentMenu().getItems()) {
                if (menuItem instanceof MenuButtonOpenLog || menuItem instanceof MenuButtonOpenVideo) {
                    menuItem.setDisable(false);
                }
                if (menuItem instanceof MenuButtonStartDebug) {
                    menuItem.setDisable(true);
                    menuItem.setVisible(false);
                }
            }
            setDisable(true);
        });
    }
}