//author Demi
package Spel;
import java.awt.Image;
import java.awt.Graphics2D;

public class Achtergrond extends SpelObject
{
    public Image plaatje;
    
    public Achtergrond(int breedte, int hoogte, Image plaatje){
        super(0, 0, breedte, hoogte);
        this.plaatje = plaatje;
    }
    
    public void teken(Graphics2D g){
        g.drawImage(plaatje, (int)x, (int)y, breedte, hoogte, null); 
    }
}
