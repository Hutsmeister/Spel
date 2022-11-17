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
    public int links, rechts, boven, beneden;
    public double a;
    
    public BestuurbaarDing(int x, int y, float v, Image plaatje){
        super(x, y, 30, 30);
        vx = v;
        vy = v;
        this.plaatje = plaatje;
        a = 11;
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
        if(omgeving.kb.isIngedrukt(boven)){
            y -= vy * stap;
            //vy = -300;
        }
        if(omgeving.kb.isIngedrukt(beneden)){
            y += vy * stap;
        } 
        
        vy += a;
        y += vy * stap;
        
        
       //botsen met sleutel
       Sleutel sleutel; 
       if(botstMet(sleutel)){
            omgeving.tekenaar.verwijderObject(sleutel);
            isOpgepakt = true;
            }
            
       
        /*
        int teller = 0;
        while(teller < omgeving.SpecialeBeweegObjecten.size()){
            BewegendDingExtra be = omgeving.SpecialeBeweegObjecten.get(teller);
            if(botstMet(be)){
                omgeving.SpecialeBeweegObjecten.remove(be);
                omgeving.tekenaar.verwijderObject(be);
                omgeving.beweeg.verwijderObject(be);
            }else{
                teller++; 
            }
        }
        
        
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
                }
                if(ikKomVan(o).equals("boven")|| ikKomVan(o).equals("beneden")){
                    zetyTerug();
                    vy = 0;
                }
                
            }
            teller ++;
        }
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
