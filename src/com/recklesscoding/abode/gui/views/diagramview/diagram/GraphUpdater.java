package com.recklesscoding.abode.gui.views.diagramview.diagram;

/**
 * Athor: Andreas
 * Date: 16/02/2016.
 */
public class GraphUpdater implements Runnable {

    // In MS.
    private long decreaseGlowTimer = 1000;

    private boolean isRunning = false;

    private final Thread thread;

    private GraphWindow graphWindow;

    public GraphUpdater(GraphWindow graphWindow) {
        this.graphWindow = graphWindow;
        this.thread = new Thread(this);
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                Thread.sleep(decreaseGlowTimer);
                graphWindow.update();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        setAllCellsToNoGlow();
    }

    private void setAllCellsToNoGlow() {
        // Sets back everything to 0 glow. 30 was chosen as we decrease by 0.1 per time.
        for (int i = 0; i < 30; i++) {
            graphWindow.update();
        }
    }

    /**
     * Starts the thread.
     */
    public void startUp() {
        if (!isRunning) {
            isRunning = true;
            thread.start();
        }
    }

    /**
     * Stops the thread from running on its next iteration.
     */
    public void stop() {
        isRunning = false;
    }
}
