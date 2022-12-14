package controler;
import model.Parameters;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

/*
 * No Copyright (C) - Free to use
 * Source : https://www.codespeedy.com/how-to-add-audio-on-jswing-in-java/
 * File Music (exemple) : https://file-examples.com/storage/fe352586866388d59a8918d/2017/11/file_example_WAV_1MG.wav
 * File Music (theme) : https://www.auboutdufil.com/index.php?id=492#downloads
 */

public class Music {
    Clip clip;
    AudioInputStream sound;
    public Music(String soundFileName) {
        setFile(soundFileName);
    }
    public void setFile(String soundFileName) {
        try {
            File file = new File(Parameters.PATH_SOUND + soundFileName);

            sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
            loop();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void play() {
        clip.start();
    }

    private void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() throws IOException {
        sound.close();
        clip.close();
        clip.stop();
    }
}