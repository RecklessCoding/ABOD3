package com.recklesscoding.abode.util.wrappers;

import javafx.scene.Node;
import javafx.scene.layout.HBox;

/**
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class HBoxWrapper extends HBox{

    public void addItem(Node node) {
        super.getChildren().add(node);
    }

}
