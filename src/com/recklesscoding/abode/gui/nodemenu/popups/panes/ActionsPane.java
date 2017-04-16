package com.recklesscoding.abode.gui.nodemenu.popups.panes;

import com.recklesscoding.abode.core.plan.Plan;
import com.recklesscoding.abode.core.plan.planelements.action.ActionEvent;
import com.recklesscoding.abode.util.panes.MultipleListsView;
import com.recklesscoding.abode.util.wrappers.VBoxWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class ActionsPane extends VBoxWrapper {

    private static final String AVAILABLE_ACTIONS = "Available Actions:";

    private static final String SELECTED_ACTIONS = "Selected Actions:";

    private ObservableList<ActionEvent> selectedActions = FXCollections.observableArrayList();

    public ActionsPane(List<ActionEvent> actions) {
        if (actions != null) {
            selectedActions.setAll(actions);
        }
        init();
    }

    private void init() {
        ObservableList allAvailableActions = FXCollections.observableArrayList();
        allAvailableActions.addAll(Plan.getInstance().getActionEvents());
        MultipleListsView multipleListView = new MultipleListsView(AVAILABLE_ACTIONS, SELECTED_ACTIONS,
                allAvailableActions, selectedActions);
        addItem(multipleListView);
    }

    public List<ActionEvent> getSelectedActions() {
        return selectedActions;
    }
}
