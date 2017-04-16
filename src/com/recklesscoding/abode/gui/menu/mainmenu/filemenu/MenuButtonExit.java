package com.recklesscoding.abode.gui.menu.mainmenu.filemenu;

import javafx.application.Platform;
import javafx.scene.control.MenuItem;

/**
 * Created by Andreas on 17/01/2016.
 */
public class MenuButtonExit extends MenuItem {

    public MenuButtonExit() {
        super("Exit");
        this.setOnAction(actionEvent -> Platform.exit());
    }
}
