package com.recklesscoding.abode.core.plan;

import com.recklesscoding.abode.core.plan.planelements.PlanElement;
import com.recklesscoding.abode.core.plan.planelements.action.ActionEvent;
import com.recklesscoding.abode.core.plan.planelements.action.ActionPattern;
import com.recklesscoding.abode.core.plan.planelements.competence.Competence;
import com.recklesscoding.abode.core.plan.planelements.competence.CompetenceElement;
import com.recklesscoding.abode.core.plan.planelements.drives.DriveCollection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Author: @Andreas.
 * Date : @07/01/2016
 */
public class Plan {

    private static Plan instance = null;

    private volatile List<ActionEvent> actionEvents = new LinkedList<>();

    private volatile List<ActionPattern> actionPatterns = new LinkedList<>();

    private volatile List<Competence> competences = new LinkedList<>();

    private volatile List<DriveCollection> driveCollections = new LinkedList<>();

    private Plan() {
    }

    public static Plan getInstance() {
        if (instance == null) {
            instance = new Plan();
        }
        return instance;
    }

    public void cleanAllLists() {
        actionPatterns.clear();
        competences.clear();
        driveCollections.clear();
    }

    public void addActionPattern(ActionPattern action) {
        actionPatterns.add(action);
    }

    public void addCompetence(Competence competence) {
        competences.add(competence);
    }

    public void addDriveCollection(DriveCollection driveCollection) {
        this.driveCollections.add(driveCollection);
    }

    public List<DriveCollection> getDriveCollections() {
        return driveCollections;
    }

    public List<Competence> getCompetences() {
        return competences;
    }

    public List<ActionPattern> getActionPatterns() {
        return actionPatterns;
    }

    public List<ActionEvent> getActionEvents() {
        return actionEvents;
    }

    public void removeDriveCollection(DriveCollection driveCollection) {
        driveCollections.remove(driveCollection);
    }

    public ActionEvent createAction(String name) {
        ActionEvent action = findAction(name);
        if (action != null) {
            return action;
        }
        action = new ActionEvent(name);
        actionEvents.add(action);

        return action;
    }

    public ActionEvent findAction(String name) {
        for (ActionEvent actionEvent : actionEvents) {
            if (actionEvent.getNameOfElement().equals(name)) {
                return actionEvent;
            }
        }
        return null;
    }

    public PlanElement findActionPattern(String name) {
        for (ActionPattern actionsPattern : actionPatterns) {
            if (actionsPattern.getNameOfElement().equals(name)) {
                return actionsPattern;
            }
        }
        return null;
    }


    public Competence findCompetence(String name) {
        for (Competence competence : competences) {
            if (competence.getNameOfElement().equals(name)) {
                return competence;
            }
        }
        return null;
    }

    public CompetenceElement findCompetenceElement(String name) {
        for (Competence competence : competences) {
            for (CompetenceElement competenceElement : competence.getCompetenceElements()) {
                if (competenceElement.getNameOfElement().equals(name))
                    return competenceElement;
            }
        }
        return null;
    }

    public DriveCollection findDriveCollection(String name) {
        for (DriveCollection driveCollection : driveCollections) {
            if (driveCollection.getNameOfElement().equals(name)) {
                return driveCollection;
            }
        }
        return null;
    }

    public PlanElement findActionPatternOrCompetence(String name) {
        for (Competence competence : competences) {
            if (competence.getNameOfElement().equals(name)) {
                return competence;
            }
        }
        return findActionPattern(name);
    }

    public List<ActionPattern> findActionPatternsWithAction(String name) {
        List<ActionPattern> actionPatterns = new ArrayList<>();

        for (ActionPattern actionsPattern : actionPatterns) {
            for (ActionEvent actionEvent : actionsPattern.getActionEvents()) {
                if (actionEvent.getNameOfElement().equals(name)) {
                    actionPatterns.add(actionsPattern);
                }
            }
        }

        return actionPatterns;
    }
}