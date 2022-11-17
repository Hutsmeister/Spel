 // author Felix
package Spel;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Image;

public class Tafel extends SpelObject{
    public Tafel(int x, int y, int breedte, int hoogte, Image Plaatje){
        super(x,y,breedte, hoogte);

    }
    public void teken(Graphics2D g){
        g.fillRect((int)x, (int)y,breedte, hoogte);
    }
}
