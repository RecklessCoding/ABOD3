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
public class ActionNode extends PlanElementNode {

    public ActionNode(PlanElement planElement) {
        super(planElement, Color.GREEN);
    }
}