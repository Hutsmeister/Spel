package Spel;

import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.Color;
import Tools.*;
import java.awt.Image;


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
    public Sleutel sleutel;
    /**
     * Declareer hieronder alle objecten die je in je spel nodig hebt
     * Bijvoorbeeld:
     * public Ding d;
     * public ArrayList<AnderDing> andereDingen
     * enz.
     */
    public Achtergrond achtergrond;
    public BestuurbaarDing bd1, bd2;
    // ArrayList<Lift> liften;
    public ArrayList<Tafel> tafels;
    public ArrayList<Stoel> stoelen;
    public ArrayList<Obstakel> vloeren;
    public ArrayList<BewegendDing> beweegObjecten;
    public ArrayList<BewegendDingExtra> specialeBeweegObjecten;
    
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
        maakSpecialeBeweegObjecten();
        maakSleutel();
        maakVloeren();
        voegAanTekenaarToe();
        voegAanBeweegToe();
    }
    
    //vliegende scooters maken
    public void maakSpecialeBeweegObjecten(){
        specialeBeweegObjecten = new ArrayList<BewegendDingExtra>();
        Image p = Laden.laadPlaatje("plaatjes/scooter.png");
        specialeBeweegObjecten.add(new BewegendDingExtra(0, 0, 100, breedte, p));
        int teller = 0;
        while(teller < 10){
            specialeBeweegObjecten.add(new BewegendDingExtra(maakGetal(100, 1800), maakGetal(800, 9000), maakGetal(200, 500), breedte, p));
            teller ++;
        }
        
    }
    
    public int maakGetal(int min, int max){
        return (int)(Math.random()*(max - min) + min);
    }
    
    //sleutel maken
    public void maakSleutel(){
        Image s = Laden.laadPlaatje("plaatjes/sleutel.png");
        sleutel = new Sleutel(0, 0, s);
    }
    
    public void maakAchtergrond(){
        achtergrond = new Achtergrond(breedte, hoogte, Laden.laadPlaatje("plaatjes/klaslokaal.jpg"));
    }
    
    public void maakVloeren(){
        vloeren = new ArrayList<Obstakel>();
        vloeren.add(new Obstakel(0, 1000, breedte, 50, Color.black));
        //vloeren.add(new Obstakel(0, hoogte - 10, breedte, 10, Color.black));
        //vloeren.add(new Obstakel(breedte/2 - 50, hoogte/2 - 10, 100, 10, Color.black)); 
        //int teller = 0;
        //while(teller < aantal){
            //vloeren.add(new Obstakel(teller*100, hoogte - 50*teller, 50, 10, Color.black));
           // teller ++;
        //}
        //int x_begin = teller*100;
        //int y_begin = hoogte - 50*teller;
        //teller = 0;
        //while(teller < aantal){
            //vloeren.add(new Obstakel(x_begin + teller*100, y_begin + 50*teller, 50, 10, Color.black));
            //teller ++;
        //}
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
        tekenaar.voegLijstToe(vloeren);
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
