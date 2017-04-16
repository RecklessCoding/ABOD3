package com.recklesscoding.abode.util.wrappers;

import javafx.scene.control.ComboBox;

/**
 * Created by Andreas on 19/01/2016.
 */
public class BooleanComboBox extends ComboBox {

    public BooleanComboBox() {
        addOptions();
    }

    private void addOptions() {
        getItems().add("True");
        getItems().add("False");
        setValue(getItems().get(0));
    }


    public boolean getSelectedValue() {
        return getValue().toString().startsWith("T");
    }

    public void setValue(boolean value){
        if (value){
            setValue(getItems().get(0));
        } else {
            setValue(getItems().get(1));
        }
    }
}
