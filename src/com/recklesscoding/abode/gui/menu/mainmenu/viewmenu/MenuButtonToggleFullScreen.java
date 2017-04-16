package com.recklesscoding.abode.gui.menu.mainmenu.viewmenu;

import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * Created by Andreas on 17/01/2016.
 */
public class MenuButtonToggleFullScreen extends MenuItem {

    private boolean isFullScreen = false;

    public MenuButtonToggleFullScreen(Stage primaryWindow) {
        super("Enter Full Screen");
        initButton(true);
        initAction(primaryWindow);
    }

    private void initButton(boolean keyMnemonicsOn) {
        setMnemonicParsing(keyMnemonicsOn);
    }

    private void initAction(Stage primaryWindow) {
        setOnAction((ActionEvent actionEvent) -> {
            if (!isFullScreen) {
                primaryWindow.setFullScreen(true);
                setText("Enter Full Screen");
                isFullScreen = true;
            } else {
                primaryWindow.setFullScreen(false);
                setText("Exit Full Screen");
                isFullScreen = false;
            }
        });
    }
}