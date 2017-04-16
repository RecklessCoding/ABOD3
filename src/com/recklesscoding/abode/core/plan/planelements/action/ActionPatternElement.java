package com.recklesscoding.abode.core.plan.planelements.action;

import com.recklesscoding.abode.core.plan.Plan;
import com.recklesscoding.abode.core.plan.planelements.ElementWithTrigger;
import com.recklesscoding.abode.core.plan.planelements.TimeUnits;

import java.util.LinkedList;
import java.util.List;

/**
 * Author: Andreas
 * Date: 18/01/2016.
 */
public class ActionPatternElement extends ElementWithTrigger {

    public ActionPatternElement(String nameOfElement) {
        super(nameOfElement);
    }

    public ActionPatternElement(String name, double timeValue, TimeUnits timeUnits, List<ActionEvent> actions) {
        super(name);
    }
}