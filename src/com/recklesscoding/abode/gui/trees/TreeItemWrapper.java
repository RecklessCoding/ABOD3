package com.recklesscoding.abode.gui.trees;

import javafx.scene.control.TreeItem;

/**
 * Wraps a {@link TreeItem} making it easier to add new items to it.
 * <p>
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class TreeItemWrapper<PlanElement> extends TreeItem<PlanElement> {

    public TreeItemWrapper(PlanElement element) {
        super(element);
        setExpanded(true);
    }

    public void addChild(TreeItemWrapper item) {
        this.getChildren().add(item);
    }
}
