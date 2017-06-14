package com.recklesscoding.abode.gui.console;

import com.recklesscoding.abode.util.wrappers.BorderPaneWrapper;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.PrintStream;

public class MultipleAgentsPane extends BorderPaneWrapper {

    private AgentNumberComboBox agentNumberComboBox = new AgentNumberComboBox();
    private volatile TextArea output = new TextArea();
    private final TextField input = new ConsoleInputTexField();

    public MultipleAgentsPane() {
        initLayout();
        initOutput();
    }

    private void initAgentChooser() {

    }


    private void initOutput() {
        output.setWrapText(true);
        output.setEditable(false);
        PrintStream consolePrintStream = new PrintStream(new ConsoleOutputStream(output), true);

        System.setOut(consolePrintStream);
//        System.setErr(consolePrintStream);
    }

    private void initLayout() {
        setTop(agentNumberComboBox);
        setCenter(output);
        setBottom(input);
    }

    @Override
    public void requestFocus() {
        super.requestFocus();
    }
}
