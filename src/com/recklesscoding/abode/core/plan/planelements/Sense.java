package com.recklesscoding.abode.core.plan.planelements;

/**
 * Author: @Andreas.
 * Date : @13/01/2016
 */
public class Sense extends PlanElement {

    private String value;

    private String predicate;

    public Sense(String nameOfElement, String predicate, String value) {
        super(nameOfElement);
        this.value = value;
        this.predicate = predicate;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPredicate() {
        return predicate;
    }

    public void setPredicate(String predicate) {
        this.predicate = predicate;
    }
}