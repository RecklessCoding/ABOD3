package com.recklesscoding.abode.gui.layout;

import com.recklesscoding.abode.util.wrappers.FlowPaneWrapper;
import com.recklesscoding.abode.util.wrappers.VBoxWrapper;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * Created by Andreas on 19/01/2016.
 */
public abstract class TabLayout extends FlowPaneWrapper {

    private TextField textName;

    private TextField textTime;

    public TabLayout(Stage primaryStage, Label NAME_LABEL) {
        NAME_LABEL.setPrefWidth(120);
        NAME_LABEL.setWrapText(true);
        addItem(NAME_LABEL);
        initTree(primaryStage);
        addPropertiesComponent();
        setScaleShape(true);
        setVgap(10);
        setHgap(25);
        setPadding(new Insets(5, 5, 5, 5));
    }

    protected abstract void addTree(Window primaryStage);

    protected abstract void addTreeViewListener();

    private void initTree(Window primaryStage) {
        addTree(primaryStage);
        addTreeViewListener();
    }

    protected void addPropertiesComponent() {
        VBoxWrapper vbox = new VBoxWrapper();
        vbox.setScaleShape(true);
        Label labelName = new Label("Name:");
        vbox.addItem(labelName);
        textName = new TextField();
        vbox.addItem(textName);
        Label labelTime = new Label("Time:");
        vbox.addItem(labelTime);
        textTime = new TextField();
        vbox.addItem(textTime);
        this.getChildren().add(vbox);
    }

    protected TextField getTextName() {
        return textName;
    }

    protected TextField getTextTime() {
        return textTime;
    }
}
