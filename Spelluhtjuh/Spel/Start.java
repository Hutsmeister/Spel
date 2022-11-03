package Spel;
import Tools.*;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * Abstract class Spel
 * Wat doet deze klasse?
 *
 * @author Dhr. Luites en Dhr. Koldenhof
 * @version (V4.0)
 * 
 * Deze klasse mag je NIET veranderen
 * 
 */
public class Start{
    // Wat wordt in de volgende 3 regelsgedaan ?
    static boolean aan = false;
    private JFrame venster;
    private Tekenaar tekenaar;
    private Omgeving omgeving;
    private Beweeg beweeg;
    private Keyboard kb;
    private Mouse muis;

    // Wat doen de onderstaande 6 regels ? 
    private int framesPerSecond = 120;
    private long nsPerFrame = 1000000000 / framesPerSecond;
    private float spelSnelheid = 1.0f; 
    private long begintijdHerhaling = System.nanoTime();
    private boolean running = true;
    private float stapGrootte;  
    
    // Hoeveel constructoren heeft deze klasse?
    
    public Start(){
        this(1000,800);
    }

    //Wat wordt in de onderstaande constructor gedaan ?
    private Start(int breedte, int hoogte){
        aan = true;

        venster = new JFrame();
        venster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tekenaar = new Tekenaar();
        tekenaar.setPreferredSize(new Dimension(breedte, hoogte));

        kb = new Keyboard(venster, tekenaar);
        muis = new Mouse(tekenaar);

        beweeg = new Beweeg();
        omgeving = new Omgeving(breedte, hoogte, tekenaar, beweeg, kb, muis );

        venster.add(tekenaar);

        tekenaar.init();

        venster.setVisible(true);

        herhaling();
        aan = false;

    }

    public void versnel(int hoeveelheid){
        framesPerSecond += hoeveelheid;
    }

    public void stopSpel(){
        running = false;
    }
    
    //  Wat doet deze methode en welke constructie gebruikt deze?
    public void herhaling(){
        while(running){
            long nu = System.nanoTime();
            long herhalingsduur = nu - begintijdHerhaling;
            begintijdHerhaling = nu; 
            stapGrootte = (herhalingsduur * spelSnelheid) / 1000000000;

            beweeg.beweegAlles(stapGrootte);
            tekenaar.tekenAlles();

            try{
                Thread.sleep(
                (begintijdHerhaling - System.nanoTime() + nsPerFrame)/1000000 );
            } catch(Exception e){
                //print("Er is iets mis gegaan " + e);
            };

        }
        
        venster.dispose();
        
    }
    
    private void print(String t){
        System.out.println(t);
    }
}
