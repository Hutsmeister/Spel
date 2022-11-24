package Spel;
import java.awt.Graphics2D;
import java.awt.Image;


/**
 * class Jumpscare - geef hier een beschrijving van deze class
 *
 * @author (jouw naam)
 * @version (versie nummer of datum)
 */
public class Jumpscare extends SpelObject
{
    Image plaatje;
    public boolean opScherm;
    public Jumpscare(Image plaatje){
        super(1920 - 512/2, 1020 - 512/2, 512, 512);
        this.plaatje = plaatje;
        opScherm = false;
    }
    
    
    
    public void teken(Graphics2D g){
        g.drawImage(plaatje, (int)x, (int)y, breedte, hoogte, null);
    }
}
