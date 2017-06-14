package com.recklesscoding.abode.gui.console;


import javafx.scene.Scene;
import javafx.stage.Stage;

public class ConsoleWindow extends Window {

    public ConsoleWindow(String windowTitle) {
        super(windowTitle, new ConsolePane());
    }
}
