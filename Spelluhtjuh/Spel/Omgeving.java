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
    public ArrayList<Obstakel> muren;
    public ArrayList<Weg> wegen;
    public ArrayList<Jochem> jochems;
    public ArrayList<Blokje> blokjes;
    public ArrayList<BewegendDing> beweegObjecten;
    public ArrayList<BewegendDingExtra> specialeBeweegObjecten;
    public ArrayList<Hart> levens; 
    
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
        klok = new Klok(910, 64, 100, 50, 900);
        
        maakAchtergrond();
        maakSpecialeBeweegObjecten();
        maakJochems();
        maakSleutel();
        maakGijs();
        maakStijn();
        maakBlokjes();
        maakVloeren();
        maakWegen();
        maakTafels();
        maakBestuurbareDingen();
        maakMuren();
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
    
    public void maakMuren(){
        muren = new ArrayList<Obstakel>();
        muren.add(new Obstakel(0,0, 1, hoogte, Color.BLACK));
        muren.add(new Obstakel(0,1, breedte, 1, Color.BLACK));
        muren.add(new Obstakel(0,hoogte, breedte, 1, Color.BLACK));
        muren.add(new Obstakel(breedte, 0, 1, hoogte, Color.BLACK));        
    }
    
    //levens op het scherm zetten
    public void maakLevens(){
        Image h = Laden.laadPlaatje("plaatjes/hart.png");
        Image p = Laden.laadPlaatje("plaatjes/pacman.png");
        Image y = Laden.laadPlaatje("plaatjes/legoYoda.png");
        levens = new ArrayList<Hart>();
        int tellerpacman = 10;
        int tellerlegoyoda = 10;
        levens.add(new Hart(80, 20, 30, 30, p));
        levens.add(new Hart(80, 60, 30, 30, y));
        while(tellerpacman > 0){
            levens.add(new Hart(100 + 20 * tellerpacman, 20, 30, 30, h));
            tellerpacman --;
        }
        while(tellerlegoyoda > 0){
            levens.add(new Hart(100 + 20 * tellerlegoyoda, 60, 30, 30, h));
            tellerlegoyoda --;
        }
    }
    
    public void maakBestuurbareDingen(){
        Image p = Laden.laadPlaatje("plaatjes/pacman.png");
        Image y = Laden.laadPlaatje("plaatjes/legoYoda.png");
        
        pacman = new BestuurbaarDing(100, 100, 200, p, false, 10 );
        pacman.register(this);
        pacman.defineKeys(KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_UP, KeyEvent.VK_DOWN);
        
        legoYoda = new BestuurbaarDing(200, 100, 250, y, false, 10); 
        legoYoda.register(this);
        legoYoda.defineKeys(KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W, KeyEvent.VK_S);
    }
    public void maakBlokjes(){
       blokjes = new ArrayList<Blokje>();
        blokjes.add(new Blokje(9,978, 152, 12,Laden.laadPlaatje("plaatjes/rechthoek.png")));
        blokjes.add(new Blokje(313,978, 152, 12,Laden.laadPlaatje("plaatjes/rechthoek.png")));
        blokjes.add(new Blokje(629,978, 152, 12,Laden.laadPlaatje("plaatjes/rechthoek.png")));
        blokjes.add(new Blokje(945,978, 152, 12,Laden.laadPlaatje("plaatjes/rechthoek.png")));
        blokjes.add(new Blokje(1261,978, 152, 12,Laden.laadPlaatje("plaatjes/rechthoek.png")));
        blokjes.add(new Blokje(1577,978, 152, 12,Laden.laadPlaatje("plaatjes/rechthoek.png")));
        blokjes.add(new Blokje(1893,978, 152, 12,Laden.laadPlaatje("plaatjes/rechthoek.png")));
        blokjes.add(new Blokje(1640,173, 480, 43,Laden.laadPlaatje("plaatjes/rechthoek.png")));
    }


    
    //vliegende scooters maken
    public void maakSpecialeBeweegObjecten(){
        specialeBeweegObjecten = new ArrayList<BewegendDingExtra>();
        Image p = Laden.laadPlaatje("plaatjes/scooter.png");
        //specialeBeweegObjecten.add(new BewegendDingExtra(maakGetal(200, 400), maakGetal(1, 1000), maakGetal(-200, 200), breedte, p));
        //specialeBeweegObjecten.add(new BewegendDingExtra(maakGetal(100, 400), maakGetal(200, 600), maakGetal(-200, 200), breedte, p));
        int teller = 0;
        while(teller < 3){
            specialeBeweegObjecten.add(new BewegendDingExtra(maakGetal(100, 1800), maakGetal(890, 930), maakGetal(200, 500), breedte, p));
            teller ++;
        }
        
    }
    
    public void maakJochems(){
        jochems = new ArrayList<Jochem>();
        Image j = Laden.laadPlaatje("plaatjes/Jochem.jpeg");
        jochems.add(new Jochem(maakGetal(0, 1800), maakGetal(0, 600), maakGetal(100, 300), maakGetal(600, breedte), j));
        jochems.add(new Jochem(maakGetal(0, 1800), maakGetal(0, 600), maakGetal(100, 300), maakGetal(600, breedte), j));
        jochems.add(new Jochem(maakGetal(0, 1800), maakGetal(0, 600), maakGetal(100, 300), maakGetal(600, breedte), j));
        jochems.add(new Jochem(maakGetal(0, 1800), maakGetal(0, 600), maakGetal(100, 300), maakGetal(600, breedte), j));
        jochems.add(new Jochem(maakGetal(0, 1800), maakGetal(0, 600), maakGetal(100, 300), maakGetal(600, breedte), j));
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
        gijs = new Gijs(1805, 18, g);
    }
    
    public void maakStijn(){
        Image st = Laden.laadPlaatje("plaatjes/Stijn.png");
        stijn = new Stijn(1701, 33, st);
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
        tekenaar.voegLijstToe(jochems);
        tekenaar.voegLijstToe(blokjes);
        tekenaar.voegLijstToe(tafels);
        tekenaar.voegObjectToe(pacman);
        tekenaar.voegObjectToe(legoYoda);
        tekenaar.voegObjectToe(klok);
        tekenaar.voegLijstToe(muren);
        tekenaar.voegLijstToe(levens);
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
        beweeg.voegLijstToe(jochems);
        beweeg.voegObjectToe(pacman);
        beweeg.voegObjectToe(legoYoda);
        beweeg.voegObjectToe(klok);
    }

}
