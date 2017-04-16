package com.recklesscoding.abode.core.plan.nodes;

import com.recklesscoding.abode.core.plan.planelements.PlanElement;
import javafx.scene.Node;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Each node represents an individual {@link PlanElement} by containing a reference to it. The node extends {@link Pane}
 * that can be placed and dragged around the diagram tree canvas. Nodes hold a reference of their "glow" level, which changes
 * based on how many times it was called in debugger.
 * </p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class PlanElementNode extends Pane {

    private final PlanElement planElement;

    private List<PlanElementNode> children = new ArrayList<>();

    private PlanElementNode parent;

    private Rectangle rectangle = new Rectangle(175, 50);

    private Text text;

    private Glow glow = new Glow(0);

    private boolean isSubtreeCollapsed;

    public PlanElementNode(PlanElement planElement, Color color) {
        if (planElement != null) {
            this.planElement = planElement;
            this.text = new Text(planElement.getNameOfElement());
        } else {
            this.planElement = new PlanElement("Error");
            this.text = new Text("Element error");
        }
        initView(color);
    }

    public synchronized void updateCell(boolean decrease) {
        boolean isUsed = planElement.getIsUsed();
        if (isUsed) {
            setGlow(isUsed);
        }
        if (decrease) {
            setGlow(!decrease);
        }
        planElement.setFinishUpdate();
    }

    public PlanElementNode getNodeParent() {
        return parent;
    }

    private void setView(Node view) {
        getChildren().add(view);
    }

    public void addCellChild(PlanElementNode planElementNode) {
        children.add(planElementNode);
    }

    public List<PlanElementNode> getCellChildren() {
        return children;
    }

    public void addCellParent(PlanElementNode planElementNode) {
        parent = planElementNode;
    }

    public void removeChild(PlanElementNode planElementNode) {
        children.remove(planElementNode);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Text getText() {
        return text;
    }

    public PlanElement getPlanElement() {
        return planElement;
    }

    public boolean isSubtreeCollapsed() {
        return isSubtreeCollapsed;
    }

    public void setSubtreeCollapsed(boolean subtreeCollapsed) {
        isSubtreeCollapsed = subtreeCollapsed;
    }

    private void initView(Color color) {
        initRectangle(color);
        initText(color);

        StackPane stack = new StackPane();
        stack.getChildren().addAll(getRectangle(), text);
        setView(stack);
    }

    private void initRectangle(Color color) {
        rectangle.setFill(Color.DARKSLATEGRAY);
        rectangle.setStroke(color);
        rectangle.setStrokeType(StrokeType.OUTSIDE);
//        rectangle.setStrokeMiterLimit(5.0);
    }

    private void initText(Color color) {
        text.setStyle("-fx-font-size: 16px;");
        text.setFill(color);
        text.setFill(Color.WHITE);
    }

    private void setGlow(boolean increase) {
        if (increase) {
            glow.setLevel(glow.getLevel() + 0.5);
        } else {
            glow.setLevel(glow.getLevel() - 0.1);
        }
        if (glow.getLevel() > 3)
            glow.setLevel(3);
        else if (glow.getLevel() < 0)
            glow.setLevel(0);
        else
            rectangle.setEffect(glow);
    }
}