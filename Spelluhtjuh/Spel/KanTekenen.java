package Spel;

import java.awt.Graphics2D;

/**
 * interface tekenen - Zorgt voor eis om een object tekenbaar te maken door de Tekenaar
 *
 * @author (Dhr. Koldenhof)
 * @version (V1.2)
 */

public interface KanTekenen{ 
    /**
     *
     * @param  Het Graphics2D geeft het object waar je de verschillende
     * grafische acties op kan toepassen.
     *  zoals fillRect(), fillOval(), drawImage(), setColor(), setFont() enz.
     *  
     */
    
    public void teken(Graphics2D g);

}