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
    public Geluid g1;
    
    
    public BestuurbaarDing(int x, int y, float v, Image plaatje, boolean heeftSleutel, int levens){
        super(x, y, 30, 30);
        vx = v;
        vy = v;
        this.plaatje = plaatje;
        a = 20;
        this.heeftSleutel = heeftSleutel;
        this.levens = levens; 
        gesprongen = 0;
        g1 = new Geluid("geluiden/pickupkey.wav");
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
        bewaar();
        
        if(omgeving.kb.isIngedrukt(links)){
            x -= vx * stap;
        }
        if(omgeving.kb.isIngedrukt(rechts)){
            x += vx * stap;
        }
        if(omgeving.kb.isIngedrukt(boven) && gesprongen == 0){
            vy = -800;
            if(omgeving.kb.isIngedrukt(boven) ){
                vy = -800;
                gesprongen = 1;
            }
        }
        if(omgeving.kb.isIngedrukt(beneden)){
            //y += vy * stap;
        } 
        
        vy += a;
        y += vy * stap;
        
        //botsen met sleutel
        Sleutel sleutel; 
        if(botstMet(omgeving.sleutel)){
           g1.speelaf();
           omgeving.tekenaar.verwijderObject(omgeving.sleutel);
           heeftSleutel = true;
           }
        
            
       
        
        int teller = 0;
        
        while(teller < omgeving.specialeBeweegObjecten.size()){
            BewegendDingExtra be = omgeving.specialeBeweegObjecten.get(teller);
            if(omgeving.pacman.botstMet(be)){
                omgeving.specialeBeweegObjecten.remove(be);
                omgeving.tekenaar.verwijderObject(be);
                omgeving.beweeg.verwijderObject(be);
                omgeving.tekenaar.verwijderObject(omgeving.pacmanLevens.get(omgeving.pacman.levens));
                omgeving.pacmanLevens.remove(omgeving.pacman.levens);
                omgeving.pacman.levens --;
            }else{
                teller++;
            }
        }
        
        while(teller < omgeving.specialeBeweegObjecten.size()){
            BewegendDingExtra b = omgeving.specialeBeweegObjecten.get(teller);
            if(omgeving.legoYoda.botstMet(b)){
                omgeving.specialeBeweegObjecten.remove(b);
                omgeving.tekenaar.verwijderObject(b);
                omgeving.beweeg.verwijderObject(b);
                omgeving.tekenaar.verwijderObject(omgeving.legoYodaLevens.get(omgeving.pacman.levens));
                omgeving.legoYodaLevens.remove(omgeving.legoYoda.levens);
                omgeving.legoYoda.levens --;
            }else{
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
        
        teller = 0;
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
