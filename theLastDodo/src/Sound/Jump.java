package Sound;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Jump {

    Clip clip;

    AudioInputStream audioInputStream;

    URL musicURL;

    public void start() {

        musicURL = getClass().getResource("/jump.wav");
        try {
            setURL(musicURL);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public void setURL(URL soundFileName) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        audioInputStream = AudioSystem.getAudioInputStream(soundFileName);
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        play();
    }

    public void play() {

        clip.setFramePosition(0);
        clip.start();
    }

    public void stop() {

        clip.stop();
        try {
            audioInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
