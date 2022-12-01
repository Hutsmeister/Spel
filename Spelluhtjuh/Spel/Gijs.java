package Spel;
import java.awt.Graphics2D;
import java.awt.Image;

public class Gijs extends SpelObject{
  Image plaatje;

  public Gijs(int x, int y, Image plaatje){
    super(x,y, 131, 160);
    this.plaatje = plaatje;
  }
  public void teken(Graphics2D g){
    g.drawImage(plaatje, (int)x, (int)y, breedte, hoogte, null);
  }
}
