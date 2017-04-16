package com.recklesscoding.abode.gui.menu.wrappers;

import javafx.scene.control.MenuBar;

/**
 * Created by Andreas on 17/01/2016.
 */
public class MenuBarWrapper extends MenuBar {

    public void addMenu(MenuWrapper menu) {
        super.getMenus().add(menu);
    }
}
