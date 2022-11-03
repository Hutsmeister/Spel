package Tools;

import Spel.*;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;

public class Mouse{ 
    private boolean[] _buttons;
    private boolean[] _clicks;
    private int _scroll;
    private Tekenaar _venster;
    Cursor _geenCursor = Toolkit.getDefaultToolkit().createCustomCursor( new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "verborgen cursor");

    public Mouse(Tekenaar venster){
        _venster = venster;

        _buttons = new boolean[MouseInfo.getNumberOfButtons()];
        _clicks = new boolean[MouseInfo.getNumberOfButtons()];
        _scroll = 0;

        _venster.addMouseWheelListener(new MouseWheelListener(){
                public void mouseWheelMoved(MouseWheelEvent e) {
                    _scroll += e.getWheelRotation();
                }
            });

        _venster.addMouseListener(new MouseListener(){
                @Override
                public void mousePressed(MouseEvent e) {
                    _buttons[e.getButton()] = true;
                    _clicks[e.getButton()] = true;
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    _buttons[e.getButton()] = false;
                }

                @Override
                public void mouseClicked(MouseEvent e) {}

                @Override
                public void mouseEntered(MouseEvent e) {}

                @Override
                public void mouseExited(MouseEvent e) {}

            });
    }

    public boolean isIngedrukt(int button){
        return (_buttons.length > button ? _buttons[button] :  false);
    }

    public boolean isClick(int button){
        return (_clicks.length > button ? _clicks[button] :  false);
    }

    public boolean consumeClick(int button){
        if(_clicks.length > button && _clicks[button]){
            _clicks[button] = false;
            return true;
        }
        return false;
    }

    public Point getPositie(){
        return getPositie(true);
    }

    public Point getPositie(boolean binnenVenster){
        Point positie = MouseInfo.getPointerInfo().getLocation();;
        if(binnenVenster){
            Point venster = _venster.getLocationOnScreen();
            if(venster!=null){
                positie.x -= venster.x;
                positie.y -= venster.y;
            }
        }
        return positie;
    }

    public int getScrollHoeveelheid(){
        int scroll  = _scroll;
        _scroll = 0;
        return scroll;
    }

    public void toonMuis(boolean visible){
        _venster.setCursor( visible ? null : _geenCursor);
    }

    public void setMuisIcon(Image icon){
        int x = icon.getWidth(null)<32 ? icon.getWidth(null)/2 : 16;
        int y = icon.getHeight(null)<32 ? icon.getHeight(null)/2 : 16;
        _venster.setCursor( Toolkit.getDefaultToolkit().createCustomCursor( icon, new Point( x, y), "eigen cursor") );
    }
}