package com.recklesscoding.abode.util.wrappers;

import javafx.scene.Node;
import javafx.scene.layout.FlowPane;

/**
 * Created by Andreas on 17/01/2016.
 */
public class FlowPaneWrapper extends FlowPane {

    public  void addItem(Node item){
        getChildren().add(item);
    }

}
