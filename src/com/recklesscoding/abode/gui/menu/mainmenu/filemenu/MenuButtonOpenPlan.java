package com.recklesscoding.abode.gui.menu.mainmenu.filemenu;

import com.recklesscoding.abode.core.Editor;
import com.recklesscoding.abode.gui.menu.menuitems.MenuButtonFileHandler;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;

public class MenuButtonOpenPlan extends MenuButtonFileHandler {

    private static final String NAME_LABEL = "Load Plan";

    public MenuButtonOpenPlan(Window primaryStage, Editor editor) {
        super(primaryStage, editor, NAME_LABEL, true);
    }

    @Override
    protected void initAction(Window primaryStage, Editor editor) {
        this.setOnAction(actionEvent -> {
            File file = getFileChooser().showOpenDialog(primaryStage);
            if (file != null) {
                editor.readPlanFile(file.getAbsolutePath());
            }
        });
    }

    @Override
    protected void initFileChooser() {
        getFileChooser().setTitle("Open Video File");
        getFileChooser().getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All files", "*.*"),
                new FileChooser.ExtensionFilter("XPOSH", "*.xml"), new FileChooser.ExtensionFilter("Instinct", "*.inst"),
                new FileChooser.ExtensionFilter("Lap", "*.lap"));
        getFileChooser().setSelectedExtensionFilter(getFileChooser().getExtensionFilters().get(1));
    }
}