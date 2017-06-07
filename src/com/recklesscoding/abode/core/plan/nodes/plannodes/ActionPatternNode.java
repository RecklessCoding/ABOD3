package com.recklesscoding.abode.core.plan.nodes.plannodes;

import com.recklesscoding.abode.core.plan.nodes.PlanElementNode;
import com.recklesscoding.abode.core.plan.planelements.PlanElement;
import javafx.scene.paint.Color;

/**
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 * @see PlanElementNode
 * </p>
 */
public class ActionPatternNode extends PlanElementNode {

    private boolean isActivated = false;
    private int nodesActivated = 0;

    public ActionPatternNode(PlanElement planElement) {
        super(planElement, Color.DARKOLIVEGREEN);
    }

    public void activate() {
        nodesActivated++;

        if (nodesActivated < getCellChildren().size())
        {
            isActivated = true;
        }

        if (nodesActivated == getCellChildren().size())
        {
            isActivated = false;
            nodesActivated = 0;
        }
    }

    public boolean isActivated() {
        return isActivated;
    }
}