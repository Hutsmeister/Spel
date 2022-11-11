package Spel;
import java.awt.Graphics2D;
import java.awt.Color;

/**
 * class BewegendDing - geef hier een beschrijving van deze class
 *
 * @author (jouw naam)
 * @version (versie nummer of datum)
 */
public class BewegendDing extends BotsObject{
    public float g;
    public Omgeving omgeving;    
    
    public BewegendDing(int x, int y, int breedte, int hoogte, float vx, float vy, Omgeving o){
        super(x, y, breedte, hoogte);
        this.vx = vx;
        this.vy = vy;
        omgeving = o;
        g = 10;
    }
    
    public BewegendDing(int x, int y, Omgeving o){
        super(x, y, 20, 20);
        omgeving = o;
        this.vx = 0;
        this.vy = 0;
        g = 10;
    }
    
    public void beweeg(float stap){
        bewaar();
        x += vx*stap;
        vy += g;
        y += vy*stap;
        
        int teller = 0;
        while(teller < omgeving.vloeren.size()){
            Obstakel vl = omgeving.vloeren.get(teller);
            if(botstMet(vl)){
                if(ikKomVan(vl).equals("boven")){
                    zetyTerug();
                    vy = 0;
                }
            }
            teller ++;
        }
    }
    
    public void teken(Graphics2D g){
        g.setColor(Color.red);
        g.fillRect((int)x, (int)y, breedte, hoogte);
    }
}
