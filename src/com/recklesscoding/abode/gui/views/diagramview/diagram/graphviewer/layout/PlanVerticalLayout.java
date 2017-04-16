package com.recklesscoding.abode.gui.views.diagramview.diagram.graphviewer.layout;

import com.recklesscoding.abode.gui.views.diagramview.diagram.GraphWindow;
import com.recklesscoding.abode.core.plan.nodes.PlanElementNode;

import java.util.List;

/**
 * Created by Andreas on 28/12/2015.
 */
public class PlanVerticalLayout extends PlanLayout {

    private GraphWindow graphWindow;

    public PlanVerticalLayout(GraphWindow graphWindow) {
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
            double maxYSubtree = 0;
            double maxYGraph = 0;
            for (PlanElementNode drive : drives) {
                for (PlanElementNode childFirstLayer : drive.getCellChildren()) {
                    maxYSubtree = maxYSubtree + (75 * countLeafs(0, childFirstLayer));
                }
                maxYGraph = maxYGraph + maxYSubtree;
                maxYSubtree = 0;
            }
            graphWindow.getNodesHolder().getDriveCollection().relocate(x, maxYGraph / 2);
            maxYSubtree = 0;
            x += 250;
            double ySubtree = y;
            for (PlanElementNode drive : drives) {
                for (PlanElementNode childFirstLayer : drive.getCellChildren()) {
                    maxYSubtree = maxYSubtree + (75 * countLeafs(0, childFirstLayer));
                }
                drive.relocate(x, y);
                ySubtree = y;
                for (PlanElementNode childFirstLayer : drive.getCellChildren()) {
                    ySubtree = getSubtree(x + 250, ySubtree, childFirstLayer);
                }
                y += 75 + maxYSubtree;
                maxYSubtree = 0;
            }
        }
    }

    private double getSubtree(double x, double y, PlanElementNode parentPlanElementNode) {
        parentPlanElementNode.relocate(x, y);
        if (!parentPlanElementNode.getCellChildren().isEmpty()) {
            for (PlanElementNode childPlanElementNode : parentPlanElementNode.getCellChildren()) {
                y = getSubtree(x + 250, y, childPlanElementNode);
            }
        } else
            return y + 75;
        return y;
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