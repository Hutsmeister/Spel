package Spel;

import Tools.*;
import javax.swing.JFrame;
import java.util.ArrayList;

/**
 * Deze klasse zorgt voor de dynamica van het spel.
 *
 * @author (E.E. Koldenhof)
 * @version (V.1.0)
 * 
 * Deze klasse mag je NIET veranderen
 * 
 */
public class Beweeg{
    private ArrayList<KanBewegen> beweegLijst;
    
    /**
     * Vult de 4 basis objecten die de controller nodig heeft om de 
     * dynamica van het spel te kunnen afhandelen
     */
    public Beweeg(){
        beweegLijst = new ArrayList<KanBewegen>();
    }
    
    public void voegObjectToe(KanBewegen o){
        beweegLijst.add(o);
    }
    
    public void verwijderObject(KanBewegen o){
        beweegLijst.remove(o);
    }

    public void voegLijstToe(ArrayList lijst){
        beweegLijst.addAll(lijst);
    }

    public void verwijderLijst(ArrayList lijst){
        beweegLijst.removeAll(lijst);
    }
    
    public void beweegAlles(float stapGrootte){
        int teller = 0;
        while (teller < beweegLijst.size()){
            beweegLijst.get(teller).beweeg(stapGrootte);
            teller ++;
        }
    }
        
}
