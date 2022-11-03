package Spel;
import Tools.*;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.Window;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.SwingUtilities; 
/**
 * 
 * @author Dhr. Luites en Dhr. Koldenhof
 * 
 * Deze klasse mag je NIET veranderen
 *
 */
public class Tekenaar extends Canvas{
    private static final long serialVersionUID = 8035610796241827944L;
    private BufferStrategy _buffer;
    private long _fps, _fpsCounter, _lastFpsTime;
    private boolean _showDebug = false;
    private Color _backgroundColor;

    private ArrayList<KanTekenen> tekenLijst;

    public Tekenaar(){
        super(Laden.config);
        tekenLijst = new ArrayList<KanTekenen>();
        _backgroundColor = Color.LIGHT_GRAY;

        this.setIgnoreRepaint(true);
    }

    public void setBackground(Color c){
        _backgroundColor = c;
    }

    /**
     * Start de Tekenaar.
     */
    public void init(){
        Window w = SwingUtilities.getWindowAncestor(this);
        if(w!=null){
            w.pack();
        }

        createBufferStrategy(2);
        _buffer = getBufferStrategy();    
    }

    public void toonDebugInfo(Graphics2D g){
        _fpsCounter++;

        long now = System.nanoTime();
        if (now-_lastFpsTime >= 1000000000){
            _fps = _fpsCounter;

            _lastFpsTime = now;
            _fpsCounter = 0;
        }

        g.setColor(new Color(0,0,0,0.5f));
        g.fillRect(0, 0, 130, 50);

        g.setColor(Color.WHITE);
        g.drawString("FPS: " +_fps, 10, 19);

        g.setColor(Color.BLACK);
        g.drawRect(0, 0, 130, 50);

    }

    public void voegObjectToe(KanTekenen o){
        tekenLijst.add(o);
    }

    public void verwijderObject(KanTekenen o){
        tekenLijst.remove(o);
    }

    public void verwijderLijst(ArrayList lijst){
        tekenLijst.removeAll(lijst);
    }

    public void voegLijstToe(ArrayList lijst){
        tekenLijst.addAll(lijst);
    }

    /**
     * Teken all TekenObjecten.
     */
    public void tekenAlles(){
        Graphics2D g = (Graphics2D)_buffer.getDrawGraphics();

        g.setColor(_backgroundColor);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        if(_showDebug){
            toonDebugInfo(g);
        }

        for (KanTekenen obj : tekenLijst){
            obj.teken(g);
        }

        g.dispose();        
        _buffer.show();
    }

}