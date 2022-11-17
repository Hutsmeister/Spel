//author Felix
package Spel;
import java.awt.Graphics2D;
import java.awt.Image;

public class Sleutel extends SpelObject{
  Image plaatje;
  public boolean isOpgepakt;
  public Sleutel(int x, int y, int breedte, int hoogte){
    super(x,y,30, 30);
    isOpgepakt= false;
    plaatje = Laden.laadPlaatje("plaatjes/klaslokaal.jpg");
  }

}
