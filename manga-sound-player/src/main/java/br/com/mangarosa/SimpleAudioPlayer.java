package br.com.mangarosa;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SimpleAudioPlayer {
    private Clip clip;
    private Long currentFrame;
    private AudioInputStream audioInputStream;
    private String filePath;
    private String status;

    public SimpleAudioPlayer(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.filePath = path;
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.loop(0); // Não repete continuamente
    }

    public void play() {
        clip.start();
        status = "play";
    }

    public void pause() {
        if ("paused".equals(status)) {
            System.out.println("Áudio já está pausado.");
            return;
        }
        currentFrame = clip.getMicrosecondPosition();
        clip.stop();
        status = "paused";
    }

    public void resumeAudio() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        if ("play".equals(status)) {
            System.out.println("Áudio já está tocando.");
            return;
        }
        clip.close();
        resetAudioStream();
        clip.setMicrosecondPosition(currentFrame);
        this.play();
    }

    public void restart() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        clip.stop();
        clip.close();
        resetAudioStream();
        currentFrame = 0L;
        clip.setMicrosecondPosition(0);
        this.play();
    }

    public void stop() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }

    public void jump(long c) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        if (c > 0 && c < clip.getMicrosecondLength()) {
            clip.stop();
            clip.close();
            resetAudioStream();
            currentFrame = c;
            clip.setMicrosecondPosition(c);
            this.play();
        }
    }

    private void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        clip.open(audioInputStream);
        clip.loop(0);
    }
}
