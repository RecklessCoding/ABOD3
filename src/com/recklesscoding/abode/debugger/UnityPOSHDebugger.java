package com.recklesscoding.abode.debugger;

import com.recklesscoding.abode.debugger.realtime.xposh.UnityPOSHServer;
import com.recklesscoding.abode.gui.videoplayer.VideoPlayerView;

/**
 * Author: Andreas
 * Date: 05/03/2016.
 */
public class UnityPOSHDebugger implements IDebugger {
    private UnityPOSHServer unityServer = null;
    private VideoPlayerView mediaViewPane = null;

    @Override
    public void readLogFile(String logPath) {
        return;
    }

    @Override
    public void loadVideo(String videoPath) {
        mediaViewPane = new VideoPlayerView(videoPath);
        mediaViewPane.openInWindow();
    }

    @Override
    public void startServer() {
        if (unityServer != null) {
            stopServer();
        }
        unityServer = new UnityPOSHServer();
        unityServer.startServer();
    }

    @Override
    public void stopServer() {
        unityServer.stopServer();
        unityServer = null;
    }

    @Override
    public void startLogDebugger() {
        return;
    }

    @Override
    public void pauseLogDebugger() {
        return;
    }


    @Override
    public void stopLogDebugger() {
        return;
    }

    @Override
    public VideoPlayerView getMediaViewPane() {
        return mediaViewPane;
    }
}
