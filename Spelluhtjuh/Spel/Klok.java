//author Demi
package Spel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Klok extends BeweegObject{
    public String melding;
    public boolean status, tijdIsOm;
    public int snelheid, alarmtijd, verlopenTijd;
    public long vorigeTijd;
    
    public Klok(int x, int y, int breedte, int hoogte, int alarmtijd){
        super(x,y,breedte,hoogte);
        status = true;
        snelheid = 1000000000;
        this.alarmtijd = alarmtijd;
        tijdIsOm = false;
        verlopenTijd = 0;
        vorigeTijd = System.nanoTime();
        melding = "Gestopt";
    }
    
    public void beweeg(float s){
        loopt();
    }
    
    public void loopt(){
        long now = System.nanoTime();
        if(status){
            if(now-vorigeTijd >= snelheid){
                verlopenTijd ++;
                melding = "Time "+ verlopenTijd;
                vorigeTijd = now;
            }
            if(verlopenTijd >= alarmtijd){
                status = false;
                tijdIsOm = true;
                melding = "Time "+ verlopenTijd;
            }
        }
    }
    
        public void teken(Graphics2D g){
        g.setColor(Color.WHITE);
        g.fillRect((int)x, (int)y, breedte, hoogte);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        g.setColor(Color.BLACK);
        g.drawString(melding, x+5, y+26);
    }
}
