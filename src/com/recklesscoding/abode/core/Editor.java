package com.recklesscoding.abode.core;

import com.recklesscoding.abode.core.plan.reader.xposh.XPOSHPlanReader;
import com.recklesscoding.abode.core.plan.writer.XMLPOSHPlanWriter;
import com.recklesscoding.abode.debugger.InsinctDebugger;
import com.recklesscoding.abode.debugger.IDebugger;
import com.recklesscoding.abode.debugger.UnityPOSHDebugger;
import com.recklesscoding.abode.gui.EditorLayout;
import com.recklesscoding.abode.core.plan.Plan;
import com.recklesscoding.abode.core.plan.reader.PlanReader;
import com.recklesscoding.abode.core.plan.reader.inst.InstPlanReader;
import com.recklesscoding.abode.core.plan.reader.lap.LapPlanReader;
import com.recklesscoding.abode.core.plan.writer.LapPlanWriter;
import javafx.stage.Stage;

/**
 * <p>
 * ----------------------------------------------------
 * ----_    ____   ___  ____  ______ -----Advanced-----
 * ---/ \  | __ ) / _ \|  _ \|_ __ | -----Behavior-----
 * --/ _ \ |  _ \| | | | | |  ___| | -----Oriented-----
 * -/ ___ \| |_) | |_| | |_|  ___| | ------Design------
 * /_/   \_\____/ \___/|____/|_____| ----Environment---
 * ----------------------------------------------------
 * </p>
 * <p>
 * AUTHOR:            Andreas Theodorou
 * VERSION:           @version
 * LICENSING MODEL:   TBA
 * COPYRIGHT:         TBA
 * </p>
 * <p> Contains an instance of the views.</p>
 */
public class Editor {

    private static Editor instance;

    private EditorLayout editorLayout;

    private PlanReader planReader;

    private IDebugger insinctDebugger = new InsinctDebugger();
    private IDebugger unityPOSHDebugger = new UnityPOSHDebugger();

    /**
     * @param primaryWindow The primary window of the application, where the views will reside.
     */
    public Editor(Stage primaryWindow) {
        init(primaryWindow);
        instance = this;
    }

    private void init(Stage primaryWindow) {
        editorLayout = new EditorLayout(primaryWindow, this);
    }

    public void readPlanFile(String file) {
        if (file.endsWith(".xml")) {
            planReader = new XPOSHPlanReader();
        }  else if (file.endsWith(".inst")) {
            planReader = new InstPlanReader();
        } else if (file.endsWith(".lap")) {
            planReader = new LapPlanReader();
        }
        if (planReader != null) {
            Plan.getInstance().cleanAllLists();
            planReader.readFile(file);
        }
        refreshLayout();
    }

    public void savePlan(String file) {
        new XMLPOSHPlanWriter().writeFile(file);
        refreshLayout();
    }

    public void refreshLayout() {
        editorLayout.refresh();
    }

    /**
     * @return The IDE viewer component in the graphviewer it was initialised.
     */
    public EditorLayout getEditorLayout() {
        return editorLayout;
    }

    public IDebugger getUnityPOSHDebugger(){
        return unityPOSHDebugger;
    }

    public IDebugger getIncinctDebugger(){
        return insinctDebugger;
    }

    public static Editor getInstance() {
        return instance;
    }
}
