package Spel;

import java.util.ArrayList;
import java.awt.event.KeyEvent;
import Tools.*;
import java.awt.Color;
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
    /**
     * Declareer hieronder alle objecten die je in je spel nodig hebt
     * Bijvoorbeeld:
     * public Ding d;
     * public ArrayList<AnderDing> andereDingen
     * enz.
     */
    public Sleutel sleutel;
    public Klok klok;
    public Gijs gijs;
    public Stijn stijn;
    public Achtergrond achtergrond;
    public BestuurbaarDing pacman, legoYoda;
    // ArrayList<Lift> liften;
    public ArrayList<Tafel> tafels;
    public ArrayList<Stoel> stoelen;
    public ArrayList<Obstakel> vloeren;
    public ArrayList<Weg> wegen;
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
        klok = new Klok(100, 100, 100, 50, 10);
        
        maakAchtergrond();
        maakSpecialeBeweegObjecten();
        maakSleutel();
        maakGijs();
        maakStijn();
        maakVloeren();
        maakWegen();
        maakTafels();
        maakBestuurbareDingen();
        voegAanTekenaarToe();
        voegAanBeweegToe();
    }
      public void maakTafels(){
        tafels = new ArrayList<Tafel>();
        tafels.add(new Tafel(100, 100, 30, 30,Laden.laadPlaatje("plaatjes/tafel.png")));
        tafels.add(new Tafel(200, 100, 30, 30,Laden.laadPlaatje("plaatjes/tafel.png")));
        tafels.add(new Tafel(300, 100, 30, 30,Laden.laadPlaatje("plaatjes/tafel.png")));
        tafels.add(new Tafel(400, 100, 30, 30,Laden.laadPlaatje("plaatjes/tafel.png")));
        tafels.add(new Tafel(500, 100, 30, 30,Laden.laadPlaatje("plaatjes/tafel.png")));
    } 
    
    public void maakBestuurbareDingen(){
        Image p = Laden.laadPlaatje("plaatjes/pacman.gif");
        Image y = Laden.laadPlaatje("plaatjes/legoYoda.png");
        
        pacman = new BestuurbaarDing(100, 100, 200, p, false, 10 );
        pacman.register(this);
        pacman.defineKeys(KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_UP, KeyEvent.VK_DOWN);
        
        legoYoda = new BestuurbaarDing(200, 100, 250, y, false, 10); 
        legoYoda.register(this);
        legoYoda.defineKeys(KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W, KeyEvent.VK_S);
    }
    
    //vliegende scooters maken
    public void maakSpecialeBeweegObjecten(){
        specialeBeweegObjecten = new ArrayList<BewegendDingExtra>();
        Image p = Laden.laadPlaatje("plaatjes/scooter.png");
        //specialeBeweegObjecten.add(new BewegendDingExtra(maakGetal(200, 400), maakGetal(1, 1000), maakGetal(-200, 200), breedte, p));
        //specialeBeweegObjecten.add(new BewegendDingExtra(maakGetal(100, 400), maakGetal(200, 600), maakGetal(-200, 200), breedte, p));
        int teller = 0;
        while(teller < 3){
            specialeBeweegObjecten.add(new BewegendDingExtra(maakGetal(100, 1800), maakGetal(800, 900), maakGetal(200, 500), breedte, p));
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
    
        public void maakGijs(){
        Image g = Laden.laadPlaatje("plaatjes/HolleBolleGijs.png");
        gijs = new Gijs(500, 500, g);
    }
    
    public void maakStijn(){
        Image st = Laden.laadPlaatje("plaatjes/Stijn.png");
        stijn = new Stijn(200, 200, st);
    }
    
    public void maakAchtergrond(){
        achtergrond = new Achtergrond(breedte, hoogte, Laden.laadPlaatje("plaatjes/pastelblauw.jpg"));
    }
    
    public void maakVloeren(){
        vloeren = new ArrayList<Obstakel>();
        vloeren.add(new Obstakel(0, hoogte, breedte, 50, Color.black));
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
    
    public void maakWegen(){
        wegen = new ArrayList<Weg>();
        int teller = 0;
        Image q = Laden.laadPlaatje("plaatjes/weg.png");
        while(teller < 20){
            wegen.add(new Weg(0+252*teller, 800, 253, 201, q));
            teller ++;
        }
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
        tekenaar.voegObjectToe(sleutel);
        tekenaar.voegObjectToe(gijs);
        tekenaar.voegObjectToe(stijn);
        tekenaar.voegLijstToe(vloeren);
        tekenaar.voegLijstToe(wegen);
        tekenaar.voegLijstToe(specialeBeweegObjecten);
        tekenaar.voegLijstToe(tafels);
        tekenaar.voegObjectToe(pacman);
        tekenaar.voegObjectToe(legoYoda);
        tekenaar.voegObjectToe(klok);
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
        beweeg.voegObjectToe(pacman);
        beweeg.voegObjectToe(legoYoda);
        beweeg.voegObjectToe(klok);
    }

}
