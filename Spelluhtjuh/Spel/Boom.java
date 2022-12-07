//author Felix
package Spel;
import java.awt.Graphics2D;
import java.awt.Image;

public class Boom extends SpelObject{
  Image plaatje;


  public Boom(int x, int y, int breedte, int hoogte, Image plaatje){
    super(x,y, breedte, hoogte);
    this.plaatje = plaatje;
  }
  public void teken(Graphics2D g){
    g.drawImage(plaatje, (int)x, (int)y, breedte, hoogte, null);
  }
}
