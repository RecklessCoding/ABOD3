package com.recklesscoding.abode.util.panes;

import com.recklesscoding.abode.core.plan.planelements.Sense;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
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

/**
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class SensesTableView extends TableViewPanel {

    private static final String NAME_COLUMN = "Name";
    private static final String PREDICATE_COLUMN = "Predicate";
    private static final String VALUE_COLUMN = "Value";

    /**
     * @param list Context that will be added the source list
     */
    public SensesTableView(ObservableList list) {
        super();
        init();
        getTable().addItems(list);

    }

    public ObservableList<Sense> getSenses() {
        return getTable().getItems();
    }

    private void init() {
        setTableEditable();

        TableColumn<Sense, String> nameColumn = new TableColumn(NAME_COLUMN);
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNameOfElement()));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setMinWidth(200);
        nameColumn.setOnEditCommit(event ->
        {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setNameOfElement(event.getNewValue());
            getTable().refresh();
        });

        TableColumn<Sense, String> predicateColumn = new TableColumn<>(PREDICATE_COLUMN);
        predicateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getComperator()));
        predicateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        predicateColumn.setMinWidth(75);
        predicateColumn.setOnEditCommit(event ->
        {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setComperator(event.getNewValue());
            getTable().refresh();
        });


        TableColumn<Sense, String> valueColumn = new TableColumn<>(VALUE_COLUMN);
        valueColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue()));
        valueColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        valueColumn.setMinWidth(75);
        valueColumn.setOnEditCommit(event ->
        {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setValue(event.getNewValue());
            getTable().refresh();
        });

        getTable().setEditable(true);
        getTable().addColumn(nameColumn);
        getTable().addColumn(predicateColumn);
        getTable().addColumn(valueColumn);
    }

    private void setTableEditable() {
        getTable().setEditable(true);
        getTable().getSelectionModel().cellSelectionEnabledProperty().set(true);
        getTable().setOnKeyPressed(event -> {
            if (event.getCode().isLetterKey() || event.getCode().isDigitKey()) {
                editFocusedCell();
            } else if (event.getCode() == KeyCode.RIGHT ||
                    event.getCode() == KeyCode.TAB) {
                getTable().getSelectionModel().selectNext();
                event.consume();
            } else if (event.getCode() == KeyCode.LEFT) {
                getTable().getSelectionModel().selectPrevious();
                event.consume();
            }
        });
    }


    @SuppressWarnings("unchecked")
    private void editFocusedCell() {
        ObjectProperty<TableView.TableViewFocusModel<Sense>> objectProperty = getTable()
                .focusModelProperty();
        final TablePosition focusedCell = objectProperty.get().focusedCellProperty().get();

        getTable().edit(focusedCell.getRow(), focusedCell.getTableColumn());
    }
}