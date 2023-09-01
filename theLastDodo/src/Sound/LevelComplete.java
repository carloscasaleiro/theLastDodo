package Sound;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class LevelComplete {

    Clip clip;

    URL musicURL;

    public void start() {

        musicURL = getClass().getResource("/levelcomplete.wav");
        try {
            setURL(musicURL);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public void setURL(URL soundFileName) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        AudioInputStream sound = AudioSystem.getAudioInputStream(soundFileName);
        clip = AudioSystem.getClip();
        clip.open(sound);
        play();
    }

    public void play() {

        clip.setFramePosition(0);
        clip.start();
    }
}
