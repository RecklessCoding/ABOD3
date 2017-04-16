package com.recklesscoding.abode.util.wrappers;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

/**
 * Created by Andreas on 19/01/2016.
 */
public class ListViewWrapper extends ListView {

    public ListViewWrapper() {
    }

    public ListViewWrapper(ObservableList list) {
        super(list);
    }
    public void addItem(Object item){
        getItems().add(item);
    }

    public void removeItem(Object item){
        getItems().remove(item);
    }

    public void addItems(ObservableList list){
        getItems().addAll(list);
    }

    public void removeItems(ObservableList list){
        getItems().removeAll(list);
    }
}
