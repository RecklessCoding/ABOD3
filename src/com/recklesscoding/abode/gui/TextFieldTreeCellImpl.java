package com.recklesscoding.abode.gui;

import com.recklesscoding.abode.core.plan.planelements.PlanElement;
import com.recklesscoding.abode.core.plan.planelements.action.ActionPattern;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

/**
 * Created by Andreas on 26/12/2015.
 */
public class TextFieldTreeCellImpl extends TreeCell<PlanElement> {

    private TextField textField;

    private ContextMenu addMenu = new ContextMenu();

    public TextFieldTreeCellImpl(Window primaryStage) {
        initMenu(primaryStage);
    }

    private void initMenu(Window primaryStage) {
        MenuItem addActionElement = new MenuItem("Add action");
        addActionElement.setOnAction(t -> {
            TreeItem<PlanElement> newAction =
                    new TreeItem(new ActionPattern("Action!"));
            getTreeItem().getChildren().add(newAction);
        });


        MenuItem editElement = new MenuItem("Add action");
        editElement.setOnAction(t -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TextFieldTreeCellImpl.class.getResource("views\\ActionElementDialog.fxml"));
            try {
                DialogPane dialogPane = loader.load();

                Stage dialogStage = new Stage();
                dialogStage.setTitle("Edit Person");
                dialogStage.initModality(Modality.NONE);

                dialogStage.initOwner(primaryStage);
                Scene scene = new Scene(dialogPane);
                dialogStage.setScene(scene);

                // Show the dialog and wait until the user closes it
                dialogStage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        addMenu.getItems().add(editElement);
        addMenu.getItems().add(addActionElement);
    }

    @Override
    public void startEdit() {
        super.startEdit();

        if (textField == null) {
            createTextField();
        }

        setText(null);
        setGraphic(textField);
        textField.selectAll();
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText(getItem().getNameOfElement());
        setGraphic(getTreeItem().getGraphic());
    }

    @Override
    public void updateItem(PlanElement item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getItem().getNameOfElement());
                }
                setText(null);
                setGraphic(textField);
            } else {
                if (getItem() != null)
                {
                    setText(getItem().getNameOfElement());
                    setGraphic(getTreeItem().getGraphic());
                }
                if (!getTreeItem().isLeaf() && getTreeItem().getParent() != null) {
                    setContextMenu(addMenu);
                }
            }
        }
    }

    private int computeGreen(int value) {
        int clamped = Math.max(0, Math.min(value, 100));
        clamped = 255 - 255 * clamped / 200;
        return clamped;
    }


    private void createTextField() {
        // Create a new text field with the current text
        textField = new TextField(getItem().getNameOfElement());
        textField.setOnKeyReleased(t -> {
            // If user commits change
            if (t.getCode() == KeyCode.ENTER) {
                getItem().setNameOfElement(textField.getText());
                commitEdit(getItem());
                // Abort!
            } else if (t.getCode() == KeyCode.ESCAPE) {
                cancelEdit();
            }
        });
    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }
}
