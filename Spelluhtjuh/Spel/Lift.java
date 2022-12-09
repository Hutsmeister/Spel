package Spel;
import java.awt.Color;
import java.awt.Graphics2D;


/**
 * class Lift - geef hier een beschrijving van deze class
 *
 * @author (felix)
 * @version (versie nummer of datum)
 */
public class Lift extends BotsObject
{
    public Color kleur;
    public Omgeving omgeving;
    public int max;
   
    public Lift(int max, int x, int y, int breedte, float vy, int r, int g, int b, Omgeving o){
        super(x, y, breedte, 10);
        this.max = max;
        this.vy = vy;
        this.vx = 0;
        omgeving = o;
        this.kleur = new Color(r,g,b);
    }
   
    public void beweeg(float stap){
        bewaar();
        y += vy*stap;
        if(y < 0){
            y = max;
        }
        if(y > max){
            y = 0;
        }
        if(botstMet(omgeving.pacman)){
            if(ikKomVan(omgeving.pacman).equals("beneden")){
                omgeving.pacman.y = y - omgeving.pacman.hoogte;
                omgeving.pacman.vy = vy;
            }
        }
       
        if(botstMet(omgeving.legoYoda)){
            if(ikKomVan(omgeving.legoYoda).equals("beneden")){
                omgeving.legoYoda.y = y - omgeving.legoYoda.hoogte;
                omgeving.legoYoda.vy = vy;
            }
        }
    }
   
    public void teken(Graphics2D g){
        g.setColor(kleur);
        g.fillRect((int)x, (int)y, breedte, hoogte);
    }
}