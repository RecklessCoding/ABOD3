package com.recklesscoding.abode.util.panes;

import com.recklesscoding.abode.util.wrappers.HBoxWrapper;
import com.recklesscoding.abode.util.wrappers.ListViewWrapper;
import com.recklesscoding.abode.util.wrappers.VBoxWrapper;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.util.Collections;

/**
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class MultipleListsView extends HBoxWrapper {

    private ListViewWrapper sourceListView;

    private ListViewWrapper targetListView;

    /**
     * @param sourceListHeader  Header that goes above the source list
     * @param targetListHeader  Header that goes above the target list
     * @param sourceList        Context that will be added the source list
     * @param targetList        Context that will be added the target list
     */
    public MultipleListsView(String sourceListHeader, String targetListHeader, ObservableList sourceList, ObservableList targetList) {
        setSpacing(5);
        initSource(sourceListHeader, sourceList);
        initButtons();
        initTargetList(targetListHeader, targetList);
    }

    private void initSource(String sourceListHeader, ObservableList sourceList) {
        Label header = new Label(sourceListHeader);
        sourceListView = new ListViewWrapper(sourceList);

        addHeaderListVBox(header, sourceListView);
    }

    private void initTargetList(String targetListHeader, ObservableList targetList) {
        Label header = new Label(targetListHeader);
        targetListView = new ListViewWrapper(targetList);

        addHeaderListVBox(header, targetListView);
    }

    private void addHeaderListVBox(Label header, ListViewWrapper list) {
        VBoxWrapper vBox = new VBoxWrapper(); // Contains the header-list
        vBox.addItem(header);
        vBox.addItem(list);
        addItem(vBox);
    }

    private void initButtons() {
        Button addButton = new Button(">");
        addButton.setOnAction(event -> {
            if (sourceListView.getSelectionModel().getSelectedItem() != null) {
                targetListView.addItem(sourceListView.getSelectionModel().getSelectedItem());
            }
        });

        Button removeButton = new Button("<");
        removeButton.setOnAction(event -> {
            if (targetListView.getSelectionModel().getSelectedItem() != null) {
                targetListView.removeItem(targetListView.getSelectionModel().getSelectedItem());
            }
        });

        Button moveUp = new Button("\u2191");
        moveUp.setOnAction(event -> {
//            Collections.swap(sourceListView.getItems(), sourceListView.getSelectionModel().getSelectedIndex(), sourceListView.getSelectionModel().getSelectedIndex()-1);
            if (targetListView.getSelectionModel().getSelectedIndex() > 0) {
                Collections.swap(targetListView.getItems(), targetListView.getSelectionModel().getSelectedIndex(), targetListView.getSelectionModel().getSelectedIndex() - 1);
                targetListView.getSelectionModel().select(targetListView.getSelectionModel().getSelectedIndex() - 1);
            }
        });


        Button moveDown = new Button("\u2193");
        moveDown.setOnAction(event -> {
//            Collections.swap(sourceListView.getItems(), sourceListView.getSelectionModel().getSelectedIndex(), sourceListView.getSelectionModel().getSelectedIndex()-1);
            if (targetListView.getSelectionModel().getSelectedIndex() < targetListView.getItems().size()) {
                Collections.swap(targetListView.getItems(), targetListView.getSelectionModel().getSelectedIndex(), targetListView.getSelectionModel().getSelectedIndex() + 1);
                targetListView.getSelectionModel().select(targetListView.getSelectionModel().getSelectedIndex() + 1);
            }
        });

        addButtonsVBox(addButton, removeButton, moveUp, moveDown);
    }

    private void addButtonsVBox(Button addButton, Button removeButton, Button moveUp, Button moveDown) {
        VBoxWrapper vBoxWrapper = new VBoxWrapper();
        vBoxWrapper.setSpacing(10);
        vBoxWrapper.addItem(new Pane()); // Used to add space
        vBoxWrapper.addItem(new Pane());
        vBoxWrapper.addItem(addButton);
        vBoxWrapper.addItem(removeButton);
        vBoxWrapper.addItem(new Pane());
        vBoxWrapper.addItem(moveUp);
        vBoxWrapper.addItem(moveDown);
        addItem(vBoxWrapper);
    }
}