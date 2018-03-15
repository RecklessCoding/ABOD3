package com.recklesscoding.abode.util.panes;

import com.recklesscoding.abode.core.plan.planelements.Sense;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.cell.TextFieldTableCell;

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

    public SensesTableView() {
        super();

        init();
    }

    /**
     * @param list Context that will be added the source list
     */
    public SensesTableView(ObservableList list) {
        super();
        init();
        getTable().addItems(list);
    }

    private void init() {
        TableColumn<Sense, String> nameColumn = new TableColumn<>(NAME_COLUMN);
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNameOfElement()));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setMinWidth(200);

        nameColumn.setOnEditCommit((TableColumn.CellEditEvent<Sense, String> event) -> {
            TablePosition<Sense, String> pos = event.getTablePosition();

            String newFullName = event.getNewValue();

            int row = pos.getRow();
            Sense sense = event.getTableView().getItems().get(row);

            sense.setNameOfElement(newFullName);
        });

        TableColumn<Sense, String> predicateColumn = new TableColumn<>(PREDICATE_COLUMN);
        predicateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getComperator()));

        TableColumn<Sense, String> valueColumn = new TableColumn<>(VALUE_COLUMN);
        valueColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue()));

        getTable().setEditable(true);
        getTable().addColumn(nameColumn);
        getTable().addColumn(predicateColumn);
        getTable().addColumn(valueColumn);



    }


}