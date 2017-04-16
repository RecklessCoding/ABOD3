package com.recklesscoding.abode.debugger.prerecorded;

import com.recklesscoding.abode.gui.videoplayer.VideoPlayerView;
import com.recklesscoding.abode.core.plan.planelements.PlanElement;

import java.util.Map;

/**
 * Synchronizers the map extracted by reading a local log file with the running ABODE, by poking the diagram view.
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class LogSynchronizer implements Runnable {

    private boolean isRunning = false;

    private final Thread thread;

    private Map<Double, PlanElement> timePlanElementMap;

    private VideoPlayerView mediaViewPane;

    public LogSynchronizer(Map<Double, PlanElement> timePlanElementMap, VideoPlayerView mediaViewPane) {
        this.timePlanElementMap = timePlanElementMap;
        this.mediaViewPane = mediaViewPane;
        this.thread = new Thread(this);
    }

    /**
     * Don't call to start running the thread. Instead use start() method.
     */
    @Override
    public void run() {
        long timeThreadStarted = System.nanoTime();
        long currentTime;
        double timeDifference;
        Double time;
        int count = 0;
        boolean notFound = true;

        if (mediaViewPane != null) {
            mediaViewPane.startPlaying();
        }
        while (isRunning) {
            if (count < timePlanElementMap.keySet().size()) {
                time = (Double) timePlanElementMap.keySet().toArray()[count];
            } else {
                break;
            }
            while (notFound) {
                currentTime = System.nanoTime();
                timeDifference = getTimeDifference(timeThreadStarted, currentTime);
                if (isTimeToUpdateElement(timeDifference, time)) {
                    timePlanElementMap.get(time).setToUpdate();
                    notFound = false;
                    count++;
                }
            }
            notFound = true;
        }
        isRunning = false;
    }

    private double getTimeDifference(long timeStarted, long currentTime) {
        return (currentTime - timeStarted) / 1e6;
    }

    private boolean isTimeToUpdateElement(double timeThreadStarted, Double time) {
        return ((time) <= timeThreadStarted - 0.25) && (timeThreadStarted + 0.25 >= (time));
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
