package Spel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;


public class Jochem extends BewegendDing{
    public int max;
    public Image plaatje;
    
    public Jochem(int x, int y, float vx, int max, Image plaatje){
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

