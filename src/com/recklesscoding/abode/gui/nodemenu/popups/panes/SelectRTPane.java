package com.recklesscoding.abode.gui.nodemenu.popups.panes;

import com.recklesscoding.abode.util.wrappers.BooleanComboBox;
import com.recklesscoding.abode.util.wrappers.HBoxWrapper;
import javafx.geometry.Pos;

/**
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class SelectRTPane extends HBoxWrapper {

    private BooleanComboBox comboBox = new BooleanComboBox();

    public SelectRTPane() {
        init();
    }

    public SelectRTPane(boolean isRT) {
        init();
        comboBox.setValue(isRT);
    }

    private void init() {
        setAlignment(Pos.CENTER);
        addItem(comboBox);
    }

    public boolean isRT() {
        return comboBox.getSelectedValue();
    }
}