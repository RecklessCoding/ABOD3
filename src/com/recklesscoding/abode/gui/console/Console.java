package com.recklesscoding.abode.gui.console;

import javafx.application.Platform;

public class Console {

    private static Console instance = null;

    private ConsoleWindow consoleWindow  = new ConsoleWindow();

    private Console() {
    }

    public static Console getInstance() {
        if (instance == null) {
            instance = new Console();
        }
        return instance;
    }

    public void show(){
        Platform.runLater(() -> consoleWindow.show());
    }

    public void hide(){
        consoleWindow.hide();
    }
}
