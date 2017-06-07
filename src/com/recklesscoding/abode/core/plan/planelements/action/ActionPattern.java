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
public class ActionPattern extends ElementWithTrigger {

    private double timeValue;

    private TimeUnits timeUnits = TimeUnits.SECONDS;

    private List<ActionPatternElement> actionPatternElements = new LinkedList<>();

    private List<ActionEvent> actionEvents = new LinkedList<>();

    public ActionPattern(String nameOfElement) {
        super(nameOfElement);
    }

    public ActionPattern(String name,List<ActionEvent> actions) {
        super(name);

        this.actionEvents = actions;
    }

    public ActionPattern(String name, double timeValue, TimeUnits timeUnits, List<ActionEvent> actions) {
        super(name);

        this.timeValue = timeValue;
        this.timeUnits = timeUnits;
        this.actionEvents = actions;
    }

    public void addAction(ActionEvent actionEvent) {
        if (Plan.getInstance().getActionEvents().contains(actionEvent))
            actionEvents.add(actionEvent);
        else {
            Plan.getInstance().getActionEvents().add(actionEvent);
            actionEvents.add(actionEvent);
        }
    }

    public void addActionPatternElemment(ActionPatternElement actionPatternElement) {
        actionPatternElements.add(actionPatternElement);
    }

    public List<ActionEvent> getActionEvents() {
        return actionEvents;
    }

    public List<ActionPatternElement> getActionPatternElements() {
        return actionPatternElements;
    }

    public void setActions(List<ActionEvent> actions) {
        this.actionEvents = actions;
    }

    public void setTimeValue(double timeValue) {
        this.timeValue = timeValue;
    }

    public void setTimeUnits(TimeUnits timeUnits) {
        this.timeUnits = timeUnits;
    }

    public double getTimeValue() {
        return timeValue;
    }

    public TimeUnits getTimeUnits() {
        return timeUnits;
    }

    public void clearActionEvents() {
        actionEvents.clear();
    }

    public void removeActionEvent(ActionEvent actionEvent) {
        actionEvents.remove(actionEvent);
    }

}