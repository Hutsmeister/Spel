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
    public int dood = 0;
    /**
     * Declareer hieronder alle objecten die je in je spel nodig hebt
     * Bijvoorbeeld:
     * public Ding d;
     * public ArrayList<AnderDing> andereDingen
     * enz.
     */
    
    //public Controller controller;
    
    public Sleutel sleutel;
    public Sleutel sleutelOpgepakt;
    public Win win;
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
    public ArrayList<Lift> liften;
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
        
        //controller maken die zorgt voor toestandsveranderingen
        //controller = new Controller(this);
        //beweeg.voegObjectToe(controller);
        
        // geef hier de opdracthen om de methoden uit te voeren
        // die de objecten maken.
        klok = new Klok(910, 64, 100, 50, 900);
        
        gameOver = new GameOver(breedte, hoogte, Laden.laadPlaatje("plaatjes/gameOver.jpg"));

        win = new Win(breedte, hoogte, Laden.laadPlaatje("plaatjes/win.jpg"));
        
        // maakSpeler();
        
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
        maakLiften();
        maakTafels();
        maakBestuurbareDingen();
        maakMuren();
        maakLevens();
        voegAanTekenaarToe();
        voegAanBeweegToe();
        //eindeSpel();
        // achtergrondGeluid = new Geluid("geluiden/Mellohi.wav");
        // achtergrondGeluid.speelInHerhaling();
    }
    // public void maakSpeler(){
        // speler = new BestuurbaarDing(200, 200, 500, 500, 0, Laden.laadPlaatje("plaatjes/speler0.png"));
        // speler.registreer(this);
        // speler.defineerToetsen(KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_UP, KeyEvent.VK_DOWN);
        // tekenaar.voegObjectToe(speler);
        // beweeg.voegObjectToe(speler);
    // }
    // public void veranderSpeler(int level){
        // speler.plaatje = Laden.laadPlaatje("plaatjes/speler"+level+".png");
        // tekenaar.verwijderObject(speler);
        // tekenaar.voegObjectToe(speler);
        // if (level == 0){
            // speler.x = 200;
            // speler.y = 200;
            // speler.breedte = 500;
            // speler.hoogte = 500;
            // speler.vx = 0;
            // speler.vy = 0;
        // } else if (level == 1){
            // speler.x = 200;
            // speler.y = 200;
            // speler.breedte = 50;
            // speler.hoogte = 100;
            // speler.vx = 50;
            // speler.vy = 50;
            
        // } else if (level == 2){
            // speler.x = 300;
            // speler.y = 300;
            // speler.breedte = 50;
            // speler.hoogte = 100;
            // speler.vx = 50;
            // speler.vy = 50;
        // }else if (level == 3){
            // speler.x = 100;
            // speler.y = 100;
            // speler.breedte = 500;
            // speler.hoogte = 100;
            // speler.vx = 50;
            // speler.vy = 50;
        // }
    // }
    // public void maakObstakels(int level){
        // if(obstakels == null){
            // obstakels = new ArrayList<Obstakel>();
        // } else {
            // tekenaar.verwijderLijst(obstakels);
            // obstakels.clear();
        // }
        // Image p = Laden.laadPlaatje("plaatjes/obstakel"+level+".png");
        // String[] regels = Laden.laadTextFile("omgevingen/obstakels"+level+".txt").split("\n");
        // int teller = 0;
        // while(teller < regels.length){
            // int x = Integer.parseInt(regels[teller].split(",")[0].replaceAll(" ", ""));
            // int y = Integer.parseInt(regels[teller].split(",")[1].replaceAll(" ", ""));
            // int b = Integer.parseInt(regels[teller].split(",")[2].replaceAll(" ", ""));
            // int h = Integer.parseInt(regels[teller].split(",")[3].replaceAll(" ", ""));
            // obstakels.add(new Obstakel(x,y,b,h,p));
            // teller ++; 
        // }
        // tekenaar.voegLijstToe(obstakels);
    // }
    // public void maakMuren(int level){
        // if(muren == null){
            // muren = new ArrayList<Obstakel>();
        // } else {
            // tekenaar.verwijderLijst(muren);
            // muren.clear();
        // }
        // Image p = Laden.laadPlaatje("plaatjes/muur.png");
        // String[] regels = Laden.laadTextFile("omgevingen/muren"+level+".txt").split("\n");
        // int teller = 0;
        // while(teller < regels.length){
            // int x = Integer.parseInt(regels[teller].split(",")[0].replaceAll(" ", ""));
            // int y = Integer.parseInt(regels[teller].split(",")[1].replaceAll(" ", ""));
            // int b = Integer.parseInt(regels[teller].split(",")[2].replaceAll(" ", ""));
            // int h = Integer.parseInt(regels[teller].split(",")[3].replaceAll(" ", ""));
            // muren.add(new Obstakel(x,y,b,h,p));
            // teller ++; 
        // }
        // tekenaar.voegLijstToe(muren);
    // }
    // public void maakVloeren(int level){
        // if(vloeren == null){
            // vloeren = new ArrayList<Obstakel>();
        // } else {
            // tekenaar.verwijderLijst(vloeren);
            // vloeren.clear();
        // }
        // Image p = Laden.laadPlaatje("plaatjes/vloer.jpg");
        // String [] regels = Laden.laadTextFile("omgevingen/vloeren"+level+".txt").split("\n");
        // int teller = 0;
        // while(teller < regels.length){
            // int x = Integer.parseInt(regels[teller].split(",")[0].replaceAll(" ", ""));
            // int y = Integer.parseInt(regels[teller].split(",")[1].replaceAll(" ", ""));
            // int b = Integer.parseInt(regels[teller].split(",")[2].replaceAll(" ", ""));
            // vloeren.add(new Obstakel(x,y,b,10,p)); 
            // teller ++;
        // }
    // }
    // public void maakVijanden(int level){
        // if(vijanden == null){
            // vijanden = new ArrayList<Vijand>();
        // } else {
            // tekenaar.verwijderLijst(vijanden);
            // beweeg.verwijderLijst(vijanden);
            // vijanden.clear();
        // }
        // Image p = Laden.laadPlaatje("plaatjes/vijand"+level+".png");
        // String [] regels = Laden.laadTextFile("omgevingen/vijanden"+level+".txt").split("\n");
        // int teller = 0;
        // while(teller < regels.length){
            // int x = Integer.parseInt(regels[teller].split(",")[0].replaceAll(" ", ""));
            // int y = Integer.parseInt(regels[teller].split(",")[1].replaceAll(" ", ""));
            // int b = Integer.parseInt(regels[teller].split(",")[2].replaceAll(" ", ""));
            // int h = Integer.parseInt(regels[teller].split(",")[3].replaceAll(" ", ""));
            // int vx = Integer.parseInt(regels[teller].split(",")[4].replaceAll(" ", ""));
            // int vy = Integer.parseInt(regels[teller].split(",")[5].replaceAll(" ", ""));
            // Vijand v = new Vijand(x,y,b,h,vx,vy,p);
            // v.registreer(this);
            // vijanden.add(v);
            // teller ++;
        // }
    // }
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
    public void maakLiften(){
        liften = new ArrayList<Lift>();
        liften.add(new Lift(700, 0, 450, 100, 100, 0, 204, 255, this));
        liften.add(new Lift(500, 0, 450, 100, 100, 0, 204, 255, this));
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
        //legoYodaHart = new Hart(80, 60, 30, 30, p); // icon legoYoda

        int teller = pacman.levens;
        while(teller > 0){
            pacmanLevens.add(new Hart(320 - 20 * teller, 20, 30, 30, h));
            teller --;
        }
        // teller = legoYoda.levens;
        // while(teller > 0){
            // legoYodaLevens.add(new Hart(320 - 20 * teller, 60, 30, 30, h));
            // teller --;
        // }

    }

    
    public void maakBestuurbareDingen(){
        Image p = Laden.laadPlaatje("plaatjes/pacman.png");
        Image y = Laden.laadPlaatje("plaatjes/legoYoda.png");
        bestuurbareDingen = new ArrayList<BestuurbaarDing>();
        
        pacman = new BestuurbaarDing(100, 100, 200, false, 10, p);
        pacman.register(this);
        pacman.defineKeys(KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_UP, KeyEvent.VK_DOWN);
        
        // legoYoda = new BestuurbaarDing(200, 100, 250, false, 10, y); 
        // legoYoda.register(this);
        // legoYoda.defineKeys(KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W, KeyEvent.VK_S);
        // bestuurbareDingen.add(pacman);
        // bestuurbareDingen.add(legoYoda);
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
        while(teller < 5){
            specialeBeweegObjecten.add(new BewegendDingExtra(maakGetal(100, 1800), maakGetal(890, 930), maakGetal(200, 500), breedte, p));
            teller ++;
        }

    }
    
    public void maakJochems(){
        jochems = new ArrayList<Jochem>();
        Image j = Laden.laadPlaatje("plaatjes/vliegende jochem.png");
        jochems.add(new Jochem(maakGetal(1, 663), maakGetal(1, 465), 200 , breedte, j));
        jochems.add(new Jochem(maakGetal(1, 663), maakGetal(1, 465), 200 , breedte, j));
        jochems.add(new Jochem(maakGetal(1, 663), maakGetal(1, 465), 200, breedte, j));
        jochems.add(new Jochem(maakGetal(1, 663), maakGetal(1, 465), 200, breedte, j));
        jochems.add(new Jochem(maakGetal(1, 663), maakGetal(1, 465), 200, breedte, j));
    }

    public int maakGetal(int min, int max){
        return (int)(Math.random()*(max - min) + min);
    }
    
    //sleutel maken
    public void maakSleutel(){
        Image s = Laden.laadPlaatje("plaatjes/sleutel.png");
        sleutel = new Sleutel(0, 500, s);
        sleutelOpgepakt = new Sleutel(20, 20, s);
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
        vloeren.add(new Obstakel(9,978, 152, 12,Color.black));
        vloeren.add(new Obstakel(313,978, 152, 12,Color.black));
        vloeren.add(new Obstakel(629,978, 152, 12,Color.black));
        vloeren.add(new Obstakel(945,978, 152, 12,Color.black));
        vloeren.add(new Obstakel(1261,978, 152, 12,Color.black));
        vloeren.add(new Obstakel(1577,978, 152, 12,Color.black));
        vloeren.add(new Obstakel(1893,978, 152, 12,Color.black));
        vloeren.add(new Obstakel(1640,173, 480, 43,Color.black));
        vloeren.add(new Obstakel(1440,300, 680, 43,Color.black));
        
        
        
        vloeren.add(new Obstakel(0,800, 200, 15,Color.black));
        vloeren.add(new Obstakel(250,600, 100, 15,Color.black));
        vloeren.add(new Obstakel(500,500, 1950, 15,Color.black));
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
        int totaleLevens = pacman.levens; //+ legoYoda.levens;
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
        tekenaar.voegLijstToe(liften);
        tekenaar.voegLijstToe(wegen);
        tekenaar.voegLijstToe(specialeBeweegObjecten);
        tekenaar.voegLijstToe(jochems);
        tekenaar.voegLijstToe(blokjes);
        tekenaar.voegLijstToe(tafels);
        tekenaar.voegLijstToe(bomen);
        tekenaar.voegObjectToe(pacman);
        //tekenaar.voegObjectToe(legoYoda);
        tekenaar.voegObjectToe(klok);
        tekenaar.voegLijstToe(muren);
        tekenaar.voegLijstToe(pacmanLevens); 
        tekenaar.voegLijstToe(legoYodaLevens);
        tekenaar.voegObjectToe(pacmanHart);
        //tekenaar.voegObjectToe(legoYodaHart);
        
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
        beweeg.voegLijstToe(liften);
        //beweeg.voegObjectToe(legoYoda);
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
