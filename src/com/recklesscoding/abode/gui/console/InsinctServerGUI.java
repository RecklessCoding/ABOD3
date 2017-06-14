package com.recklesscoding.abode.gui.console;

import javafx.application.Platform;

public class InsinctServerGUI {

    private static final String WINDOW_TITLE = "Insinct Server";

    private ConsoleWindow consoleWindow;

    public InsinctServerGUI()
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
