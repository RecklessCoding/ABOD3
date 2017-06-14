package com.recklesscoding.abode.gui.console;

import javafx.application.Platform;

public class UnityPOSHServerGUI {

    private static final String WINDOW_TITLE = "Unity POSH Server";

    private MultipleAgentsWindow consoleWindow;

    public UnityPOSHServerGUI() {
        consoleWindow = new MultipleAgentsWindow(WINDOW_TITLE);
    }

    public void show() {
        Platform.runLater(() -> consoleWindow.show());
    }

    public void hide() {
        consoleWindow.hide();
    }
}
