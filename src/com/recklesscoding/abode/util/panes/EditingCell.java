package com.recklesscoding.abode.util.panes;

import com.recklesscoding.abode.core.plan.planelements.Sense;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class EditingCell extends TableCell<Sense, String> {

    private TextField textField;

    public EditingCell() {
    }
    @Override
    public void startEdit() {
        super.startEdit();
        if (textField == null) {
            createTextField();
        }
        setGraphic(textField);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        Platform.runLater(() -> {
            textField.requestFocus();
            textField.selectAll();
        });
    }
    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText(getItem());
        setContentDisplay(ContentDisplay.TEXT_ONLY);
    }
    @Override
    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getString());
                }
                setGraphic(textField);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            } else {
                setText(getString());
                setContentDisplay(ContentDisplay.TEXT_ONLY);
            }
        }
    }
    private void createTextField() {
        textField = new TextField(getString());
        textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        textField.setOnKeyPressed((javafx.scene.input.KeyEvent event) -> {
                if (event.getCode() == KeyCode.ENTER) {
                commitEdit(textField.getText());
            } else if (event.getCode() == KeyCode.ESCAPE) {
                cancelEdit();
            }
        });
        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue && textField != null) {
                    commitEdit(textField.getText());
                }
            }
        });
    }
    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }
}
