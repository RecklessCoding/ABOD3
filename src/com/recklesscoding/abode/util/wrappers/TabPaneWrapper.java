package com.recklesscoding.abode.util.wrappers;

import com.recklesscoding.abode.gui.layout.EditorViewTab;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class TabPaneWrapper extends TabPane {

    public TabPaneWrapper() {
        setScaleShape(true);
    }

    public void addTab(Tab tab) {
        getTabs().add(tab);
    }

    public EditorViewTab getTab(int i){
        EditorViewTab tab = (EditorViewTab) getTabs().get(i);

        return tab;
    }
}
