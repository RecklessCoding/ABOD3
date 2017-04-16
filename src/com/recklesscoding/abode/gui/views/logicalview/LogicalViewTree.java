package com.recklesscoding.abode.gui.views.logicalview;

import com.recklesscoding.abode.core.plan.Plan;
import com.recklesscoding.abode.core.plan.planelements.PlanElement;
import com.recklesscoding.abode.core.plan.planelements.Sense;
import com.recklesscoding.abode.core.plan.planelements.action.ActionEvent;
import com.recklesscoding.abode.core.plan.planelements.action.ActionPattern;
import com.recklesscoding.abode.core.plan.planelements.competence.Competence;
import com.recklesscoding.abode.core.plan.planelements.competence.CompetenceElement;
import com.recklesscoding.abode.core.plan.planelements.drives.DriveCollection;
import com.recklesscoding.abode.core.plan.planelements.drives.DriveElement;
import com.recklesscoding.abode.gui.trees.PlanTree;
import com.recklesscoding.abode.gui.trees.TreeItemWrapper;
import javafx.stage.Window;

import java.util.List;

/**
 * Creates a tree of {@link javafx.scene.control.TreeItem} containing all the elements.
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class LogicalViewTree extends PlanTree {

    public LogicalViewTree(Window primaryStage) {
        super(primaryStage);

        init();
    }

    private void init() {
        this.setEditable(true);
        this.setShowRoot(false);
    }

    @Override
    protected void createTree() {
        createLogicalViewTree();
    }

    private void createLogicalViewTree() {
        TreeItemWrapper<PlanElement> rootItem = new TreeItemWrapper<>(new PlanElement("Drive collections"));

        List<DriveCollection> driveCollections = Plan.getInstance().getDriveCollections();

        TreeItemWrapper<PlanElement> driveItem;
        for (DriveCollection driveCollection : driveCollections) {
            driveItem = new TreeItemWrapper<>(driveCollection);

            PlanElement triggeredElement = driveCollection.getTriggeredElement();
            if (triggeredElement != null) {
                TreeItemWrapper<PlanElement> triggeredElementItem = getSubtree(triggeredElement);
                driveItem.addChild((triggeredElementItem));
            }

            for (DriveElement driveElement : driveCollection.getDriveElements()) {
                TreeItemWrapper<PlanElement> driveElementItem = new TreeItemWrapper<>((driveElement));
                driveElementItem.getChildren().add(new TreeItemWrapper<>(driveElement.getTriggeredElement()));

                TreeItemWrapper<PlanElement> triggerElementsItem = new TreeItemWrapper<>((new Sense("Trigger Elements", null, null)));
                for (Sense sense : driveElement.getDriveElementSenses()) {
                    triggerElementsItem.addChild(new TreeItemWrapper<>(sense));
                }
                driveElementItem.addChild(triggerElementsItem);

                driveItem.addChild((driveElementItem));
            }


            rootItem.addChild(driveItem);
        }
        super.setRoot(rootItem);
    }
//
//    private TreeItemWrapper<PlanElement> getTriggeredElementTree(PlanElement triggeredElement) {
//        TreeItemWrapper<PlanElement> triggeredElementItem = new TreeItemWrapper<>(triggeredElement);
//        triggeredElementItem.addChild(getSubtree(triggeredElementItem, triggeredElement));
//
//        return triggeredElementItem;
//    }


    private TreeItemWrapper<PlanElement> getSubtree(PlanElement planElement) {
        TreeItemWrapper<PlanElement> subTree = new TreeItemWrapper<>(planElement);

        if (planElement instanceof Competence) {
            for (CompetenceElement competenceElement : ((Competence) planElement).getCompetenceElements()) {
                TreeItemWrapper<PlanElement> competenceElementTree = new TreeItemWrapper<>(competenceElement);
                PlanElement triggeredElement = competenceElement.getTriggeredElement();
                if (triggeredElement != null) {
                    competenceElementTree.addChild(getSubtree(triggeredElement));
                }

                subTree.addChild(competenceElementTree);
            }
        }

        if (planElement instanceof ActionPattern) {
            for (ActionEvent actionEvent : ((ActionPattern) planElement).getActionEvents()) {
                subTree.addChild(new TreeItemWrapper(actionEvent));
            }
        }

        return subTree;
    }
}