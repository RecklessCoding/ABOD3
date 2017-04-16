package com.recklesscoding.abode.gui.menu.mainmenu.viewmenu;

import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;
import com.recklesscoding.abode.gui.menu.mainmenu.debugmenu.MenuButtonOpenLog;

/**
 * Created by Andreas on 17/01/2016.
 */
public class MenuButtonSettings extends MenuItem {

    public MenuButtonSettings(String name, boolean keyMnemonicsOn) {
        super(name);
        initButton(keyMnemonicsOn);
    }

    private void initButton(boolean keyMnemonicsOn) {
        setMnemonicParsing(keyMnemonicsOn);
        setDisable(true);
        initAction();
    }

    private void initAction() {
        setOnAction((ActionEvent actionEvent) -> {
            for (MenuItem menuItem : this.getParentMenu().getItems()) {
                if (menuItem instanceof MenuButtonOpenLog) {
                    setDisable(true);
                    break;
                }
            }
        });
    }
}