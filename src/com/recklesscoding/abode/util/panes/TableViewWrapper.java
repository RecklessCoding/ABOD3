package com.recklesscoding.abode.util.panes;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class TableViewWrapper<S> extends TableView {

    public TableViewWrapper() {
        super();
        setEditableTable();
    }

    private void setEditableTable() {
        setEditable(true);
        // allows the individual cells to be selected
        getSelectionModel().cellSelectionEnabledProperty().set(true);
        setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode().isLetterKey() || event.getCode().isDigitKey()) {
                // editFocusedCell();
            } else if (event.getCode() == KeyCode.RIGHT
                    || event.getCode() == KeyCode.TAB) {
                getSelectionModel().selectNext();
                event.consume();
            } else if (event.getCode() == KeyCode.LEFT) {
                getSelectionModel().selectPrevious();
                event.consume();
            }
        });
    }

    private void initColumns(String[] columnsNames) {
        for (String columnName : columnsNames) {
            addColumn(new TableColumn(columnName));
        }
    }

    public void addColumn(TableColumn column) {
        getColumns().add(column);
    }

    public  void addItems(List items)
    {
        getItems().addAll(items);
    }

    public void removeItem(Object item) {
        getItems().remove(item);
    }

    public void removeSelectedItem() {
        if (getSelectionModel().getSelectedItem() != null) {
            removeItem(getSelectionModel().getSelectedItem());
        }
    }

    public void moveSelectedItemUp() {
        if (getSelectionModel().getSelectedIndex() > 0) {
            Collections.swap(getItems(), getSelectionModel().getSelectedIndex(), getSelectionModel().getSelectedIndex() - 1);
            getSelectionModel().select(getSelectionModel().getSelectedIndex() - 1);
        }
    }

    public void moveSelectedItemDown() {
        if (getSelectionModel().getSelectedIndex() < getItems().size()) {
            Collections.swap(getItems(), getSelectionModel().getSelectedIndex(), getSelectionModel().getSelectedIndex() + 1);
            getSelectionModel().select(getSelectionModel().getSelectedIndex() + 1);
        }
    }
}
