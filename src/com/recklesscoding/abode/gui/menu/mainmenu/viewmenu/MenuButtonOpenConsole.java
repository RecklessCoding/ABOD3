package com.recklesscoding.abode.gui.menu.mainmenu.viewmenu;

import com.recklesscoding.abode.gui.console.Console;
import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * Created by Andreas on 17/01/2016.
 */
public class MenuButtonOpenConsole extends MenuItem {

    private boolean isFullScreen = false;

    private static final String NAME_LABEL = "Open Console";

    public MenuButtonOpenConsole(Stage primaryWindow) {
        super(NAME_LABEL);
        initButton(true);
        initAction(primaryWindow);
    }

    private void initButton(boolean keyMnemonicsOn) {
        setMnemonicParsing(keyMnemonicsOn);
    }

    private void initAction(Stage primaryWindow) {
        setOnAction((ActionEvent actionEvent) -> {
            Console.getInstance().show();
        });
    }
}