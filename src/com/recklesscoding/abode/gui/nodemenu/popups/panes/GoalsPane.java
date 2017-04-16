package com.recklesscoding.abode.gui.nodemenu.popups.panes;

import com.recklesscoding.abode.core.plan.planelements.Sense;
import com.recklesscoding.abode.util.panes.SensesTableView;
import com.recklesscoding.abode.util.panes.TableViewPanel;
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
public class GoalsPane extends VBoxWrapper {



    private ObservableList<Sense> goals = FXCollections.observableArrayList();

    public GoalsPane() {
        init();
    }

    public GoalsPane(List<Sense> goals) {
        goals.addAll(goals);
        init();
    }

    private void init() {
        TableViewPanel<Sense> tableView = new SensesTableView();

        addItem(tableView);
    }

    public List<Sense> getGoals() {
        return goals;
    }
}
