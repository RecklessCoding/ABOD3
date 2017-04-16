package com.recklesscoding.abode.gui;

import com.recklesscoding.abode.core.Editor;
import com.recklesscoding.abode.gui.layout.TabComponent;
import com.recklesscoding.abode.gui.menu.mainmenu.MainMenuBar;
import com.recklesscoding.abode.gui.menu.wrappers.MenuBarWrapper;
import com.recklesscoding.abode.gui.views.diagramview.DiagramTab;
import com.recklesscoding.abode.gui.views.diagramview.diagram.saving.GraphLayoutReader;
import com.recklesscoding.abode.gui.views.diagramview.diagram.saving.GraphLayoutWriter;
import com.recklesscoding.abode.gui.views.editorlayout.IEditorLayout;
import com.recklesscoding.abode.util.wrappers.VBoxWrapper;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;


/**
 * A version of the {@link IEditorLayout}, where the plannodes are automatically placed one ontop of the other..
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class EditorLayout extends VBoxWrapper implements IEditorLayout {

    private Stage primaryWindow;

    private TabComponent tabComponent;

    /**
     * @param primaryWindow
     * @param editor
     */
    public EditorLayout(Stage primaryWindow, Editor editor) {
        super();

        this.primaryWindow = primaryWindow;
        init(editor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh() {
        removeItem(tabComponent);
        initViewSelector();
        tabComponent.getDiagramTab().refreshContent();
    }

    public void saveLayout(String filePath) {
        GraphLayoutWriter graphLayoutWriter = new GraphLayoutWriter(getDiagramTab().getNodesLocation());
        graphLayoutWriter.writeFile(filePath);
    }

    public void loadLayout(String filePath) {
        GraphLayoutReader graphLayoutReader = new GraphLayoutReader(getDiagramTab().getGraphWindow());
        graphLayoutReader.readFile(filePath);
        getDiagramTab().getGraphWindow().setNodesLocation(graphLayoutReader.getNodesLocation());
    }

    public void switchOrientation() {
        getDiagramTab().switchOrientation();
    }

    private void init(Editor editor) {
        addItem(getNewMainMenuBar(editor));
        initViewSelector();
    }

    private void initViewSelector() {
        tabComponent = new TabComponent(primaryWindow);
        setVgrow(tabComponent, Priority.ALWAYS);
        addItem(tabComponent);
    }

    private DiagramTab getDiagramTab() {
        return tabComponent.getDiagramTab();
    }

    private MenuBarWrapper getNewMainMenuBar(Editor editor) {
        return new MainMenuBar(primaryWindow, editor);
    }
}