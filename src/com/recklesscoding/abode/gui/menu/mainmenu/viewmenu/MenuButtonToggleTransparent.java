package com.recklesscoding.abode.gui.menu.mainmenu.viewmenu;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by Andreas on 17/01/2016.
 */
public class MenuButtonToggleTransparent extends MenuItem {

    private boolean isTransparentMode = false;

    public MenuButtonToggleTransparent(Stage primaryWindow) {
        super("Switch to transparent mode");
        initButton(true);
        initAction(primaryWindow);
    }

    private void initButton(boolean keyMnemonicsOn) {
        setMnemonicParsing(keyMnemonicsOn);
    }

    private void initAction(Stage primaryWindow) {
        setOnAction((ActionEvent actionEvent) -> {
            if (isTransparentMode) {

                primaryWindow.getScene().getRoot().setStyle("-fx-background-color: transparent");
                setText("Switch from transparent mode");
                isTransparentMode = true;
            } else {
                primaryWindow.getScene().getRoot().setStyle("-fx-background-color: transparent");

                setText("Switch to transparent mode");
                isTransparentMode = false;
            }
        });
    }
}