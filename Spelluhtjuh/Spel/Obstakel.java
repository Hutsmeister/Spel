package Spel;
import java.awt.Graphics2D;
import java.awt.Color;

/**
 * class Obstakel - geef hier een beschrijving van deze class
 *
 * @author (jouw naam)
 * @version (versie nummer of datum)
 */
public class Obstakel extends SpelObject{
   private Color kleur;
    
    public Obstakel(int x, int y, int breedte, int hoogte, Color kleur){
       super(x, y, breedte, hoogte, null);
       this.kleur = kleur;
   }
    
   public void teken(Graphics2D g){
       g.setColor(kleur);
       g.fillRect((int)x, (int)y, breedte, hoogte);
       g.fillOval((int)x, (int)y, breedte, hoogte);
       g.drawOval((int)x, (int)y, breedte, hoogte);
   }
}
