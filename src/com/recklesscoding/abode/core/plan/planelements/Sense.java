package com.recklesscoding.abode.core.plan.planelements;

/**
 * Author: @Andreas.
 * Date : @13/01/2016
 */
public class Sense extends PlanElement {

    private String value;

    private String comperator;

    public Sense(String nameOfElement) {
        super(nameOfElement);
    }

    public Sense(String nameOfElement, String comperator, String value) {
        super(nameOfElement);
        this.value = value;
        this.comperator = comperator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        System.out.println(value);
        this.value = value;
    }

    public String getComperator() {
        return comperator;
    }

    public void setComperator(String comperator) {
        this.comperator = comperator;
    }
}