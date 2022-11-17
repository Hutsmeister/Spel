 // author Felix
package Spel;
import java.awt.Graphics2D;
import java.awt.Image;


public class Tafel extends SpelObject{
    Image plaatje;
    public Tafel(int x, int y, int breedte, int hoogte, Image Plaatje){
        super(x,y,breedte, hoogte, null);
        this.plaatje = plaatje;
    }
    public void teken(Graphics2D g){
        g.drawImage(plaatje, (int)x, (int)y, breedte, hoogte);
    }
}
