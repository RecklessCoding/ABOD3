package com.recklesscoding.abode.util.panes;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.Collections;

/**
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class TableViewWrapper<S> extends TableView {

    public TableViewWrapper() {
        super();
    }

    public TableViewWrapper(String[] columnsNames) {

        initColumns(columnsNames);
    }

    public TableViewWrapper(String[] columnsNames, ObservableList sourceList) {
        super(sourceList);

        initColumns(columnsNames);
    }

    private void initColumns(String[] columnsNames) {
        for (String columnName : columnsNames) {
            addColumn(new TableColumn(columnName));
        }
    }

    public void addColumn(TableColumn column) {
        getColumns().add(column);
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
