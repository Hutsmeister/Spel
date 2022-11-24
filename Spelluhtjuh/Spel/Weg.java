package Spel;
import java.awt.Graphics2D;
import java.awt.Image;


public class Weg extends SpelObject{
    Image plaatje;
    public Weg(int x, int y, int breedte, int hoogte, Image Plaatje){
        super(x,y,breedte, hoogte);
        this.plaatje = plaatje;
    }
    public void teken(Graphics2D g){
        g.drawImage(plaatje, (int)x, (int)y, breedte, hoogte, null);
    }
}
