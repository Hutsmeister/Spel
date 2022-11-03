package Spel;

import java.awt.Graphics2D;

/**
 * Abstract class BeweegObject
 * Wat doet deze klasse?
 *
 * @author Dhr. Koldenhof
 * @version (V2.0)
 * 
 * Deze klasse mag je NIET veranderen
 * 
 */

public abstract class BeweegObject extends SpelObject implements KanTekenen, KanBewegen{
    public float vx, vy;
    
    public BeweegObject(int x, int y, int breedte, int hoogte){
        super(x,y,breedte, hoogte);
    }
        
    public abstract void teken(Graphics2D g);
    
    public abstract void beweeg(float stapGrootte);

}
