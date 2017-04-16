package com.recklesscoding.abode.gui.menu.mainmenu.filemenu;

import com.recklesscoding.abode.core.Editor;
import com.recklesscoding.abode.gui.menu.menuitems.MenuButtonFileHandler;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;

/**
 * Created by Andreas on 17/01/2016.
 */
public class MenuButtonSavePlan extends MenuButtonFileHandler {

    private static final String NAME_LABEL = "Save Plan";

    public MenuButtonSavePlan(Window primaryStage, Editor editor) {
        super(primaryStage, editor, NAME_LABEL, true);
    }

    @Override
    protected void initAction(Window primaryStage, Editor editor) {
        this.setOnAction(actionEvent -> {
            File file = getFileChooser().showSaveDialog(primaryStage);
            if (file != null) {
                editor.savePlan(file.getAbsolutePath());
            }
        });
    }

    @Override
    protected  void initFileChooser() {
        getFileChooser().setTitle("Open Video File");
        getFileChooser().getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All files", "*.*"),
                new FileChooser.ExtensionFilter("Lap", "*.lap"));
        getFileChooser().setSelectedExtensionFilter(getFileChooser().getExtensionFilters().get(1));
    }
}