package Spel;
import java.awt.Graphics2D;
import java.awt.Color;

/**
 * class Stoel - geef hier een beschrijving van deze class
 *
 * @author (Demi)
 * @version (versie nummer of datum)
 */
public class Stoel extends SpelObject
{
    private Color kleur;
    
    public Obstakel(int x, int y, int breedte, int hoogte, Color kleur){
        super(x, y, breedte, hoogte);
        this.kleur = kleur;
    }
    
    public void teken(Graphics2D g){
        g.setColor(kleur);
        g.fillRect((int)x, (int)y, breedte, hoogte);
    }
    
}
