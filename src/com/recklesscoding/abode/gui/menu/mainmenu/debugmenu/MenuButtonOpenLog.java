package com.recklesscoding.abode.gui.menu.mainmenu.debugmenu;

import com.recklesscoding.abode.core.Editor;
import com.recklesscoding.abode.gui.menu.menuitems.MenuButtonFileHandler;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;

/**
 * Created by Andreas on 17/01/2016.
 */
public class MenuButtonOpenLog extends MenuButtonFileHandler {

    private static final String NAME_LABEL = "Open log file";

    public MenuButtonOpenLog(Stage primaryStage, Editor editor) {
        super(primaryStage, editor, NAME_LABEL, true);

        initFileChooser();
        initAction(primaryStage, editor);
    }

    @Override
    protected void initAction(Window primaryStage, Editor editor) {
        this.setOnAction(actionEvent -> {
            File file = getFileChooser().showOpenDialog(primaryStage);
            if (file != null) {
                editor.getDebugger().readLogFile(file.getAbsolutePath());
                for (MenuItem menuItem : this.getParentMenu().getItems()) {
                    if (menuItem instanceof MenuButtonStartDebug) {
                        menuItem.setDisable(false);
                        break;
                    }
                }
            }
        });
    }

    @Override
    protected void initFileChooser() {
        getFileChooser().setTitle("Open Resource File");
        getFileChooser().getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All files", "*.*"),
                new FileChooser.ExtensionFilter("Text file", "*.txt"),
                new FileChooser.ExtensionFilter("CSV", "*.csv"));
        getFileChooser().setSelectedExtensionFilter(getFileChooser().getExtensionFilters().get(1));
    }
}
