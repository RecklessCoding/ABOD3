package com.recklesscoding.abode.gui.menu.mainmenu.debugmenu;

import com.recklesscoding.abode.core.Editor;
import com.recklesscoding.abode.gui.menu.menuitems.MenuButtonFileHandler;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.net.MalformedURLException;

/**
 * Created by Andreas on 17/01/2016.
 */
public class MenuButtonOpenVideo extends MenuButtonFileHandler {

    private static final String NAME_LABEL = "Load video file";

    public MenuButtonOpenVideo(Window primaryStage, Editor editor) {
        super(primaryStage, editor, NAME_LABEL, true);

        initFileChooser();
    }

    @Override
    protected void initAction(Window primaryStage, Editor editor) {
        this.setOnAction(actionEvent -> {
            try {
                File file = getFileChooser().showOpenDialog(primaryStage);
                if (file != null) {
                    String filePath = file.toURI().toURL().toExternalForm();
                    editor.getIncinctDebugger().loadVideo(filePath);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    protected void initFileChooser() {
        getFileChooser().setTitle("Open Video File");
        getFileChooser().getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All files", "*.*"),
                new FileChooser.ExtensionFilter("MP4", "*.mp4"),
                new FileChooser.ExtensionFilter("MPEG4", "*.mpeg4"),
                new FileChooser.ExtensionFilter("AVI", "*.avi"));
        getFileChooser().setSelectedExtensionFilter(getFileChooser().getExtensionFilters().get(1));
    }


}