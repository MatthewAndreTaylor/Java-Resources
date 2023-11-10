/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Examples.starfield;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import javacontext.JContext;


/**
 *
 * @author matth
 */
public class StarField extends JContext{
    
    public static Star[] stars = new Star[6000];
    static Color [] colors = 
    {
        new Color(255, 0, 0),new Color(0, 0, 255),new Color(60, 179, 113),
        new Color(238, 130, 238),new Color(255, 165, 0),new Color(106, 90, 205),
        new Color(255,0,255),new Color(255, 255, 0)
    };
    
    private StarField() {
        super("StarField", 1800, 1000);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // Setup stars
        for(int i=0; i < stars.length; i++)
        {
            stars[i] = new Star();
        }
        
        StarField starField = new StarField();
        starField.getRenderer().setBackground(Color.BLACK);
        starField.init();
    }

    @Override
    public void repaint(Graphics2D g2D)
    {
        for (Star star : stars) {
            g2D.setColor(colors[star.color]);
            AffineTransform toCenterAt = new AffineTransform();
            toCenterAt.translate((1800/2), (1000/2));
            g2D.setTransform(toCenterAt);
            star.updatePos();
            star.draw(g2D);
        }
    }
}
