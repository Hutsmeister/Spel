//author Felix
package Spel;
import java.awt.Graphics2D;
import java.awt.Image;

public class Sleutel extends SpelObject{
  Image plaatje;
  public boolean isOpgepakt;

  public Sleutel(int x, int y, Image plaatje){
    super(x,y, 30, 30);
    isOpgepakt = false;
    this.plaatje = plaatje;
  }
  public void teken(Graphics2D g){
    g.drawImage(plaatje, (int)x, (int)y, breedte, hoogte, null);
  }
}
