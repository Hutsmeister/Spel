//Work in progress deze als je dit kan oplossen pls doe

package Spel;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Controller extends BeweegObject{
    private Omgeving omgeving;
    //keys declareren
    //private int ;
    
    public Controller(Omgeving o){
        super(0,0,0,0);
        omgeving = o;
        definitieToestanden();
        omgeving.huidigetoestand = omgeving.begintoestand;
        definitieInvoer();
        defineerToetsen();
    }
    
    public void definitieToestanden(){
        //toestandsverzameling bij eerste (op getal; die moet invoeren bij eindtoestand)
        /*omgeving.toestandsverzameling = new String[] ();
        omgeving.begintoestand = omgeving.toestandsverzameling[0];
        omgeving.eindtoestandsverzameling = new String[] (omgeving.toestandsverzameling[]);
    */}
    
    private void definitieInvoer(){
        //invoerverzameling tussen de haakjes
        //omgeving.invoerverzameling = new String[] ();
    }
    
    public void defineerToetsen(){
        //inputs met keys matchen bijv this.f1 = KeyEvent.VK_F1;
        /*this. = KeyEvent.VK_ ;
        this. = KeyEvent.VK_ ;
        this. = KeyEvent.VK_ ;*/
    }
    
    public void beweeg(float stap){
        String input = " ";
        //allemaal gedoe moet hieronder
    }
    
    private boolean spelerBotstmetVijand(float stap){
        boolean antwoord = false;
        int teller = 0;
        while(teller < omgeving.vijanden.size()){
            Vijand v = omgeving.vijanden.get(teller);
            Speler sp = omgeving.speler;
            if(sp.botstMet(v)){
                antwoord = true;
            }
            teller ++;
        }
        return antwoord;
    }
    
    public void teken(Graphics2D g){
        //deze controller hoeft niet getekend te worden
    }
}
