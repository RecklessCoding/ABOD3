package com.recklesscoding.abode.debugger;

import com.recklesscoding.abode.gui.videoplayer.VideoPlayerView;

/**
 * Author: @Andreas.
 * Date : @02/08/2016
 */
public interface IDebugger {
    void readLogFile(String logPath);

    void loadVideo(String videoPath);

    void startServer();

    void stopServer();

    void startLogDebugger();

    void pauseLogDebugger();

    void stopLogDebugger();

    VideoPlayerView getMediaViewPane();
}
