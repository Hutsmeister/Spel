//author Sam

package Spel;
import java.awt.Graphics2D;
import java.awt.Image;


public class Hart extends SpelObject{
    Image plaatje;
    public Hart(int x, int y, int breedte, int hoogte, Image plaatje){
        super(x,y,breedte, hoogte);
        this.plaatje = plaatje;
    }
    public void teken(Graphics2D g){
        g.drawImage(plaatje, (int)x, (int)y, breedte, hoogte, null);
    }
}
