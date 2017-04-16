package com.recklesscoding.abode.gui.views.competencesview;

import com.recklesscoding.abode.core.plan.Plan;
import com.recklesscoding.abode.core.plan.planelements.PlanElement;
import com.recklesscoding.abode.core.plan.planelements.competence.Competence;
import com.recklesscoding.abode.core.plan.planelements.competence.CompetenceElement;
import javafx.scene.control.TreeItem;
import javafx.stage.Window;
import com.recklesscoding.abode.gui.trees.PlanTree;
import com.recklesscoding.abode.gui.trees.TreeItemWrapper;

import java.util.List;

/**
 * Creates a tree of {@link javafx.scene.control.TreeItem} containing the competences;
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class CompetenceTree extends PlanTree {

    public CompetenceTree(Window primaryStage) {
        super(primaryStage);
    }

    @Override
    protected void createTree() {
        createCompetenceTree();
    }

    private void createCompetenceTree() {
        TreeItemWrapper<PlanElement> rootItem = new TreeItemWrapper<>(new PlanElement(""));

        List<Competence> competences = Plan.getInstance().getCompetences();

        TreeItem<PlanElement> item;
        // O(N^2) to populate the tree
        for (Competence competence : competences) {
            item = new TreeItemWrapper<>(competence);
//            TreeItemWrapper<PlanElement> goalItem = new TreeItemWrapper<>((new Sense("Goal", null, null)));
//            for (Sense competenceGoal : competence.getGoals()) {
//                goalItem.getChildren().add(new TreeItemWrapper<>(competenceGoal));
//            }
//            item.getChildren().add(goalItem);

            for (CompetenceElement competenceElement : competence.getCompetenceElements()) {
                TreeItemWrapper<PlanElement> competenceElementItem = new TreeItemWrapper<>((competenceElement));
//                for (Sense sense : competenceElement.getSenses()) {
//                    competenceElementItem.getChildren().add(new TreeItemWrapper<>(sense));
//                }
                competenceElementItem.getChildren().add(new TreeItemWrapper<>(competenceElement.getTriggeredElement()));
                item.getChildren().add(competenceElementItem);
            }

            rootItem.getChildren().add(item);
        }

        super.setRoot(rootItem);
    }
}