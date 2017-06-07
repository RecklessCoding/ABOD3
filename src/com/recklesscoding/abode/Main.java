package com.recklesscoding.abode;

import com.recklesscoding.abode.core.Editor;
import com.recklesscoding.abode.debugger.realtime.xposh.XPOSHUnityServer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * <p>
 * ----------------------------------------------------
 * ----_    ____   ___  ____  ______ -----Advanced-----
 * ---/ \  | __ ) / _ \|  _ \|_ __ | -----Behavior-----
 * --/ _ \ |  _ \| | | | | |  ___| | -----Oriented-----
 * -/ ___ \| |_) | |_| | |_|  ___| | ------Design------
 * /_/   \_\____/ \___/|____/|_____| ----Environment---
 * ----------------------------------------------------
 * </p>
 * <p>
 * AUTHOR:            @Author(name = "Andreas Theodorou")
 * VERSION:           @version
 * LICENSING MODEL:   TBA
 * COPYRIGHT:         MIT License
 * </p>
 * <p> A POSH plan IDE. </p>
 * <p>
 * <p> Copyright (c) <year> <copyright holders> </p>
 *
 * <p> Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions: </p>
 *
 * <p> The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.
 * <p> * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.<p></p>
 */
public class Main extends Application {

    /**
     * Holds an instance of the views
     */
    private Editor editor;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryWindow) {

        new Thread(new XPOSHUnityServer()).start();

        editor = new Editor(primaryWindow);
        initWindow(primaryWindow);
        primaryWindow.show();
    }

    /**
     * Initialises the stage setting a scene and other properties/parameters.
     *
     * @param primaryWindow
     */
    private void initWindow(Stage primaryWindow) {
        primaryWindow.setTitle("ABOD3");
        Scene scene = new Scene(editor.getEditorLayout(), 1440, 900);
        scene.setFill(Color.DARKGRAY);
        scene.getStylesheets().add("main.css");
        primaryWindow.setScene(scene);
        primaryWindow.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });
    }
}