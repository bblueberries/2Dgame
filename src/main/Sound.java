package main;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {
    MediaPlayer mediaPlayer;
    String soundFile;

    public Sound(String soundFile) {
        this.soundFile = soundFile;
        Media media = new Media(getClass().getResource(soundFile).toString());
        mediaPlayer = new MediaPlayer(media);
    }

    public void play() {
        mediaPlayer.play();
    }

    public void loop() {
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    public void stop() {
        mediaPlayer.stop();
    }
}