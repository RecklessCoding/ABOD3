package com.recklesscoding.abode.gui.views.drivescollectionsview;

import com.recklesscoding.abode.core.plan.planelements.PlanElement;
import com.recklesscoding.abode.core.plan.planelements.Sense;
import com.recklesscoding.abode.core.plan.planelements.drives.DriveElement;
import javafx.scene.control.TreeItem;
import javafx.stage.Window;
import com.recklesscoding.abode.gui.trees.PlanTree;
import com.recklesscoding.abode.core.plan.Plan;
import com.recklesscoding.abode.core.plan.planelements.drives.DriveCollection;
import com.recklesscoding.abode.gui.trees.TreeItemWrapper;

import java.util.List;

/**
 * Creates a tree of {@link javafx.scene.control.TreeItem} containing the drives collections;
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class DrivesCollectionsTree extends PlanTree {

    public DrivesCollectionsTree(Window primaryStage) {
        super(primaryStage);

        init();
    }

    private void init() {
        this.setEditable(true);
        this.setShowRoot(false);
    }

    @Override
    protected void createTree() {
        createDrivesCollectionsTree();
    }

    private void createDrivesCollectionsTree() {
        TreeItemWrapper<PlanElement> rootItem = new TreeItemWrapper<>(new PlanElement(null));

        List<DriveCollection> driveCollections = Plan.getInstance().getDriveCollections();

        TreeItem<PlanElement> item;
        for (DriveCollection driveCollection : driveCollections) {
            item = new TreeItemWrapper<>(driveCollection);
//            TreeItemWrapper<PlanElement> goalItem = new TreeItemWrapper<>((new Sense("Goal", null, null)));
//            for (Sense goal : driveCollection.getSenses()) {
//                goalItem.getChildren().add(new TreeItemWrapper<>(goal));
//            }
//            item.getChildren().add(goalItem);

            for (DriveElement driveElement : driveCollection.getDriveElements()) {
                TreeItemWrapper<PlanElement> driveElementItem = new TreeItemWrapper<>((driveElement));
                for (Sense sense : driveElement.getSenses()) {
                    driveElementItem.getChildren().add(new TreeItemWrapper<>(sense));
                }

                item.getChildren().add(driveElementItem);
            }
            rootItem.getChildren().add(item);
        }

        super.setRoot(rootItem);
    }
}