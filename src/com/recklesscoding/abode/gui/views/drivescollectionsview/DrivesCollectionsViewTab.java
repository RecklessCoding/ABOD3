package com.recklesscoding.abode.gui.views.drivescollectionsview;

import javafx.stage.Stage;
import javafx.stage.Window;
import com.recklesscoding.abode.gui.layout.EditorViewTab;
import com.recklesscoding.abode.gui.views.competencesview.CompetenceLayout;

/**
 * @see {@link EditorViewTab}
 * <p>
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class DrivesCollectionsViewTab extends EditorViewTab {

    private static final String DRIVES_TAB = "Drives Collections";

    public DrivesCollectionsViewTab(Stage primaryStage) {
        super(DRIVES_TAB, new DrivesCollectionLayout(primaryStage));
    }

    @Override
    public void refresh(Stage primaryStage) {
        setContent(new DrivesCollectionLayout(primaryStage));
    }
}