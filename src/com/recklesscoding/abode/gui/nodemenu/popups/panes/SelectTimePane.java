package com.recklesscoding.abode.gui.nodemenu.popups.panes;

import com.recklesscoding.abode.core.plan.planelements.TimeUnits;
import com.recklesscoding.abode.util.wrappers.HBoxWrapper;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class SelectTimePane extends HBoxWrapper {

    private ComboBox comboBox = new ComboBox();

    private TextField timeText = new TextField();

    public SelectTimePane() {
        init();
    }

    public SelectTimePane(double time, TimeUnits timeUnits) {
        init();
        timeText.setText(String.valueOf(time));
        comboBox.setValue(timeUnits);
    }

    private void init() {
        setSpacing(5);
        setAlignment(Pos.CENTER);
        comboBox.setItems(TimeUnits.getAll());
        comboBox.setValue(TimeUnits.SECONDS.toString());
        timeText.setAlignment(Pos.CENTER);
        timeText.setText("0");
        addItem(timeText);
        addItem(comboBox);
    }

    public double getTime() {
        return Double.parseDouble(timeText.getText());
    }

    public TimeUnits getTimeUnit() {
        return TimeUnits.getTimeUnits(comboBox.getSelectionModel().getSelectedItem().toString());
    }
}