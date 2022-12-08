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
    public GameOver gameOver;
    public BestuurbaarDing pacman, legoYoda;
    public Hart pacmanHart;
    public Hart legoYodaHart;
    // ArrayList<Lift> liften;
    public ArrayList<Tafel> tafels;
    public ArrayList<Stoel> stoelen;
    public ArrayList<Obstakel> vloeren;
    public ArrayList<Boom> bomen;
    public ArrayList<Obstakel> muren;
    public ArrayList<Weg> wegen;
    public ArrayList<Jochem> jochems;
    public ArrayList<Blokje> blokjes;
    public ArrayList<BewegendDing> beweegObjecten;
    public ArrayList<BewegendDingExtra> specialeBeweegObjecten;
    public ArrayList<BestuurbaarDing> bestuurbareDingen;
    public ArrayList<Hart> pacmanLevens; 
    public ArrayList<Hart> legoYodaLevens;
    public Geluid achtergrondGeluid;
    
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
        
        gameOver = new GameOver(breedte, hoogte, Laden.laadPlaatje("plaatjes/gameOver.jpg"));

        
        maakAchtergrond();
        maakSpecialeBeweegObjecten();
        maakJochems();
        maakSleutel();
        maakGijs();
        maakStijn();
        maakBlokjes();
        maakVloeren();
        maakWegen();
        maakBomen();
        maakTafels();
        maakBestuurbareDingen();
        maakMuren();
        maakLevens();
        voegAanTekenaarToe();
        voegAanBeweegToe();
        //eindeSpel();
        achtergrondGeluid = new Geluid("geluiden/Mellohi.wav");
        achtergrondGeluid.speelInHerhaling();
    }
    
      public void maakTafels(){
        tafels = new ArrayList<Tafel>();
        tafels.add(new Tafel(100, 100, 30, 30,Laden.laadPlaatje("plaatjes/tafel.png")));
        tafels.add(new Tafel(200, 100, 30, 30,Laden.laadPlaatje("plaatjes/tafel.png")));
        tafels.add(new Tafel(300, 100, 30, 30,Laden.laadPlaatje("plaatjes/tafel.png")));
        tafels.add(new Tafel(400, 100, 30, 30,Laden.laadPlaatje("plaatjes/tafel.png")));
        tafels.add(new Tafel(500, 100, 30, 30,Laden.laadPlaatje("plaatjes/tafel.png")));
    } 
    public void maakBomen(){
        bomen = new ArrayList<Boom>();
        bomen.add(new Boom(60, 814, 100, 100, Laden.laadPlaatje("plaatjes/boom.png")));
        bomen.add(new Boom(260, 814, 100, 100, Laden.laadPlaatje("plaatjes/boom.png")));
        bomen.add(new Boom(460, 814, 100, 100, Laden.laadPlaatje("plaatjes/boom.png")));
        bomen.add(new Boom(660, 814, 100, 100, Laden.laadPlaatje("plaatjes/boom.png")));
        bomen.add(new Boom(860, 814, 100, 100, Laden.laadPlaatje("plaatjes/boom.png")));
        bomen.add(new Boom(1060, 814, 100, 100, Laden.laadPlaatje("plaatjes/boom.png")));
        bomen.add(new Boom(1260, 814, 100, 100, Laden.laadPlaatje("plaatjes/boom.png")));
        bomen.add(new Boom(1460, 814, 100, 100, Laden.laadPlaatje("plaatjes/boom.png")));
        bomen.add(new Boom(1660, 814, 100, 100, Laden.laadPlaatje("plaatjes/boom.png")));
        bomen.add(new Boom(1860, 814, 100, 100, Laden.laadPlaatje("plaatjes/boom.png")));
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
        legoYodaLevens = new ArrayList<Hart>();
        pacmanLevens = new ArrayList<Hart>();

        pacmanHart = new Hart(80, 20, 30, 30, p); // icon pacman
        legoYodaHart = new Hart(80, 60, 30, 30, y); // icon legoYoda

        int teller = pacman.levens;
        while(teller > 0){
            pacmanLevens.add(new Hart(320 - 20 * teller, 20, 30, 30, h));
            teller --;
        }
        teller = legoYoda.levens;
        while(teller > 0){
            legoYodaLevens.add(new Hart(320 - 20 * teller, 60, 30, 30, h));
            teller --;
        }

    }

    
    public void maakBestuurbareDingen(){
        Image p = Laden.laadPlaatje("plaatjes/pacman.png");
        Image y = Laden.laadPlaatje("plaatjes/legoYoda.png");
        bestuurbareDingen = new ArrayList<BestuurbaarDing>();
        
        pacman = new BestuurbaarDing(100, 100, 200, p, false, 10 );
        pacman.register(this);
        pacman.defineKeys(KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_UP, KeyEvent.VK_DOWN);
        
        legoYoda = new BestuurbaarDing(200, 100, 250, y, false, 10); 
        legoYoda.register(this);
        legoYoda.defineKeys(KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W, KeyEvent.VK_S);
        bestuurbareDingen.add(pacman);
        bestuurbareDingen.add(legoYoda);
    }
    public void maakBlokjes() {
        blokjes = new ArrayList<Blokje>();
        //String[] regels = Laden.laadTextFile("omgevingen/blokjes.txt").split("/n");
       // int teller = 0;
       // while (teller < regels.length) {
        //    int x = Integer.parseInt(regels[teller].split(",")[0].replaceAll(" ", ""));
        //    int y = Integer.parseInt(regels[teller].split(",")[1].replaceAll(" ", ""));

         //   blokjes.add(new Blokje(x,y,152,12,Laden.laadPlaatje("plaatjes/blokje.png")));
       //     teller++;

        //}
        Image b = Laden.laadPlaatje("plaatjes/rechthoek.png");
        blokjes.add(new Blokje(9,978, 152, 12,b));
        blokjes.add(new Blokje(313,978, 152, 12,b));
        blokjes.add(new Blokje(629,978, 152, 12,b));
        blokjes.add(new Blokje(945,978, 152, 12,b));
        blokjes.add(new Blokje(1261,978, 152, 12,b));
        blokjes.add(new Blokje(1577,978, 152, 12,b));
        blokjes.add(new Blokje(1893,978, 152, 12,b));
        blokjes.add(new Blokje(1640,173, 480, 43,b));

    }


    
    //vliegende scooters maken
    public void maakSpecialeBeweegObjecten(){
        specialeBeweegObjecten = new ArrayList<BewegendDingExtra>();
        Image p = Laden.laadPlaatje("plaatjes/scooter.png");
        //specialeBeweegObjecten.add(new BewegendDingExtra(maakGetal(200, 400), maakGetal(1, 1000), maakGetal(-200, 200), breedte, p));
        //specialeBeweegObjecten.add(new BewegendDingExtra(maakGetal(100, 400), maakGetal(200, 600), maakGetal(-200, 200), breedte, p));
        int teller = 0;
        while(teller < 10){
            specialeBeweegObjecten.add(new BewegendDingExtra(maakGetal(100, 1800), maakGetal(890, 930), maakGetal(200, 500), breedte, p));
            teller ++;
        }

    }
    
    public void maakJochems(){
        jochems = new ArrayList<Jochem>();
        Image j = Laden.laadPlaatje("plaatjes/vliegende jochem.png");
        jochems.add(new Jochem(maakGetal(0, 1800), maakGetal(0, 600), maakGetal(100, 300), breedte, j));
        jochems.add(new Jochem(maakGetal(0, 1800), maakGetal(0, 600), maakGetal(100, 300), breedte, j));
        jochems.add(new Jochem(maakGetal(0, 1800), maakGetal(0, 600), maakGetal(100, 300), breedte, j));
        jochems.add(new Jochem(maakGetal(0, 1800), maakGetal(0, 600), maakGetal(100, 300), breedte, j));
        jochems.add(new Jochem(maakGetal(0, 1800), maakGetal(0, 600), maakGetal(100, 300), breedte, j));
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
    
    public void eindeSpel(){
        int totaleLevens = pacman.levens + legoYoda.levens;
        if(totaleLevens == 0){
            //tekenaar.verwijderObject(achtergrond);
            // tekenaar.verwijderObject(sleutel);
            // tekenaar.verwijderObject(gijs);
            // tekenaar.verwijderObject(stijn);
            // tekenaar.verwijderLijst(vloeren);
            // tekenaar.verwijderLijst(wegen);
            // tekenaar.verwijderLijst(specialeBeweegObjecten);
            // tekenaar.verwijderLijst(jochems);
            // tekenaar.verwijderLijst(blokjes);
            // tekenaar.verwijderLijst(tafels);
            // tekenaar.verwijderObject(klok);
            // tekenaar.verwijderLijst(muren);
            // tekenaar.verwijderLijst(pacmanLevens);
            // tekenaar.verwijderLijst(legoYodaLevens);
            // tekenaar.verwijderObject(pacmanHart);
            // tekenaar.verwijderObject(legoYodaHart); 

            // beweeg.verwijderLijst(specialeBeweegObjecten);
            // beweeg.verwijderLijst(jochems);
            // beweeg.verwijderObject(pacman);
            // beweeg.verwijderObject(legoYoda);
            // beweeg.verwijderObject(klok);

            // tekenaar.voegObjectToe(gameOver);
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
        //int totaleLevens = pacman.levens + legoYoda.levens;
        
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
        tekenaar.voegLijstToe(bomen);
        tekenaar.voegObjectToe(pacman);
        tekenaar.voegObjectToe(legoYoda);
        tekenaar.voegObjectToe(klok);
        tekenaar.voegLijstToe(muren);
        tekenaar.voegLijstToe(pacmanLevens); 
        tekenaar.voegLijstToe(legoYodaLevens);
        tekenaar.voegObjectToe(pacmanHart);
        tekenaar.voegObjectToe(legoYodaHart);
        
        // if(totaleLevens == 0){
        // tekenaar.verwijderObject(achtergrond);
        // tekenaar.verwijderObject(sleutel);
        // tekenaar.verwijderObject(gijs);
        // tekenaar.verwijderObject(stijn);
        // tekenaar.verwijderLijst(vloeren);
        // tekenaar.verwijderLijst(wegen);
        // tekenaar.verwijderLijst(specialeBeweegObjecten);
        // tekenaar.verwijderLijst(jochems);
        // tekenaar.verwijderLijst(blokjes);
        // tekenaar.verwijderLijst(tafels);
        // tekenaar.voegLijstToe(bomen);
        // tekenaar.verwijderObject(klok);
        // tekenaar.verwijderLijst(muren);
        // tekenaar.verwijderLijst(pacmanLevens);
        // tekenaar.verwijderLijst(legoYodaLevens);
        // tekenaar.verwijderObject(pacmanHart);
        // tekenaar.verwijderObject(legoYodaHart);

        // tekenaar.voegObjectToe(gameOver);
        // }
    }   
    
    
    /**
     * Deze procedure voegt de gemaakte objecten aan de
     * beweeglijst van Beweeg toe:
     * beweeg.voegObjectToe(een gemaakt object)
     * beweeg.voegLijstToe(een gemaakte en gevulde lijst)
     *
     */

    private void voegAanBeweegToe(){
        //int totaleLevens = pacman.levens + legoYoda.levens;
        
        beweeg.voegLijstToe(specialeBeweegObjecten);
        beweeg.voegLijstToe(jochems);
        beweeg.voegObjectToe(pacman);
        beweeg.voegObjectToe(legoYoda);
        beweeg.voegObjectToe(klok);
        
        // if(totaleLevens == 0){
        // beweeg.verwijderLijst(specialeBeweegObjecten);
        // beweeg.verwijderLijst(jochems);
        // beweeg.verwijderObject(pacman);
        // beweeg.verwijderObject(legoYoda);
        // beweeg.verwijderObject(klok);
        // }
    }

}
