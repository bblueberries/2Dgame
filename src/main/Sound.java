package main;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {
    private MediaPlayer mediaPlayer;
    private String soundPath[] = new String[30];

    // Dict for using sound
    // 0 : bg sound
    // 1 : hitmonster
    // 2 : wining gamestate
    // 3 : select option using W,S
    public Sound(int i) {
    	this.soundPath[0] = "/sound/themesong.mp3";
    	this.soundPath[1] = "/sound/hitmonster.mp3";
    	this.soundPath[2] = "/sound/wining.mp3";
    	this.soundPath[3] = "/sound/selectsound.mp3";
    	Media media = new Media(getClass().getResource(this.soundPath[i]).toString());
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
    
    public String getSoundPath(int i) {
    	return this.soundPath[i];
    }
}