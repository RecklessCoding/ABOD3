package com.recklesscoding.abode.gui.nodemenu.popups.panes;

import com.recklesscoding.abode.util.wrappers.HBoxWrapper;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;

/**
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class RetriesPane extends HBoxWrapper {

    private TextField retriesTexfield = new TextField();

    public RetriesPane(int retries) {
        retriesTexfield = new TextField(String.valueOf(retries));
        retriesTexfield.setAlignment(Pos.CENTER);
        init();
    }

    private void init() {
        setSpacing(5);
        setAlignment(Pos.CENTER);
        addItem(retriesTexfield);
    }

    public int getRetries() {
        return Integer.parseInt(retriesTexfield.getText());
    }

}