package com.recklesscoding.abode.gui.videoplayer;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * <p>
 * Responsible for handling the window displaying the video using {@link VideoPlayerView}, live or pre-recorded, during plan debugging.
 * </p>
 *
 * @author :   Andreas Theodorou - www.recklesscoding.com
 * @version :   %G%
 */
public class VideoPlayerWindow extends Stage {

    private static final int WINDOW_WIDTH = 720;

    private static final int WINDOW_HEIGHT = 480;

    private Group group = new Group();
    private double mediaWidth;
    private double mediaHeight;
    private Label topLeft = new Label("T - L");
    private Label topRight = new Label("T - R");
    private Label bottomLeft = new Label("B - L");
    private Label bottomRight = new Label("B - R");

    public VideoPlayerWindow(VideoPlayerView videoPlayer) {
        Media media = videoPlayer.getMediaPlayer().getMedia();
        mediaWidth = media.getWidth();
        mediaHeight = media.getHeight();

        init(videoPlayer);
        addMuteButton(videoPlayer);
//        initSensorsLabel();
//        initGroup();

        show();
    }

    private void init(VideoPlayerView videoPlayer) {
        initWindowProperties(videoPlayer);
        setScene(initScene(videoPlayer));
        videoPlayer.resize();
    }

    private Scene initScene(VideoPlayerView videoPlayer) {
        BorderPane pane = new BorderPane();
        pane.setScaleShape(true);
        pane.setCenter(videoPlayer);
        pane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        return new Scene(pane, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    private void initWindowProperties(VideoPlayerView videoPlayer) {
        setAlwaysOnTop(true);
        setOnCloseRequest(t -> {
            videoPlayer.closedWindow();
            close();
        });
    }

    private void addMuteButton(VideoPlayerView videoPlayer) {
        Button mute = new Button("Mute");
        mute.setLayoutX(50);
        mute.setLayoutY(50);
        mute.setBackground(Background.EMPTY);
        mute.setOnMousePressed(event -> videoPlayer.getMediaPlayer().setVolume(0));
        mute.setVisible(true);
        group.getChildren().add(mute);
    }

    private void initSensorsLabel() {
        topLeft.setLayoutX(mediaWidth / 2 - 50);
        topLeft.setLayoutY(mediaHeight - 75);
        topRight.setLayoutX(mediaWidth / 2 + 50);
        topRight.setLayoutY(mediaHeight - 75);
        bottomLeft.setLayoutX(mediaWidth / 2 - 50);
        bottomLeft.setLayoutY(mediaHeight - 25);
        bottomRight.setLayoutX(mediaWidth / 2 + 50);
        bottomRight.setLayoutY(mediaHeight - 25);
    }

    private void initGroup() {
        group.getChildren().add(topLeft);
        group.getChildren().add(topRight);
        group.getChildren().add(bottomLeft);
        group.getChildren().add(bottomRight);
    }


    public void setBottomRightValue(double value) {
        this.bottomRight.setText(String.valueOf(value));
    }

    public void setTopLeftValue(double value) {
        this.topLeft.setText(String.valueOf(value));
    }

    public void setTopRightValue(double value) {
        this.topRight.setText(String.valueOf(value));
    }

    public void setBottomLeftValue(double value) {
        this.bottomLeft.setText(String.valueOf(value));
    }
}