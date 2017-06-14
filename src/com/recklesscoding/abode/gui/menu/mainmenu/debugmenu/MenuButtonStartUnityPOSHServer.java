package com.recklesscoding.abode.gui.menu.mainmenu.debugmenu;

import com.recklesscoding.abode.core.Editor;
import com.recklesscoding.abode.gui.console.UnityPOSHServerGUI;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;

public class MenuButtonStartUnityPOSHServer extends MenuItem {

    private static final String NAME_LABEL_START = "Start U-POSH Server";

    private static final String NAME_LABEL_RESTART = "Restart U-POSH Server";

    private UnityPOSHServerGUI unityPOSHServerGUI;

    public MenuButtonStartUnityPOSHServer(Editor editor) {
        super(NAME_LABEL_START);
        initButton(editor);
    }

    private void initButton(Editor editor) {
        setMnemonicParsing(true);
        setDisable(false);
        initAction(editor);
    }

    private void initAction(Editor editor) {
        setOnAction((ActionEvent actionEvent) -> {
            Platform.runLater(() -> {
                if (unityPOSHServerGUI == null) {
                    unityPOSHServerGUI = new UnityPOSHServerGUI();
                    unityPOSHServerGUI.show();
                } else {
                    unityPOSHServerGUI.show();
                }
                editor.getUnityPOSHDebugger().startServer();
            });

            for (MenuItem menuItem : this.getParentMenu().getItems()) {
                if (menuItem instanceof MenuButtonStartUnityPOSHServer) {
                    setText(NAME_LABEL_RESTART);
                }
                if (menuItem instanceof MenuButtonStartDebug) {
                    menuItem.setDisable(false);
                }
            }
        });
    }
}