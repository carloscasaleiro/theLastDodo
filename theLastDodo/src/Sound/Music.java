package Sound;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Music {

    Clip clip;
    URL musicURL;

    public void start() {

        musicURL = getClass().getResource("/dodo.wav");
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
    }

    public void play() {

        clip.setFramePosition(0);
        clip.loop(-1);
        clip.start();
    }

    public void stop() {

        clip.stop();
    }
}
