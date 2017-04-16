package com.recklesscoding.abode.gui.views.competencesview;

import com.recklesscoding.abode.core.plan.planelements.competence.CompetenceElement;
import com.recklesscoding.abode.gui.layout.HasGoalsTabLayout;
import com.recklesscoding.abode.core.plan.planelements.competence.Competence;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;
import com.recklesscoding.abode.gui.trees.TreeItemWrapper;

/**
 * Creates the graphviewer of the items inside the competences view.
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class CompetenceLayout extends HasGoalsTabLayout {

    private final static Label NAME_LABEL = new Label("Competences:");

    private CompetenceTree competencesTree;

    public CompetenceLayout(Stage primaryStage) {
        super(primaryStage, NAME_LABEL);
    }

    @Override
    protected void addTree(Window primaryStage) {
        competencesTree = new CompetenceTree(primaryStage);
        this.getChildren().add(competencesTree);
    }

    @Override
    protected void addTreeViewListener() {
        competencesTree.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            if (newValue != null) {
                TreeItemWrapper treeItem = (TreeItemWrapper) newValue;
                if (treeItem.getValue() instanceof Competence) {
                    this.getSensesList().clear();
                    Competence competence = (Competence) treeItem.getValue();
                    this.getTextName().setText(competence.getNameOfElement());
                    this.getTextTime().setText(String.valueOf(competence.getTimeout()));
                    this.getSensesList().addAll(competence.getGoals());
                    this.getSensesLabel().setText("Goals: ");
                }
                if (treeItem.getValue() instanceof CompetenceElement) {
                    this.getSensesList().clear();
                    CompetenceElement competenceElement = (CompetenceElement) treeItem.getValue();
                    this.getSensesLabel().setText("Triggers: ");
                    this.getSensesList().addAll(competenceElement.getSenses());
                }
            }
        });
    }
}