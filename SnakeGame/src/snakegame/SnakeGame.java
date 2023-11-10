/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Matt
 */
public class SnakeGame 
{
    public static SnakeGame sg;
    
    JFrame frame;
            
    JPanel panel = new JPanel()
    {
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            SnakeGame.sg.repaint(g);
        }
    };
    
    KeyAdapter keys = new KeyAdapter()
    {
        public void KeyPressed(KeyEvent e)
        {
            //if(e.getKeyCode() == KeyEvent.VK_SPACE)
        }
        public void KeyReleased(KeyEvent e)
        {
            
        }
    };
    
    public SnakeGame()
    {
         frame = new JFrame();
         frame.setSize(600, 600);
         frame.add(panel);
         frame.addKeyListener(keys);
         frame.setLocationRelativeTo(null);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setVisible(true);
    }
    
    public void repaint(Graphics g)
    {
        //g.fillRect();
    }
    
    public void update()
    {
        panel.repaint();
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        sg = new SnakeGame();
        while(true)
        {
            sg.update();
            try 
            {
                Thread.sleep(11);
            } 
            catch (InterruptedException ex) {}
        }
    }
    
}
