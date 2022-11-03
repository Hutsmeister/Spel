package Spel;
import Tools.*;
import java.awt.Graphics2D;

/**
 * Deze klasse SpelObject is de basis klasse voor alle objecten in je spel
 * Hiervan worden afgeleid (extended) 
 * PlaatjesObject
 * BeweegObject
 * BotsOject
 *
 * @author (E.E. Koldenhof)
 * @version (V2.0)
 */
public abstract class SpelObject implements KanTekenen{
    // Er zijn 3 soorten scope's (reikwijdtes)
    // private: de variabele is alleen in deze klasse bekend
    // public: de variabele is ook buiten de klasse voor alle andere klassen bekend
    // protected: de variabele is alleen in dit package bekend
    public float x, y;
    public int breedte, hoogte;

    /**
     * Constructor for objects of class SpelObject
     * Voor objecten van dit spel object gebruiken alleen de x, y, breedte en hoogte
     */
    public SpelObject(int x, int y, int breedte, int hoogte){
        this.x = x;
        this.y = y;
        this.breedte = breedte;
        this.hoogte = hoogte;
    }
    
    public abstract void teken(Graphics2D g);
}
