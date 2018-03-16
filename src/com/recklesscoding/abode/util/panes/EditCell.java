package com.recklesscoding.abode.util.panes;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;

public class EditCell<S, T> extends TextFieldTableCell<S, T> {


    private TextField textField;

    private boolean escapePressed = false;

    private TablePosition<S, ?> tablePos = null;


    public EditCell(final StringConverter<T> converter) {
        super(converter);
    }

    public static <S> Callback<TableColumn<S,
            String>, TableCell<S,
            String>> forTableColumn() {
        return forTableColumn(new DefaultStringConverter());
    }


    public static <S,
            T> Callback<TableColumn<S,
            T>,
            TableCell<S,
                    T>> forTableColumn(
            final StringConverter<T> converter) {
        return list -> new EditCell<S, T>(converter);
    }

    @Override
    public void startEdit() {
        if (!isEditable() || !getTableView().isEditable() ||
                !getTableColumn().isEditable()) {
            return;
        }

        super.startEdit();

        if (isEditing()) {
            if (textField == null) {
                textField = getTextField();
            }

            escapePressed = false;
            startEdit(textField);
            final TableView<S> table = getTableView();
            tablePos = table.getEditingCell();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void commitEdit(T newValue) {
        if (!isEditing())
            return;

        final TableView<S> table = getTableView();

        if (table != null) {
            // Inform the TableView of the edit being ready to be committed.
            TableColumn.CellEditEvent editEvent = new TableColumn.CellEditEvent(table, tablePos,
                    TableColumn.editCommitEvent(), newValue);
            Event.fireEvent(getTableColumn(), editEvent);
        }

        super.cancelEdit();
        updateItem(newValue, false);

        if (table != null) {
            table.edit(-1, null);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cancelEdit() {
        if (escapePressed) {
            super.cancelEdit();
            setText(getItemText()); // restore the original text in the view
        } else {
            String newText = textField.getText();
            this.commitEdit(getConverter().fromString(newText));
        }
        setGraphic(null); // stop editing with TextField
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);

        updateItem();
    }


    private TextField getTextField() {
        final TextField textField = new TextField(getItemText());

        textField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("hi");
            }
        });

        textField.setOnAction(event -> {
            if (getConverter() == null) {
                throw new IllegalStateException("StringConverter is null.");
            }
            this.commitEdit(getConverter().fromString(textField.getText()));
            event.consume();
        });

        textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                commitEdit(getConverter().fromString(textField.getText()));
            }
        });

        textField.setOnKeyPressed(key -> {
            escapePressed = key.getCode() == KeyCode.ESCAPE;
        });

        textField.setOnKeyReleased(key -> {
            if (key.getCode() == KeyCode.ESCAPE) {
                throw new IllegalArgumentException(
                        "did not expect esc key releases here.");
            }
        });


        textField.addEventFilter(KeyEvent.KEY_PRESSED, event -> {

        if (event.getCode() == KeyCode.ESCAPE) {

            textField.setText(getConverter().toString(getItem()));

            cancelEdit();

            event.consume();

        } else if (event.getCode() == KeyCode.RIGHT ||

                event.getCode() == KeyCode.TAB) {

            getTableView().getSelectionModel().selectNext();

            event.consume();

        } else if (event.getCode() == KeyCode.LEFT) {

            getTableView().getSelectionModel().selectPrevious();

            event.consume();

        } else if (event.getCode() == KeyCode.UP) {

            getTableView().getSelectionModel().selectAboveCell();

            event.consume();

        } else if (event.getCode() == KeyCode.DOWN) {
            getTableView().getSelectionModel().selectBelowCell();
            event.consume();
        }
        });

        return textField;
    }


    private String getItemText() {
        return getConverter() == null ?
                getItem() == null ? "" : getItem().toString() :
                getConverter().toString(getItem());
    }


    private void updateItem() {

        if (isEmpty()) {

            setText(null);

            setGraphic(null);

        } else {

            if (isEditing()) {

                if (textField != null) {

                    textField.setText(getItemText());

                }

                setText(null);

                setGraphic(textField);

            } else {

                setText(getItemText());

                setGraphic(null);

            }

        }

    }


    private void startEdit(final TextField textField) {

        if (textField != null) {

            textField.setText(getItemText());

        }

        setText(null);

        setGraphic(textField);

        textField.selectAll();

        // requesting focus so that key input can immediately go into the

        // TextField

        textField.requestFocus();

    }

}
