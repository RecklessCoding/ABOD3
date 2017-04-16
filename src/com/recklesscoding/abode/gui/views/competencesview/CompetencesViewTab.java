package com.recklesscoding.abode.gui.views.competencesview;

import com.recklesscoding.abode.gui.layout.EditorViewTab;
import javafx.stage.Stage;

/**
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 * @see {@link EditorViewTab}
 * <p>
 */
public class CompetencesViewTab extends EditorViewTab {

    private static final String COMPTENCES_TAB = "Competences";

    public CompetencesViewTab(Stage primaryStage) {
        super(COMPTENCES_TAB, new CompetenceLayout(primaryStage));
    }

    @Override
    public void refresh(Stage primaryStage) {
        setContent(new CompetenceLayout(primaryStage));
    }
}