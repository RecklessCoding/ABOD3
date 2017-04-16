package com.recklesscoding.abode.util.panes;

import com.recklesscoding.abode.core.plan.planelements.Sense;
import com.recklesscoding.abode.util.wrappers.HBoxWrapper;
import com.recklesscoding.abode.util.wrappers.ListViewWrapper;
import com.recklesscoding.abode.util.wrappers.VBoxWrapper;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

import java.util.Collections;

/**
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class TableViewPanel<PlanElement> extends VBoxWrapper {

    private TableViewWrapper<PlanElement> table;

    public TableViewPanel() {
        table = new TableViewWrapper();

        init();
    }

    /**
     * @param list        Context that will be added the source list
     */
    public TableViewPanel(ObservableList list) {
        table = new TableViewWrapper();

        init();
    }

    private void init() {
        setSpacing(5);
        addItem(table);
        initButtons();
    }

    private void initButtons() {
        Button addButton = new Button("*");
        addButton.setOnAction(event -> {
            Sense sense = new Sense("Test", "=", "0");
            table.getItems().add(sense);
        });

        Button removeButton = new Button("\u2718");
        removeButton.setOnAction(event -> table.removeSelectedItem());

        Button moveUp = new Button("\u25B2");
        moveUp.setOnAction(event -> {
//            Collections.swap(sourceListView.getItems(), sourceListView.getSelectionModel().getSelectedIndex(), sourceListView.getSelectionModel().getSelectedIndex()-1);
         table.moveSelectedItemUp();
        });


        Button moveDown = new Button("\u25BC");
        moveDown.setOnAction(event -> {
//            Collections.swap(sourceListView.getItems(), sourceListView.getSelectionModel().getSelectedIndex(), sourceListView.getSelectionModel().getSelectedIndex()-1);
           table.moveSelectedItemDown();
        });

        addButtonsVBox(addButton, removeButton, moveUp, moveDown);
    }

    protected TableViewWrapper<PlanElement> getTable(){
        return table;
    }


    private void addButtonsVBox(Button addButton, Button removeButton, Button moveUp, Button moveDown) {
        HBoxWrapper hBoxWrapper = new HBoxWrapper();
        hBoxWrapper.setSpacing(10);
        hBoxWrapper.addItem(addButton);
        hBoxWrapper.addItem(removeButton);
        hBoxWrapper.addItem(new Pane());
        hBoxWrapper.addItem(new Pane());
        hBoxWrapper.addItem(moveUp);
        hBoxWrapper.addItem(moveDown);
        addItem(hBoxWrapper);
    }
}