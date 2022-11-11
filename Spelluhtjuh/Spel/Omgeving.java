package Spel;

import java.util.ArrayList;
import java.awt.event.KeyEvent;
import Tools.*;

/**
 * In deze klasse maak je de objecten die de omgeving van het spel vormen
 * Dat kunnen enkel objecten zijn 
 * Maar dat kunnen ook lijsten van objecten zijn
 * Of zelfs lege lijsten
 * Voor lijsten maken we gebruik van de ArrayList
**/ 

public class Omgeving {
    public int breedte, hoogte;
    public Tekenaar tekenaar;
    public Beweeg beweeg;
    public Keyboard kb;
    public Mouse muis;
    /**
     * Declareer hieronder alle objecten die je in je spel nodig hebt
     * Bijvoorbeeld:
     * public Ding d;
     * public ArrayList<AnderDing> andereDingen
     * enz.
     */
    public Achtergrond achtergrond;
    /**
     * Constructor voor de objecten van de klasse Omgeving
     * @param breedte, is de breedte van het zichtbare spel
     */
    public Omgeving(int breedte, int hoogte, Tekenaar t, Beweeg b, Keyboard k, Mouse m){
        this.breedte = breedte;
        this.hoogte = hoogte;
        this.tekenaar = t;
        this.beweeg = b;
        kb = k;
        muis = m;
        // geef hier de opdracthen om de methoden uit te voeren
        // die de objecten maken.
        maakAchtergrond();
        voegAanTekenaarToe();
        voegAanBeweegToe();
        maakSpecialeBeweegObjecten();
        maakSleutel();
    }
    
    //vliegende scooters maken
    public void maakSpecialeBeweegObjecten(){
        specialeBeweegObjecten = new ArrayList<BewegendDingExtra>();
        Image p = Laden.laadPlaatje("plaatjes/scooter.png");
        specialeBeweegObjecten.add(new BewegendDingExtra(0, 0, 100, breedte, p));
        /*int teller = 0;
        while(teller < 10){
            specialeBeweegObjecten.add(new BewegendDingExtra(maakGetal(200, 400), maakGetal(200, 600), maakGetal(-200, 200), breedte, p));
            teller ++;
        }
        */
    }
    
    public void maakAchtergrond(){
        achtergrond = new Achtergrond(breedte, hoogte, Laden.laadPlaatje("plaatjes/klaslokaal.jpg"));
    }

    /**
     * Deze procedure voegt de gemaakte objecten aan de
     * tekenlijst van de Tekenaar toe:
     * tekenaar.voegObjectToe(een gemaakt object)
     * tekenaar.voegLijstToe(een gemaakte en gevulde lijst)
     *
     */
    private void voegAanTekenaarToe(){
        tekenaar.voegObjectToe(achtergrond);
        tekenaar.voegLijstToe(specialeBeweegObjecten);
        tekenaar.voegObjectToe(sleutel);
    }
    
    /**
     * Deze procedure voegt de gemaakte objecten aan de
     * beweeglijst van Beweeg toe:
     * beweeg.voegObjectToe(een gemaakt object)
     * beweeg.voegLijstToe(een gemaakte en gevulde lijst)
     *
     */

    private void voegAanBeweegToe(){
        beweeg.voegLijstToe(specialeBeweegObjecten);
    }

}
