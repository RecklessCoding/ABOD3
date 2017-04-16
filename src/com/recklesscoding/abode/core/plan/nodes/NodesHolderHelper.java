package com.recklesscoding.abode.core.plan.nodes;

import com.recklesscoding.abode.core.plan.Plan;
import com.recklesscoding.abode.core.plan.nodes.plannodes.*;
import com.recklesscoding.abode.core.plan.planelements.PlanElement;
import com.recklesscoding.abode.core.plan.planelements.action.ActionEvent;
import com.recklesscoding.abode.core.plan.planelements.action.ActionPattern;
import com.recklesscoding.abode.core.plan.planelements.competence.Competence;
import com.recklesscoding.abode.core.plan.planelements.competence.CompetenceElement;
import com.recklesscoding.abode.core.plan.planelements.drives.DriveCollection;
import com.recklesscoding.abode.core.plan.planelements.drives.DriveElement;

/**
 * <p>
 * </p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class NodesHolderHelper {

    private NodesHolder nodesHolder;

    public NodesHolderHelper(NodesHolder nodesHolder) {
        this.nodesHolder = nodesHolder;
    }

    public void populateNodesHolder() {
        addNode(nodesHolder.getDriveCollection());
        addGraphComponents();
    }

    private void addGraphComponents() {
        for (DriveCollection driveCollection : Plan.getInstance().getDriveCollections()) {
            createNewNode(driveCollection);
            PlanElement triggeredElement = driveCollection.getTriggeredElement();
            for (DriveElement driveElement : driveCollection.getDriveElements()) {
                subtreeDriveElement(driveElement, driveCollection);
            }
            if (triggeredElement != null) {
                getSubtree(triggeredElement, driveCollection);
            }
        }
    }

    private void subtreeDriveElement(DriveElement driveElement, DriveCollection driveCollection) {
        addNewCell(driveElement, driveCollection);
        PlanElement triggeredElement = driveElement.getTriggeredElement();
        if (triggeredElement != null) {
            getSubtree(triggeredElement, driveElement);
        }
    }

    private void getSubtree(PlanElement elementToBeAdded, PlanElement parentElement) {
        if (elementToBeAdded instanceof Competence) {
            subtreeCompetence(elementToBeAdded, parentElement);
        }
        if (elementToBeAdded instanceof ActionPattern) {
            subtreeActionPattern((ActionPattern) elementToBeAdded, parentElement);
        }
        if (elementToBeAdded instanceof ActionEvent) {
            subtreeAction((ActionEvent) elementToBeAdded, parentElement);
        }
    }

    private void subtreeAction(ActionEvent action, PlanElement parentElement) {
        addNewCell(action, parentElement);
    }

    private void subtreeActionPattern(ActionPattern actionPattern, PlanElement parentElement) {
        addNewCell(actionPattern, parentElement);
        for (ActionEvent actionEvent : actionPattern.getActionEvents())
            subtreeAction(actionEvent, actionPattern);
    }

    private void subtreeCompetence(PlanElement competence, PlanElement parentElement) {
        addNewCell(competence, parentElement);
        for (CompetenceElement competenceElement : ((Competence) competence).getCompetenceElements())
            subtreeCompetenceElement(competenceElement, competence);
    }

    private void subtreeCompetenceElement(CompetenceElement competenceElement, PlanElement parentElement) {
        addNewCell(competenceElement, parentElement);
        PlanElement triggeredElement = competenceElement.getTriggeredElement();
        if (triggeredElement != null)
            getSubtree(triggeredElement, competenceElement);
    }

    private void addNewCell(PlanElement elementToBeAdded, PlanElement parentElement) {
        createNewNode(elementToBeAdded);
        saveNodesConnector(elementToBeAdded, parentElement);
    }

    private void saveNodesConnector(PlanElement childElement, PlanElement parentElement) {
        addNodesConnector(childElement, parentElement);
    }

    public void addNodesConnector(PlanElement target, PlanElement source) {
        PlanElementNode targetPlanElementNode = nodesHolder.getNodesMap().get(target);
        PlanElementNode sourcePlanElementNode = nodesHolder.getNodesMap().get(source);

        nodesHolder.getAddedEdges().add(new NodesConnector(sourcePlanElementNode, targetPlanElementNode));
    }

    public void createNewNode(PlanElement planElement) {
        PlanElementNode newPlanElementNode = null;
        if (planElement instanceof ActionEvent)
            newPlanElementNode = new ActionNode(planElement);
        else if (planElement instanceof ActionPattern)
            newPlanElementNode = new ActionPatternNode(planElement);
        else if (planElement instanceof Competence)
            newPlanElementNode = new CompetenceNode(planElement);
        else if (planElement instanceof CompetenceElement)
            newPlanElementNode = new CompetenceElementNode(planElement);
        else if (planElement instanceof DriveElement) {
            newPlanElementNode = new DriveElementNode(planElement);
        } else if (planElement instanceof DriveCollection) {
            newPlanElementNode = new DriveCollectionNode(planElement);
            nodesHolder.getDrives().add(newPlanElementNode);
        }
        if (newPlanElementNode != null) {
            addNode(newPlanElementNode);
            if (planElement instanceof DriveCollection) {
                addNodesConnector(planElement, nodesHolder.getDriveCollection().getPlanElement());
            }
        } else {
            throw new UnsupportedOperationException("Unsupported type");
        }
    }

    private void addNode(PlanElementNode planElementNode) {
        nodesHolder.getAddedPlanElementNodes().add(planElementNode);
        nodesHolder.getNodesMap().put(planElementNode.getPlanElement(), planElementNode);
    }
}
