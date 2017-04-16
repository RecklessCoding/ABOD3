package com.recklesscoding.abode.gui.views.actionpatternsview;

import com.recklesscoding.abode.gui.trees.PlanTree;
import com.recklesscoding.abode.core.plan.Plan;
import com.recklesscoding.abode.core.plan.planelements.PlanElement;
import com.recklesscoding.abode.core.plan.planelements.action.ActionPattern;
import javafx.stage.Window;
import com.recklesscoding.abode.gui.trees.TreeItemWrapper;

import java.util.List;

/**
 * Creates a tree of {@link javafx.scene.control.TreeItem} containing action patterns.
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class ActionPatternsTree extends PlanTree {

    public ActionPatternsTree(Window primaryStage) {
        super(primaryStage);

        init();
    }

    private void init() {
        this.setEditable(true);
        this.setShowRoot(false);
    }

    @Override
    protected void createTree() {
        createActionTree();
    }

    private void createActionTree() {
        TreeItemWrapper<PlanElement> rootItem = new TreeItemWrapper<>(new PlanElement(null));

        List<ActionPattern> actionPatterns = Plan.getInstance().getActionPatterns();

        TreeItemWrapper<PlanElement> actionPatternItem;
        for (ActionPattern action : actionPatterns) {
            actionPatternItem = new TreeItemWrapper<>(action);
            rootItem.addChild(actionPatternItem);
        }

        super.setRoot(rootItem);
    }
}