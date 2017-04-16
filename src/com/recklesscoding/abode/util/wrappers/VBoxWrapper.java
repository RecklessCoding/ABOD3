package com.recklesscoding.abode.util.wrappers;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 * Created by Andreas on 17/01/2016.
 */
public class VBoxWrapper extends VBox {

    public VBoxWrapper(){

    }

    public VBoxWrapper(double spacing) {
        super(spacing);
        setAlignment(Pos.CENTER);
    }

    public void addItem(Node node) {
        super.getChildren().add(node);
    }

    public void removeItem(Node node){
        super.getChildren().remove(node);
    }

    public void removeAllItems()
    {
        super.getChildren().remove(0, getChildren().size()-1);
    }
}
