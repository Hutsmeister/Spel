package Spel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

/**
 * class BewegendDingExtra - geef hier een beschrijving van deze class
 *
 * @author (jouw naam)
 * @version (versie nummer of datum)
 */
public class BewegendDingExtra extends BewegendDing{
    public int max;
    public Image plaatje;
    
    public BewegendDingExtra(int x, int y, float vx, int max, Image plaatje){
        super(x, y, 30, 30, vx, 0, null);
        this.max = max;
        this.plaatje = plaatje;
    }
    
    public void beweeg(float stap){
        //super.beweeg(stap);
        x += vx*stap;
        y += vy*stap;
        if(x > max){
            x = 0;
        }
        
        if (x < -breedte){
            x = max;
        }
    }
    
    public void teken(Graphics2D g){
        g.drawImage(plaatje, (int)x, (int)y, breedte, hoogte, null);
    }
}

