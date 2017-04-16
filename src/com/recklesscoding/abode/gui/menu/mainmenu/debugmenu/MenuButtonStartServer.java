package com.recklesscoding.abode.gui.menu.mainmenu.debugmenu;

import com.recklesscoding.abode.core.Editor;
import com.recklesscoding.abode.gui.console.Console;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;

/**
 * Created by Andreas on 17/01/2016.
 */
public class MenuButtonStartServer extends MenuItem {

    private static final String NAME_LABEL_START = "Start Server";

    private static final String NAME_LABEL_RESTART = "Restart Server";

    public MenuButtonStartServer(Editor editor) {
        super(NAME_LABEL_START);
        initButton(editor);
    }

    private void initButton(Editor editor) {
        setMnemonicParsing(true);
        setDisable(false);
        initAction(editor);
    }

    private void initAction(Editor editor) {
        setOnAction((ActionEvent actionEvent) -> {
            Platform.runLater(() -> {
                Console.getInstance().show();
                editor.getDebugger().startServer();
            });

            for (MenuItem menuItem : this.getParentMenu().getItems()) {
                if (menuItem instanceof MenuButtonStartServer) {
                setText(NAME_LABEL_RESTART);
                }
                if (menuItem instanceof MenuButtonStartDebug) {
                    menuItem.setDisable(false);
                }
            }
        });
    }
}