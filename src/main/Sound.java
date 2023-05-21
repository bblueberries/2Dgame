package main;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {
    private MediaPlayer mediaPlayer;
    private String soundPath[] ;

    // Dict for using sound
    // **NOTE SE means Sound Effect
    // 0 : BG Song
    // 1 : HitMonster SE
    // 2 : wining SE
    // 3 : select option using W,S SE
    // 4 : wining SE2
    // 5 : title BG Song
    public Sound(int i) 
    {
    	soundPath = new String[30];
    	this.soundPath[0] = "/sound/themesong2.mp3";
    	this.soundPath[1] = "/sound/hitmonster.mp3";
    	this.soundPath[2] = "/sound/wining.mp3";
    	this.soundPath[3] = "/sound/selectsound.mp3";
    	this.soundPath[4] = "/sound/ezsound.mp3";
    	this.soundPath[5] = "/sound/titlesong.mp3";
    	
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
    public void setVolumn(double volume)
    {
    	mediaPlayer.setVolume(volume);
    }
    
    public String getSoundPath(int i) {
    	return this.soundPath[i];
    }
}