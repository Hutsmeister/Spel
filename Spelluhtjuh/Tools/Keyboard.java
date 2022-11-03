package Tools;
import Spel.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import javax.swing.JFrame;

public class Keyboard{ 
    private static boolean[] _keys;
    private KeyListener _customKeylistener = new KeyListener(){
            public void keyPressed(KeyEvent e) {
                _keys[e.getKeyCode()] = true;
            }

            public void keyReleased(KeyEvent e) {
                _keys[e.getKeyCode()] = false;
            }

            @Override
            public void keyTyped(KeyEvent e) {}
        };

    public Keyboard(JFrame venster, Tekenaar tekenaar){
        int maxKeys = 0;
        Field[] fields = KeyEvent.class.getDeclaredFields();
        for (Field f : fields) {
            if (Modifier.isStatic(f.getModifiers())) {
                Class<?> type = f.getType();
                if(f.getName().startsWith("VK_") && type == int.class){
                    try { 
                        maxKeys = Math.max(maxKeys, f.getInt(null));
                    } catch (Exception e){}
                }
            } 
        }

        _keys = new boolean[maxKeys];

        venster.addKeyListener(_customKeylistener);
        tekenaar.addKeyListener(_customKeylistener);
    }

    public boolean isIngedrukt(int keyCode){
        return (_keys.length > keyCode ?  _keys[keyCode] : false);
    }

    public boolean consumeIngedrukt(int keyCode){
        if(_keys.length > keyCode && _keys[keyCode]){
            _keys[keyCode] = false;
            return true;
        }
        return false;
    }

}