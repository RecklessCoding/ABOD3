package com.recklesscoding.abode.gui.views.diagramview.diagram.graphviewer.layout;

import com.recklesscoding.abode.gui.views.diagramview.diagram.GraphWindow;
import com.recklesscoding.abode.core.plan.nodes.PlanElementNode;

import java.util.List;

/**
 * Created by Andreas on 28/12/2015.
 */
public class PlanHorizontalLayout extends PlanLayout {

    private GraphWindow graphWindow;

    public PlanHorizontalLayout(GraphWindow graphWindow) {
        this.graphWindow = graphWindow;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void execute() {
        List<PlanElementNode> drives = graphWindow.getNodesHolder().getDrives();

        if (drives.isEmpty()) {
            graphWindow.getNodesHolder().getDriveCollection().relocate(50, 50);
        } else {
            double x = 50;
            double y = 50;
            double maxXSubtree = 0;
            double maxXGraph = 0;
            for (PlanElementNode drive : drives) {
                for (PlanElementNode childFirstLayer : drive.getCellChildren()) {
                    maxXSubtree = maxXSubtree + (200 * countLeafs(0, childFirstLayer));
                }
                maxXGraph = maxXGraph + maxXSubtree;
                maxXSubtree = 0;
            }
            graphWindow.getNodesHolder().getDriveCollection().relocate(maxXGraph / 2, y);
            maxXSubtree = 0;
            y += 150;
            double xSubtree = x;
            for (PlanElementNode drive : drives) {
                for (PlanElementNode childFirstLayer : drive.getCellChildren()) {
                    maxXSubtree = maxXSubtree + (200 * countLeafs(0, childFirstLayer));
                }
                drive.relocate(x + maxXSubtree / 2, y);
                xSubtree = x;
                for (PlanElementNode childFirstLayer : drive.getCellChildren()) {
                    xSubtree = getSubtree(x, y + 150, childFirstLayer);
                }
                x += 150 + maxXSubtree;
                maxXSubtree = 0;
            }
        }
    }

    private double getSubtree(double x, double y, PlanElementNode parentPlanElementNode) {
        parentPlanElementNode.relocate(x, y);
        if (!parentPlanElementNode.getCellChildren().isEmpty()) {
            for (PlanElementNode childPlanElementNode : parentPlanElementNode.getCellChildren()) {
                x = getSubtree(x, y + 150, childPlanElementNode);
            }
        } else
            return x + 200;
        return x;
    }

    private int countLeafs(int count, PlanElementNode planElementNode) {
        if (!planElementNode.getCellChildren().isEmpty()) {
            for (PlanElementNode childPlanElementNode : planElementNode.getCellChildren()) {
                count = countLeafs(count, childPlanElementNode);
            }
        } else {
            return count + 1;
        }
        return count;
    }
}