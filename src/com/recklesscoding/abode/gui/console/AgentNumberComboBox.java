package com.recklesscoding.abode.gui.console;

import javafx.scene.control.ComboBox;

/**
 * Created by atheo on 12/06/2017.
 */
public class AgentNumberComboBox extends ComboBox<Integer> {

    public void updateNumber(int newNumber) {

        for (int i = 1; i <= newNumber; i++) {
            getItems().add(i);
        }
    }

    public int currentNumber() {
        return getSelectionModel().getSelectedItem();
    }
}
