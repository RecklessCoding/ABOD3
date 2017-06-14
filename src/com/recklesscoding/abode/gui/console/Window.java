package com.recklesscoding.abode.gui.console;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Window extends Stage {

    private static final int WINDOW_WIDTH = 480;

    private static final int WINDOW_HEIGHT = 320;

    private Pane pane;
    private String windowTitle;

    public Window(String windowTitle, Pane pane) {
        this.windowTitle = windowTitle;
        this.pane = pane;

        initProperties();
//        setScene(new Scene(console, WINDOW_WIDTH, WINDOW_HEIGHT));

        initScene();
    }

    private void initProperties() {
        setTitle(windowTitle);
        setResizable(false);
    }

    private void initScene() {
        Scene scene = new Scene(pane, WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.getStylesheets().add("main.css");
        setScene(scene);
    }
}
