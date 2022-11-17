//author Felix
package Spel;
import java.awt.Graphics2D;
import java.awt.Image;

public class Sleutel extends SpelObject{
  Image plaatje;
  public boolean isOpgepakt;
  int x;
  int y;
  
  public Sleutel(int x, int y, Image plaatje){
    super(x,y, breedte, hoogte);
    breedte = 30; 
    hoogte = 30;
    this.x = x;
    this.y = y;
    isOpgepakt = false;
    this.plaatje = plaatje;
  }
  public void teken(Graphics2D g){
    g.drawImage(plaatje, (int)x, (int)y, breedte, hoogte, null);
  }
}
