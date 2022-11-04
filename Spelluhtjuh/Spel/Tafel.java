package Spel;
import java.awt.Graphics2D;
import java.awt.Color;

public class Tafel extends SpelObject{
    public Tafel(int x, int y, int breedte, int hoogte, image Plaatje){
        super(x,y,breedte, hoogte);
        this.plaatje = "plaatjes/tafel.png"
    }
    public void teken(Graphics2D g){
        g.fillRect((int)x, (int)y,breedte, hoogte);
    }
}
