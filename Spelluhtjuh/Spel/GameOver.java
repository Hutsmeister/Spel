package Spel;
import java.awt.Graphics2D;
import java.awt.Image;


public class GameOver extends SpelObject{
    Image plaatje;
    public GameOver(int breedte, int hoogte, Image plaatje){
        super(0, 0, breedte, hoogte);
        this.plaatje = plaatje;
    }
    public void teken(Graphics2D g){
        g.drawImage(plaatje, (int)x, (int)y, breedte, hoogte, null);
    }
}
