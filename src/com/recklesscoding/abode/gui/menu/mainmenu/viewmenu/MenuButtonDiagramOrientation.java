package com.recklesscoding.abode.gui.menu.mainmenu.viewmenu;

import com.recklesscoding.abode.gui.EditorLayout;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;

/**
 * Created by Andreas on 17/01/2016.
 */
public class MenuButtonDiagramOrientation extends MenuItem {

    private static final String NAME_LABEL = "Switch Orientation";

    public MenuButtonDiagramOrientation(EditorLayout editorLayout) {
        super(NAME_LABEL);
        initButton(true);
        initAction(editorLayout);
    }

    private void initButton(boolean keyMnemonicsOn) {
        setMnemonicParsing(keyMnemonicsOn);
    }

    private void initAction(EditorLayout editorLayout) {
        setOnAction((ActionEvent actionEvent) -> {
            Platform.runLater(() -> editorLayout.switchOrientation());
        });
    }
}