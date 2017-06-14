package com.recklesscoding.abode.gui.console;

import javafx.application.Platform;

public class ConsoleGUI {

    private static final String WINDOW_TITLE = "Console Server";

    private ConsoleWindow consoleWindow;

    public ConsoleGUI()
    {
        consoleWindow  = new ConsoleWindow(WINDOW_TITLE);
    }

    public void show(){
        Platform.runLater(() -> consoleWindow.show());
    }

    public void hide(){
        consoleWindow.hide();
    }
}
