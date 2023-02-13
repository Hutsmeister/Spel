//author Demi

package Spel;
import Tools.*;
import java.awt.Image;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class BestuurbaarDing extends BotsObject
{
    public Image plaatje;
    public Omgeving omgeving;
    public int links, rechts, boven, beneden, levens;
    public double a;
    public boolean heeftSleutel;
    public int gesprongen;

    //public Geluid g1, g2;

    //Toevoegen voor toestanden
    public String[] toestandsverzameling;
    public String[] eindtoestandsverzameling;
    public String begintoestand, huidigetoestand;
    public ArrayList<Image> plaatjes;

    public String[] invoerverzameling;

    public BestuurbaarDing(int x, int y, float v, boolean heeftSleutel, int levens, Image plaatje){
        super(x, y, 30, 30);
        vx = v;
        vy = v;
        this.plaatje = plaatje;
        a = 20;
        this.heeftSleutel = heeftSleutel;
        this.levens = levens; 
        gesprongen = 0;
        //g1 = new Geluid("geluiden/pickupkey.wav");
        //g2 = new Geluid("geluiden/damage.wav");

        definitieToestanden();
        huidigetoestand = begintoestand;
        laadplaatjes();
        definitieInvoer();
        this.plaatje = plaatjes.get(0);
    }

    private void laadplaatjes(){
        plaatjes = new ArrayList<Image>();
        plaatjes.add(Laden.laadPlaatje("plaatjes/pacman.png")); // 0
        //plaatjes.add(Laden.laadPlaatje(""))                   // 1
    }

    private void definitieToestanden(){
        toestandsverzameling = new String[] {"stil", "links" , "rechts" , "val" , "buk" , "spring"};
        begintoestand = toestandsverzameling[0];
        eindtoestandsverzameling = new String[] {toestandsverzameling[0]};
    }

    private void definitieInvoer(){
        invoerverzameling = new String[] {"li" , "re" , "nli" , "nre" , "gv" , "bmv" , "up" , "uplos"};        
    }

    public void register(Omgeving o){
        omgeving = o;
    }

    public void defineKeys(int links, int rechts, int boven, int beneden){
        this.links = links;
        this.rechts = rechts;
        this.boven = boven;
        this.beneden = beneden;
    }

    private boolean isVloer(float stap){
        boolean antwoord = false;
        int teller = 0;
        vy += a;
        y += vy*stap;
        while(teller < omgeving.vloeren.size()){
            Obstakel vl = omgeving.vloeren.get(teller);
            if(botstMet(vl)){
                antwoord = true;
                zetyTerug();
                vy = 0;
            }
        }
        return antwoord;
    }
    
    public void beweeg(float stap){
        if(levens!=0){
            bewaar();
        }
        if(levens != 0){
            String input = "";
            if(huidigetoestand.equals(toestandsverzameling[0])){ // stil
                plaatje = plaatjes.get(0);
                if(omgeving.kb.isIngedrukt(links)){
                    input = invoerverzameling[0]; //input 1:
                    huidigetoestand = toestandsverzameling[1]; // toestand Tinks
                    plaatje = plaatjes.get(1);
                    x -= vx*stap; // actie
                }
                if(omgeving.kb.isIngedrukt(rechts)){
                    input = invoerverzameling[1]; //input re
                    huidigetoestand = toestandsverzameling[2]; // toestend rechts
                    plaatje = plaatjes.get(2);
                    x += vx*stap;
                }
                // if(omgeving.kb.isIngedrukt(buk)) {
                    // input = invoerverzameling[6]; //input up knop
                    // huidigetoestand = toestandsverzameling[4]; // toestand rechts
                    // plaatje = plaatjes.get(4);
                    // // geen actie nodig
                // }
                if(!isVloer(stap)){
                    input = invoerverzameling[4]; //input yn
                    huidigetoestand = toestandsverzameling[3]; // toestand val
                    plaatje = plaatjes.get(3);
                    // De actie al uitgevoerd
                }
            } else if(huidigetoestand.equals(toestandsverzameling[3])){
                plaatje = plaatjes.get(3);
                if(isVloer(stap)){
                    input = invoerverzameling[5];
                    huidigetoestand = toestandsverzameling[0];
                    plaatjes.get(0);
                }
                
            } else if(huidigetoestand.equals(toestandsverzameling[4])){
                plaatje = plaatjes.get(4);
                if(isVloer(stap)){
                    input = invoerverzameling[5];
                    huidigetoestand = toestandsverzameling[5];
                    vy = -600;
                }
            }else if(huidigetoestand.equals(toestandsverzameling[5])){
                plaatje = plaatjes.get(5);
                if(isVloer(stap)){
                    input = invoerverzameling[4];
                    huidigetoestand = toestandsverzameling[3];
                }
            }
            //botsen met sleutel
            // Sleutel sleutel; 
            if(levens != 0){
                if(botstMet(omgeving.sleutel)){
                    omgeving.tekenaar.verwijderObject(omgeving.sleutel);
                    omgeving.tekenaar.voegObjectToe(omgeving.sleutelOpgepakt);
                    omgeving.sleutel.isOpgepakt = true;
                    heeftSleutel = true;
                }
            }

            int teller = 0;
            int totaleLevens = omgeving.pacman.levens; //+ omgeving.legoYoda.levens;

            if(totaleLevens >= 1){
                while(teller < omgeving.jochems.size()){
                    Jochem j = omgeving.jochems.get(teller);
                    if(omgeving.pacman.levens != 0){
                        if(omgeving.pacman.botstMet(j)){
                            omgeving.tekenaar.verwijderObject(omgeving.pacmanLevens.get(omgeving.pacman.levens-1));
                            omgeving.pacmanLevens.remove(omgeving.pacman.levens-1);
                            omgeving.pacman.levens --;
                            //g2.speelaf();

                            if(omgeving.pacman.levens == 0){
                                //omgeving.tekenaar.verwijderObject(omgeving.pacman);
                                //omgeving.beweeg.verwijderObject(omgeving.pacman);
                                //omgeving.bestuurbareDingen.remove(omgeving.pacman);
                                omgeving.pacman.x = -100;
                                omgeving.pacman.y = -100;
                                omgeving.pacmanLevens.clear();
                            }
                        }
                    }

                    // if(omgeving.legoYoda.levens != 0){
                    // if(omgeving.legoYoda.botstMet(j)){
                    // omgeving.tekenaar.verwijderObject(omgeving.legoYodaLevens.get(omgeving.legoYoda.levens-1));
                    // omgeving.legoYodaLevens.remove(omgeving.legoYoda.levens-1);
                    // omgeving.legoYoda.levens --;
                    // //g2.speelaf();
                    // if(omgeving.legoYoda.levens == 0){
                    // //omgeving.tekenaar.verwijderObject(omgeving.legoYoda);
                    // //omgeving.beweeg.verwijderObject(omgeving.legoYoda);
                    // //omgeving.bestuurbareDingen.remove(omgeving.legoYoda);
                    // omgeving.legoYoda.x = -100;
                    // omgeving.legoYoda.y = -200;
                    // omgeving.legoYodaLevens.clear();
                    // }
                    // }
                    //}
                    teller++;
                }
            }

            if(totaleLevens >= 1){

                while(teller < omgeving.specialeBeweegObjecten.size()){
                    BewegendDingExtra be = omgeving.specialeBeweegObjecten.get(teller);

                    if(omgeving.pacman.levens != 0){
                        if(omgeving.pacman.botstMet(be)){
                            omgeving.specialeBeweegObjecten.remove(be);
                            omgeving.tekenaar.verwijderObject(be);
                            omgeving.beweeg.verwijderObject(be);
                            omgeving.tekenaar.verwijderObject(omgeving.pacmanLevens.get(omgeving.pacman.levens-1));
                            omgeving.pacmanLevens.remove(omgeving.pacman.levens-1);
                            omgeving.pacman.levens --;
                            //g2.speelaf();

                            if(omgeving.pacman.levens == 0){
                                //omgeving.tekenaar.verwijderObject(omgeving.pacman);
                                //omgeving.beweeg.verwijderObject(omgeving.pacman);
                                //omgeving.bestuurbareDingen.remove(omgeving.pacman);
                                omgeving.pacman.x = -100;
                                omgeving.pacman.y = -100;
                                omgeving.pacmanLevens.clear();
                                omgeving.dood ++;
                            }

                            //                    }else{
                            //teller++;

                        }
                    }

                    // if(omgeving.legoYoda.levens != 0){
                    // if(omgeving.legoYoda.botstMet(be)){
                    // omgeving.specialeBeweegObjecten.remove(be);
                    // omgeving.tekenaar.verwijderObject(be);
                    // omgeving.beweeg.verwijderObject(be);
                    // omgeving.tekenaar.verwijderObject(omgeving.legoYodaLevens.get(omgeving.legoYoda.levens-1));
                    // omgeving.legoYodaLevens.remove(omgeving.legoYoda.levens-1);
                    // omgeving.legoYoda.levens --;
                    // //g2.speelaf();
                    // if(omgeving.legoYoda.levens == 0){
                    // //omgeving.tekenaar.verwijderObject(omgeving.legoYoda);
                    // //omgeving.beweeg.verwijderObject(omgeving.legoYoda);
                    // //omgeving.bestuurbareDingen.remove(omgeving.legoYoda);
                    // omgeving.legoYoda.x = -100;
                    // omgeving.legoYoda.y = -200;
                    // omgeving.legoYodaLevens.clear();
                    // omgeving.dood ++;
                    // }

                    // //                    }else{
                    // //                        teller++;

                    // }
                    // }

                    teller++;

                }
            }

            if(omgeving.dood == 2 || totaleLevens == 0){
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

                omgeving.tekenaar.voegObjectToe(omgeving.gameOver);
            }

            /*
            teller = 0;
            while(teller < omgeving.obstakels.size()){
            Obstakel o = omgeving.obstakels.get(teller);
            if(botstMet(o)){
            if(ikKomVan(o).equals("links")|| ikKomVan(o).equals("rechts")){
            zetxTerug();
            }
            if(ikKomVan(o).equals("boven")|| ikKomVan(o).equals("beneden")){
            zetyTerug();
            vy = 0;
            }
            }
            teller ++;
            }
             */

            teller = 0;
            if(levens != 0){
                while(teller < omgeving.vloeren.size()){
                    Obstakel o = omgeving.vloeren.get(teller);
                    if(botstMet(o)){
                        if(ikKomVan(o).equals("links")|| ikKomVan(o).equals("rechts")){
                            zetxTerug();
                        }
                        if(ikKomVan(o).equals("boven")){
                            zetyTerug();
                            vy = 0;
                            gesprongen = 0;
                        }
                        if(ikKomVan(o).equals("beneden")){
                            zetyTerug();
                            vy = -vy;
                        }
                    }
                    teller ++;
                }
            }

            teller = 0;
            if(levens != 0){
                while(teller < omgeving.muren.size()){
                    Obstakel o = omgeving.muren.get(teller);
                    if(botstMet(o)){
                        if(ikKomVan(o).equals("links")|| ikKomVan(o).equals("rechts")){
                            zetxTerug();
                            //vx = 0;
                        }
                        if(ikKomVan(o).equals("boven")){
                            zetyTerug();
                            vy = 0;
                            gesprongen = 0;
                        }
                        if(ikKomVan(o).equals("beneden")){
                            zetyTerug();
                            vy = -vy;
                        }
                    }
                    teller ++;
                }
            }

            //Stijn bevrijden
            if(levens != 0){
                if(botstMet(omgeving.stijn)){
                    if(ikKomVan(omgeving.stijn).equals("links")|| ikKomVan(omgeving.stijn).equals("rechts")){
                        zetxTerug();
                        omgeving.stijn.isGered = true;
                    }
                    if(ikKomVan(omgeving.stijn).equals("boven")|| ikKomVan(omgeving.stijn).equals("beneden")){
                        zetyTerug();
                        vy = 0;
                        gesprongen = 0;
                        omgeving.stijn.isGered = true;
                    }
                }
            }

            //Stijn bevrijden

            if(levens != 0){

                if(botstMet(omgeving.stijn) ){
                    if(ikKomVan(omgeving.stijn).equals("links")|| ikKomVan(omgeving.stijn).equals("rechts")){
                        zetxTerug();
                        omgeving.stijn.isGered = true;
                    }
                    if(ikKomVan(omgeving.stijn).equals("boven")|| ikKomVan(omgeving.stijn).equals("beneden")){
                        zetyTerug();
                        vy = 0;
                        gesprongen = 0;
                        omgeving.stijn.isGered = true;
                    }
                }

            }
            if(heeftSleutel == true){
                if(omgeving.stijn.isGered == true){
                    omgeving.tekenaar.voegObjectToe(omgeving.win);
                }
            }

            /*
            teller = 0;
            while(teller < omgeving.BeweegObjecten.size()){
            BewegendDing bb = omgeving.BeweegObjecten.get(teller);
            if(botstMet(bb)){
            if(ikKomVan(bb).equals("links")){
            bb.x = x + breedte;
            }
            if(ikKomVan(bb).equals("rechts")){
            bb.x = x - breedte;
            }
            if(ikKomVan(bb).equals("boven") || ikKomVan(bb).equals("beneden")){
            zetyTerug();
            vy = 0;
            }
            }
            teller ++;
            }
             */

        }
    }

    public void teken(Graphics2D g){
        g.drawImage(plaatje, (int)x, (int)y, breedte, hoogte, null);
    }
}
