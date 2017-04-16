package com.recklesscoding.abode.gui.views.diagramview.diagram;

/**
 * Athor: Andreas
 * Date: 16/02/2016.
 */
public class GraphUpdater implements Runnable {

    // In MS.
    private long decreaseGlowTimer = 10000;

    private boolean isRunning = false;

    private final Thread thread;

    private GraphWindow graphWindow;

    public GraphUpdater(GraphWindow graphWindow) {
        this.graphWindow = graphWindow;
        this.thread = new Thread(this);
    }

    @Override
    public void run() {
        boolean decreaseGlow = false;
        long timeThreadStarted = System.currentTimeMillis();
        long currentTime;

        while (isRunning) {
            currentTime = System.currentTimeMillis();
            if (isTimeToUpdateElement(timeThreadStarted, currentTime)) {
                decreaseGlow = true;
            }
            graphWindow.update(decreaseGlow);
            decreaseGlow = false;
        }

        setAllCellsToNoGlow();
    }

    private boolean isTimeToUpdateElement(long timeThreadStarted, long currentTime) {
        return ((timeThreadStarted - currentTime) % decreaseGlowTimer) == 0;
    }


    private void setAllCellsToNoGlow() {
        // Sets back everything to 0 glow. 30 was chosen as we decrease by 0.1 per time.
        for (int i = 0; i < 30; i++) {
            graphWindow.update(true);
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
