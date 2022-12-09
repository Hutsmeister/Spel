//author Demi

// BOTSMET

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

    public BestuurbaarDing(int x, int y, float v, Image plaatje, boolean heeftSleutel, int levens){
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

    public void beweeg(float stap){
        if(levens!=0){
            bewaar();
        }
        if(levens != 0){
            if(omgeving.kb.isIngedrukt(links)){
                x -= vx * stap;
            }
            if(omgeving.kb.isIngedrukt(rechts)){
                x += vx * stap;
            }
            if(vy == 0){
                if(omgeving.kb.isIngedrukt(boven) && gesprongen == 0){
                    vy = -800;
                    gesprongen = 1;
                }
            }
            if(omgeving.kb.isIngedrukt(beneden)){
                //y += vy * stap;
            } 

            vy += a;
            y += vy * stap;
        }
        //botsen met sleutel
        Sleutel sleutel; 
        if(levens != 0){
            if(botstMet(omgeving.sleutel)){
                omgeving.tekenaar.verwijderObject(omgeving.sleutel);
                heeftSleutel = true;
            }
        }

        int teller = 0;
        int totaleLevens = omgeving.pacman.levens + omgeving.legoYoda.levens;

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
                        }

                        //                    }else{
                        //teller++;

                    }
                }

                if(omgeving.legoYoda.levens != 0){
                    if(omgeving.legoYoda.botstMet(be)){
                        omgeving.specialeBeweegObjecten.remove(be);
                        omgeving.tekenaar.verwijderObject(be);
                        omgeving.beweeg.verwijderObject(be);
                        omgeving.tekenaar.verwijderObject(omgeving.legoYodaLevens.get(omgeving.legoYoda.levens-1));
                        omgeving.legoYodaLevens.remove(omgeving.legoYoda.levens-1);
                        omgeving.legoYoda.levens --;
                        //g2.speelaf();
                        if(omgeving.legoYoda.levens == 0){
                            //omgeving.tekenaar.verwijderObject(omgeving.legoYoda);
                            //omgeving.beweeg.verwijderObject(omgeving.legoYoda);
                            //omgeving.bestuurbareDingen.remove(omgeving.legoYoda);
                            omgeving.legoYoda.x = -100;
                            omgeving.legoYoda.y = -200;
                            omgeving.legoYodaLevens.clear();
                        }

                        //                    }else{
                        //                        teller++;

                    }
                }

                teller++;

            }
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
                    if(ikKomVan(o).equals("boven")|| ikKomVan(o).equals("beneden")){
                        zetyTerug();
                        vy = 0;
                        gesprongen = 0;
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
                    if(ikKomVan(o).equals("boven")|| ikKomVan(o).equals("beneden")){
                        zetyTerug();
                        vy = 0;
                        gesprongen = 0;
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

    public void teken(Graphics2D g){
        g.drawImage(plaatje, (int)x, (int)y, breedte, hoogte, null);
    }
}
