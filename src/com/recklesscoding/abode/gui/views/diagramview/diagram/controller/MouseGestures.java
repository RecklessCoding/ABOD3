package com.recklesscoding.abode.gui.views.diagramview.diagram.controller;

import com.recklesscoding.abode.core.plan.nodes.PlanElementNode;
import com.recklesscoding.abode.core.plan.nodes.plannodes.*;
import com.recklesscoding.abode.gui.nodemenu.NodeMenu;
import com.recklesscoding.abode.gui.nodemenu.popups.editelement.*;
import com.recklesscoding.abode.gui.views.diagramview.diagram.GraphWindow;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * <p>
 * Controller that contains mouse gestures for the diagram representations of the graphs.
 * </p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class MouseGestures {

    private final DragContext dragContext = new DragContext();

    private GraphWindow graphWindow;

    private Stage stage;
    private EventHandler<MouseEvent> onMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            Node node = (Node) event.getSource();

            double offsetX = event.getScreenX() + dragContext.x;
            double offsetY = event.getScreenY() + dragContext.y;

            // adjust the offset in case we are zoomed
            double scale = graphWindow.getScale();

            offsetX /= scale;
            offsetY /= scale;

            node.relocate(offsetX, offsetY);
        }
    };

    public MouseGestures(Stage stage, GraphWindow graphWindow) {
        this.stage = stage;
        this.graphWindow = graphWindow;
    }

    public void makeDraggable(final PlanElementNode planElementNode) {

    //        planElementNode.setOnMouseClicked(mouseEvent ->
    //        {
    //            if (hasLeftButtonClicked(mouseEvent) && mouseEvent.isStillSincePress()) {
    //                if (planElementNode instanceof ActionNode) {
    //                    Platform.runLater(() -> new EditAction(planElementNode, graphWindow));
    //                } else if (planElementNode instanceof ActionPatternNode) {
    //                    Platform.runLater(() -> new EditActionPattern(planElementNode, graphWindow));
    //                } else if (planElementNode instanceof CompetenceNode) {
    //                    Platform.runLater(() -> new EditCompetence(planElementNode, graphWindow));
    //                } else if (planElementNode instanceof CompetenceElementNode) {
    //                    Platform.runLater(() -> new EditCompetenceElement(planElementNode, graphWindow));
    //                } else if (planElementNode instanceof DriveElementNode) {
    //                      Platform.runLater(() -> new EditDriveElement(planElementNode, graphWindow));
    //                } else if (planElementNode instanceof DriveCollectionNode) {
    //                    Platform.runLater(() -> new EditDriveCollection(planElementNode, graphWindow));
    //                }
    //            }
    //        });

        planElementNode.setOnMousePressed(mouseEvent -> {
            PlanElementNode clickedNode = (PlanElementNode) mouseEvent.getSource();
            if (hasLeftButtomDoubleClicked(mouseEvent)) {
                hideUnhideSubtree(clickedNode);
            }

            if (hasRightButtonClicked(mouseEvent)) {
          //      new NodeMenu(stage, graphWindow, clickedNode, mouseEvent);
            }

            double scale = graphWindow.getScale();
            dragContext.x = clickedNode.getBoundsInParent().getMinX() * scale - mouseEvent.getScreenX();
            dragContext.y = clickedNode.getBoundsInParent().getMinY() * scale - mouseEvent.getScreenY();
        });
        planElementNode.setOnMouseDragged(onMouseDraggedEventHandler);
    }

    private boolean hasLeftButtonClicked(MouseEvent mouseEvent) {
        return mouseEvent.getButton().equals(MouseButton.PRIMARY);
    }

    private boolean hasRightButtonClicked(MouseEvent mouseEvent) {
        return mouseEvent.getButton().equals(MouseButton.SECONDARY);
    }

    private boolean hasLeftButtomDoubleClicked(MouseEvent mouseEvent) {
        return mouseEvent.getButton().equals(MouseButton.PRIMARY) && mouseEvent.getClickCount() == 2;
    }

    private void hideUnhideSubtree(PlanElementNode node) {
        if (node.isSubtreeCollapsed()) {
            hideSubtree(node);
        } else {
            unhideSubtree(node);
        }

    }

    private void hideSubtree(PlanElementNode node) {
        setNodeConnectorsVisibility(node, true);
        for (PlanElementNode planElementNode : node.getCellChildren()) {
            getSubtree(planElementNode, true);
            node.setSubtreeCollapsed(false);
        }
    }

    private void unhideSubtree(PlanElementNode node) {
        setNodeConnectorsVisibility(node, false);
        for (PlanElementNode planElementNode : node.getCellChildren()) {
            getSubtree(planElementNode, false);
            node.setSubtreeCollapsed(true);
            graphWindow.getScrollPane().autosize();
        }
    }

    private void setNodeConnectorsVisibility(PlanElementNode node, boolean visibility) {
        graphWindow.getNodesHolder().getAllNodesConnectors().stream().filter(edge -> edge.getSource().equals(node)).forEach(edge -> edge.setVisible(visibility));
    }

    private void getSubtree(PlanElementNode parentPlanElementNode, boolean visibility) {
        parentPlanElementNode.setVisible(visibility);
        setNodeConnectorsVisibility(parentPlanElementNode, visibility);
        if (!parentPlanElementNode.getCellChildren().isEmpty()) {
            for (PlanElementNode childPlanElementNode : parentPlanElementNode.getCellChildren()) {
                getSubtree(childPlanElementNode, visibility);
            }
        } else {
            return;
        }
    }

    class DragContext {
        double x;
        double y;
    }
}
