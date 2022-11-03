package Spel;
import java.awt.Graphics2D;

/**
 * Abstract class BotsObject
 * Wat doet deze klasse?
 *
 * @author Dhr. Koldenhof
 * @version (V2.0)
 * 
 * Deze klasse mag je NIET veranderen
 * 
 */

public abstract class BotsObject extends BeweegObject implements KanBotsen{
    public float oldx, oldy;
    
    public BotsObject(int x, int y,int breedte, int hoogte){
        super(x,y,breedte, hoogte);
    }
    
    public abstract void teken(Graphics2D g);
    
    public abstract void beweeg(float stapGrootte);
    
    public void bewaar(){
        oldx = x;
        oldy = y;
    }
    
    public void zetxTerug(){
        x = oldx;
    }
    
    public void zetyTerug(){
        y = oldy;
    }
    
    public boolean botstMet(SpelObject ander){
        boolean raak = false;
        if(x < ander.x + ander.breedte){
            if(x + breedte > ander.x){
                if( y < ander.y + ander.hoogte){
                    if(y + hoogte >  ander.y){
                        raak = true;
                    }
                }
            }
        }
        return raak;
    }
    
    public String ikKomVan(SpelObject ander){
        String richting = "geen";
        float mijnOudeLinkerkant = oldx;
        float mijnOudeRechterkant = oldx + breedte;
        float mijnOudeBovenkant = oldy;
        float mijnOudeOnderkant = oldy + hoogte;

        float andereLinkerkant = ander.x;
        float andereRechterkant = ander.x + ander.breedte;
        float andereBovenkant = ander.y;
        float andereOnderkant = ander.y + ander.hoogte;

        if(andereLinkerkant>= mijnOudeRechterkant){
            richting = "links";
        }
        if(andereRechterkant<= mijnOudeLinkerkant){
            richting = "rechts";
        }
        if(andereOnderkant <= mijnOudeBovenkant){
            richting = "beneden";
        }
        if(andereBovenkant >= mijnOudeOnderkant){
            richting = "boven";
        }
        return richting;
    }
    
    public String ikKomVan(BotsObject ander){
        String richting = "geen";
        float mijnOudeLinkerkant = oldx;
        float mijnOudeRechterkant = oldx + breedte;
        float mijnOudeBovenkant = oldy;
        float mijnOudeOnderkant = oldy + hoogte;

        float andereOudeLinkerkant = ander.oldx;
        float andereOudeRechterkant = ander.oldx + ander.breedte;
        float andereOudeBovenkant = ander.oldy;
        float andereOudeOnderkant = ander.oldy + ander.hoogte;

        if(andereOudeLinkerkant>= mijnOudeRechterkant){
            richting = "links";
        }
        if(andereOudeRechterkant<= mijnOudeLinkerkant){
            richting = "rechts";
        }
        if(andereOudeOnderkant <= mijnOudeBovenkant){
            richting = "beneden";
        }
        if(andereOudeBovenkant >= mijnOudeOnderkant){
            richting = "boven";
        }
        return richting;
    }
}
