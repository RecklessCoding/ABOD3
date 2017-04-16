package com.recklesscoding.abode.gui.views.drivescollectionsview;

import com.recklesscoding.abode.core.plan.planelements.Sense;
import com.recklesscoding.abode.core.plan.planelements.drives.DriveCollection;
import com.recklesscoding.abode.gui.layout.HasGoalsTabLayout;
import com.recklesscoding.abode.gui.trees.TreeItemWrapper;
import com.recklesscoding.abode.util.wrappers.BooleanComboBox;
import com.recklesscoding.abode.util.wrappers.VBoxWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * Creates the graphviewer of the items inside the drives collection view.
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class DrivesCollectionLayout extends HasGoalsTabLayout {

    private final static Label NAME_LABEL = new Label("Drives Collections:");

    private DrivesCollectionsTree drivesCollectionsTree;

    private BooleanComboBox comboBoxRealTime;

    private TextField textDrivesCollectionName;

    private ObservableList<Sense> goalsList = FXCollections.observableArrayList();

    public DrivesCollectionLayout(Stage primaryStage) {
        super(primaryStage, NAME_LABEL);
    }

    @Override
    protected void addTree(Window primaryStage) {
        drivesCollectionsTree = new DrivesCollectionsTree(primaryStage);
        this.getChildren().add(drivesCollectionsTree);
    }

    @Override
    protected void addPropertiesComponent() {
        textDrivesCollectionName = new TextField();
        comboBoxRealTime = new BooleanComboBox();

        VBoxWrapper vbox = new VBoxWrapper();
        vbox.setScaleShape(true);
        Label labelName = new Label("Name:");
        vbox.addItem(labelName);
        vbox.addItem(textDrivesCollectionName);
        Label labelTime = new Label("Real time:");
        vbox.addItem(labelTime);
        vbox.addItem(comboBoxRealTime);

        this.getChildren().add(vbox);
    }

    @Override
    protected void addTreeViewListener() {
        drivesCollectionsTree.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            if (newValue != null) {
                TreeItemWrapper treeItem = (TreeItemWrapper) newValue;
                if (treeItem.getValue() instanceof DriveCollection) {
                    this.goalsList.clear();
                    DriveCollection driveCollection = (DriveCollection) treeItem.getValue();
                    this.textDrivesCollectionName.setText(driveCollection.getNameOfElement());
                    this.comboBoxRealTime.setValue(driveCollection.isRealTime());
                    this.goalsList.addAll(driveCollection.getGoals());
                }
            }
        });
    }
}