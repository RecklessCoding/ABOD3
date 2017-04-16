package com.recklesscoding.abode.gui.console;


import javafx.scene.Scene;
import javafx.stage.Stage;

public class ConsoleWindow extends Stage {

    private static final int WINDOW_WIDTH = 480;

    private static final int WINDOW_HEIGHT = 320;

    private static final String WINDOW_TITLE = "Java Console";

    private ConsolePane console;

    public ConsoleWindow() {

        console = new ConsolePane();

        initProperties();
//        setScene(new Scene(console, WINDOW_WIDTH, WINDOW_HEIGHT));

        initScene();
    }

    private void initProperties() {
        setTitle(WINDOW_TITLE);
        setResizable(false);
    }

    private void initScene() {
        Scene scene = new Scene(console, WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.getStylesheets().add("main.css");
        setScene(scene);
    }
}
