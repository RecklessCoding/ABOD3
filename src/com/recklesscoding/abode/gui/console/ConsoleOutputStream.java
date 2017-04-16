package com.recklesscoding.abode.gui.console;

import javafx.application.Platform;
import javafx.scene.control.*;

import java.io.IOException;
import java.io.OutputStream;

public class ConsoleOutputStream extends OutputStream {

    private volatile TextArea output;

    public ConsoleOutputStream(TextArea output) {
        this.output = output;
    }

    @Override
    public void write(int b) throws IOException {
        Platform.runLater(() -> {
            output.appendText(String.valueOf((char) b));
            output.setScrollTop(Double.MIN_VALUE);
        });
    }
}
