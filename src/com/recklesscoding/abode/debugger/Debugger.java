package com.recklesscoding.abode.debugger;

import com.recklesscoding.abode.debugger.prerecorded.LogSynchronizer;
import com.recklesscoding.abode.debugger.prerecorded.instinct.InstictLogReader;
import com.recklesscoding.abode.debugger.realtime.instinct.InstinctServer;
import com.recklesscoding.abode.gui.videoplayer.VideoPlayerView;

/**
 * Author: Andreas
 * Date: 05/03/2016.
 */
public class Debugger implements IDebugger {

    private InstictLogReader instictLogReader= null;

    private InstinctServer instinctServer = null;

    private VideoPlayerView mediaViewPane = null;

    private LogSynchronizer logSynchronizer = null;

    @Override
    public void readLogFile(String logPath) {
        instictLogReader = new InstictLogReader();
        instictLogReader.readFile(logPath);
    }

    @Override
    public void loadVideo(String videoPath) {
        mediaViewPane = new VideoPlayerView(videoPath);
        mediaViewPane.openInWindow();
    }

    @Override
    public void startServer() {
        if (instinctServer != null) {
            stopServer();
        }
            instinctServer = new InstinctServer();
            instinctServer.startServer();
    }

    @Override
    public void stopServer() {
        instinctServer.stopServer();
        instinctServer = null;
    }

    @Override
    public void startLogDebugger() {
        logSynchronizer = new LogSynchronizer(instictLogReader.getTimePlanElementMap(), mediaViewPane);
        logSynchronizer.startUp();
    }

    @Override
    public void pauseLogDebugger() {
        logSynchronizer.stop();
    }


    @Override
    public void stopLogDebugger() {
        logSynchronizer.stop();
        logSynchronizer = null;
        instictLogReader = null;
    }

    @Override
    public VideoPlayerView getMediaViewPane() {
        return mediaViewPane;
    }
}
