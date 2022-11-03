package Tools;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.awt.Transparency;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

import javax.imageio.ImageIO;

public class Laden{ 
    /**
     * Laad een plaatje.
     * @param bestandsnaam
     * @return plaatje
     */
    public static GraphicsConfiguration config = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
    
    public static Image laadPlaatje(String filename) {
        try {
            BufferedImage image = ImageIO.read( new File(filename) );
            VolatileImage vimage = createVImage(image.getWidth(), image.getHeight(), Transparency.TRANSLUCENT);

            Graphics2D g = null;

            try {
                g = vimage.createGraphics();

                g.setComposite(AlphaComposite.Src);
                g.setColor(Color.black);
                g.clearRect(0, 0, vimage.getWidth(), vimage.getHeight());

                g.drawImage(image,null,0,0);
            } finally { 
                if(g!=null){
                    g.dispose();
                }
            }
            return vimage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static BufferedImage laadBufferedPlaatje(String filename) {
        BufferedImage image = null;
        try{
            image = ImageIO.read( new File(filename) );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    private static VolatileImage createVImage(int width, int height, int transparency) {
        VolatileImage vimage = config.createCompatibleVolatileImage(width, height, transparency);

        if( vimage.validate( config ) == VolatileImage.IMAGE_INCOMPATIBLE ) {
            vimage = createVImage(width, height, transparency);
        }

        return vimage;
    }

    public static String laadTextFile(String filename){
        String tekst = "";
        try {
            File fileObj = new File(filename);
            Scanner fileReader = new Scanner(fileObj);
            while (fileReader.hasNextLine()) {
                tekst += fileReader.nextLine() +"\n";
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            tekst = "An error occurred.";
            e.printStackTrace();
        }
        return tekst;
    }
    
    public static int[] laadLevelFile(String map, String objectnaam, int level){
        String filename = map + "/" +objectnaam + "_" + level + ".txt";
        File FileObject = new File(filename);
        String[] levelregels = Laden.laadTextFile(filename).split("\n");
        int teller = 0;
        String[] regelgetallen = levelregels[teller].replaceAll(" " ,"").split(",");
        int[] getallen = new int[levelregels.length*regelgetallen.length];
        while(teller < levelregels.length){
            int getallenTeller = 0;
            regelgetallen = levelregels[teller].replaceAll(" " ,"").split(",");  
            while(getallenTeller < regelgetallen.length){
                int index = getallenTeller + teller*regelgetallen.length;
                getallen[index] = Integer.parseInt(regelgetallen[getallenTeller]);
                //System.out.println(index + " - " + getallen[index]);
                getallenTeller ++;
            }
            teller ++;
        }
        return getallen;
    }

}