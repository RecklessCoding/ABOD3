package com.recklesscoding.abode.gui.videoplayer;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * Wraps a {@link MediaView} and a {@link MediaPlayer} objects to make them easier to use within ABOD3 code.
 * <p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class VideoPlayerView extends MediaView {

    private MediaPlayer mediaPlayer;

    private boolean windowOpenned;

    public VideoPlayerView(String file) {
        Media media = new Media(file);
        mediaPlayer = new MediaPlayer(media);
        setMediaPlayer(mediaPlayer);
    }


    public void openInWindow() {
        windowOpenned = true;
        Platform.runLater(() -> new VideoPlayerWindow(VideoPlayerView.this));
    }

    public void startPlaying() {
        if (windowOpenned) {
            mediaPlayer.play();
        }
    }

    public void pausePlaying() {
        if (windowOpenned) {
            mediaPlayer.pause();
        }
    }

    public void stopPlaying() {
        if (windowOpenned) {
            mediaPlayer.stop();
        }
    }

    public void closedWindow() {
        windowOpenned = false;
    }

    public void resize() {
        fitWidthProperty().bind((Bindings.selectDouble(sceneProperty(), "width")));
        fitHeightProperty().bind(Bindings.selectDouble(sceneProperty(), "height"));

        setPreserveRatio(true);
    }
}