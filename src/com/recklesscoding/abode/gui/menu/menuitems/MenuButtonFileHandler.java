package com.recklesscoding.abode.gui.menu.menuitems;

import com.recklesscoding.abode.core.Editor;
import com.recklesscoding.abode.gui.EditorLayout;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;

/**
 * Created by Andreas on 17/01/2016.
 */
public abstract class MenuButtonFileHandler extends MenuItem {

    private FileChooser fileChooser;

    public MenuButtonFileHandler(Window primaryStage, Editor editor, String name, boolean keyMnemonicsOn) {
        super(name);

        setMnemonicParsing(keyMnemonicsOn);
        this.fileChooser = new FileChooser();
        this.fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        initAction(primaryStage, editor);
        initFileChooser();
    }

    protected abstract void initFileChooser();

    protected abstract void initAction(Window primaryStage, Editor editor);

    protected FileChooser getFileChooser() {
        return fileChooser;
    }
}