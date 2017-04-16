package com.recklesscoding.abode.gui.console;

import javafx.application.Platform;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConsoleInputTexField  extends TextField{

    private final List<String> history = new ArrayList<>();

    private int historyPointer = 0;

    public ConsoleInputTexField() {

        System.setIn(new ByteArrayInputStream(getText().getBytes()));
        initEvenHandler();
    }

    private void initEvenHandler() {
        addEventHandler(KeyEvent.KEY_RELEASED, keyEvent -> {
            switch (keyEvent.getCode()) {
                case ENTER:
                    String text = getText();
                    if (text != null && !Objects.equals(text, "")) {
                        System.setIn(new ByteArrayInputStream(getText().getBytes()));
                        historyPointer++;
                        history.add(text);
                        clear();
                    }
                    break;
                case UP:
                    if (historyPointer == 0) {
                        break;
                    }
                    historyPointer--;
                    runSafe(() -> setText(history.get(historyPointer)));
                    break;
                case DOWN:
                    if (historyPointer == history.size() - 1) {
                        break;
                    }
                    historyPointer++;
                    runSafe(() -> {
                        setText(history.get(historyPointer));
                        selectAll();
                    });
                    break;
                default:
                    break;
            }
        });
    }

    private void runSafe(final Runnable runnable) {
        Objects.requireNonNull(runnable, "runnable");
        if (Platform.isFxApplicationThread()) {
            runnable.run();
        } else {
            Platform.runLater(runnable);
        }
    }
}
