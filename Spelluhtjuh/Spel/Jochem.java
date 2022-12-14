//author Felix

package Spel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;


public class Jochem extends BewegendDing{
    public int max;
    public int xmax = 664;
    public int ymax = 466;
    public int xmin = 0;
    public int ymin = 0;
    public Image plaatje;
    public int minv;
    public int getalletje;
    public Jochem(int x, int y, float vx, int max, Image plaatje){
        super(x, y, 60, 60, vx, 0, null);
        this.max = max;
        this.plaatje = plaatje;
    }
     public int maakGetal(int min, int max){
        return (int)(Math.random()*(max - min) + min);
    }
    public void beweeg(float stap){
        //super.beweeg(stap);
        x += vx*stap;
        y += vy*stap;
        minv = 200;
        
        if(x < xmin){
            getalletje = maakGetal(0,1);
            if(getalletje == 0){
              vx = maakGetal(0, 200);
              vy = 200 - java.lang.Math.abs(vx);
            }
            if(getalletje == 1){
              vy = maakGetal(0, 200);
              vx = 200 - java.lang.Math.abs(vy);
            }
        }
        if(y < ymin){
            getalletje = maakGetal(0,1);
            if(getalletje == 0){
              vx = maakGetal(0,200);
              vy = 200 - java.lang.Math.abs(vx);
            }
            if(getalletje == 1){
              vy = maakGetal(0,200);
              vx = 200 - java.lang.Math.abs(vy);
            }
        }
        if(x > xmax){
            getalletje = maakGetal(0,1);
            if(getalletje == 0){
              vx = maakGetal(-200,0);
              vy = -200 + java.lang.Math.abs(vx);
            }
            if(getalletje == 1){
              vy = maakGetal(-200,0);
              vx = -200 + java.lang.Math.abs(vy);
            }
        }
        if(y > ymax){
            getalletje = maakGetal(0,1);
            if(getalletje == 0){
              vx = maakGetal(-200,0);
              vy = -200 + java.lang.Math.abs(vx);
            }
            if(getalletje == 1){
              vy = maakGetal(-200,0);
              vx = -200 + java.lang.Math.abs(vy);
            }
        }
       
    }
    
    public void teken(Graphics2D g){
        g.drawImage(plaatje, (int)x, (int)y, breedte, hoogte, null);
    }
}

