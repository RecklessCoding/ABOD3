package com.recklesscoding.abode.gui.layout;

import com.recklesscoding.abode.gui.views.actionpatternsview.ActionPatternsViewTab;
import com.recklesscoding.abode.gui.views.competencesview.CompetencesViewTab;
import com.recklesscoding.abode.gui.views.diagramview.DiagramTab;
import com.recklesscoding.abode.gui.views.drivescollectionsview.DrivesCollectionsViewTab;
import com.recklesscoding.abode.util.wrappers.TabPaneWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class TabComponent extends TabPaneWrapper {

    private List<EditorViewTab> tabsList = new LinkedList<>();

    private DiagramTab diagramTab;
    private Stage primaryStage;

    public TabComponent(Stage primaryStage) {
        this.primaryStage = primaryStage;
        diagramTab = new DiagramTab(primaryStage);
        init();

        getSelectionModel().selectedIndexProperty().addListener((ov, oldValue, newValue) ->
                getTab((Integer) newValue).refresh(primaryStage));
    }

    private void init() {
        addTab(diagramTab);
    }

    private void addTab(EditorViewTab tab) {
        tabsList.add(tab);
        super.addTab(tab);
    }

    public DiagramTab getDiagramTab() {
        return diagramTab;
    }
}