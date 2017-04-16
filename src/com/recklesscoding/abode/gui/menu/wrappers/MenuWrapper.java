package com.recklesscoding.abode.gui.menu.wrappers;

import javafx.collections.ObservableList;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * Created by Andreas on 17/01/2016.
 */
public class MenuWrapper extends Menu {

    public MenuWrapper(String text, boolean keyMnemonicsOn) {
        super(text);

        if (keyMnemonicsOn) {
            setMnemonicParsing(true);
            setText("_" + text);
        }
    }

    public void addMenuItem(MenuItem item) {
        super.getItems().add(item);
    }

    public void addMenuItems(ObservableList<MenuItem> items) {
        super.getItems().addAll(items);
    }
}
