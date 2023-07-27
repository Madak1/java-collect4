package hu.ppke.itk.ma;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class BackgroundMusic{

    private final MediaPlayer mediaPlayer;

    /**
     * Background music constructor
     *  - It starts the music what we add in the path variable
     * @param path Where is the music file
     */
    public BackgroundMusic(String path){
        File file = new File(path);
        this.mediaPlayer = new MediaPlayer(new Media(file.toURI().toString()));
        this.mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        this.mediaPlayer.setAutoPlay(true);
    }

    /**
     * Mute mode switcher
     *  - Mute or unmute the background music
     */
    public void muteSwitch(){
        this.mediaPlayer.setMute(!this.mediaPlayer.isMute());
    }

}
