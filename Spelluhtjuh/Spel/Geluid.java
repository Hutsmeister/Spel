//author Demi
package Spel;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Geluid{
    private Clip clip;
    
    public Geluid(String filenaam){
        try{
            AudioInputStream ai = AudioSystem.getAudioInputStream(new File(filenaam).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(ai);
        } catch(Exception ex){
            System.out.println("fout bij het laden van een geluidsbestand");
            ex.printStackTrace();
        }
    }
    
    public void speelInHerhaling(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    public void speelaf(){
        clip.stop();
        clip.setFramePosition(0);
        clip.start();
    }
    
    public void stop(){
        clip.stop();
        clip.setFramePosition(0);
    }
}
