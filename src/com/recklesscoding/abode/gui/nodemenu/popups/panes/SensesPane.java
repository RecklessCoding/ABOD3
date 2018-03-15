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
public class SensesPane extends VBoxWrapper {

    private ObservableList<Sense> senses = FXCollections.observableArrayList();

    public SensesPane() {
        init();
    }

    public SensesPane(List<Sense> senses) {
        this.senses.addAll(senses);

        init();
    }

    private void init() {
        TableViewPanel<Sense> tableView = new SensesTableView(senses);
        addItem(tableView);
    }

    public List<Sense> getSenses() {
        return senses;
    }
}
