/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starfeild;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import javax.swing.JFrame;

/**
 *
 * @author Matt
 */
public class StarFeild 
{
    public static StarFeild feild;
    public Renderer renderer;
    public boolean m_running;
    public long m_lastTimeNanos;
    public Star[] stars = new Star[6000];
    Color [] colors = new Color[8];
    int[][] colorPalette = 
    {
        {255, 0, 0},{0, 0, 255},{60, 179, 113},
        {238, 130, 238},{255, 165, 0},{106, 90, 205},
        {255,0,255},{255, 255, 0}
    };
    
    public StarFeild()
    {
        JFrame jframe = new JFrame();
        renderer = new Renderer();
	jframe.add(renderer);
        renderer.setBackground(Color.BLACK);
	jframe.setTitle("StarField");
	jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	jframe.setSize(1800, 1000);
	jframe.setResizable(false);
        jframe.setVisible(true);
        for(int i = 0; i < 8; i++)
        {
            colors[i] = new Color(colorPalette[i][0],colorPalette[i][1], colorPalette[i][2]);
        }
    }
    
    public void repaint(Graphics2D g2D)
    {
        //g2D.setColor(Color.WHITE);
        for (Star star : stars) {
            g2D.setColor(colors[star.color]);
            AffineTransform toCenterAt = new AffineTransform();
            toCenterAt.translate((1800/2), (1000/2));
            g2D.setTransform(toCenterAt);
            star.updatePos();
            star.draw(g2D);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        feild = new StarFeild();
        feild.start();
        
        while(feild.isRunning())
        {
            long currentTimeNanos = System.nanoTime();
            long elapsedTimeNanos = currentTimeNanos - feild.getLastTime();
            double elapsedTimeInSecond = (double)elapsedTimeNanos / 1000000000;
            feild.update(elapsedTimeInSecond);
            try {
                Thread.sleep(0);
            } 
            catch (InterruptedException ex) {}    
        }
    }
    
    public void update(double time)
    {
        renderer.repaint();
    }
    
    public void start()
    {
        m_running = true;
        m_lastTimeNanos = System.nanoTime();
        for(int i=0; i < stars.length; i++)
        {
            stars[i] = new Star();
        }
    }
    
    public void stop()
    {
        m_running = false;
    }
    
    public long getLastTime()
    {
        return m_lastTimeNanos;
    }
    
    public boolean isRunning()
    {
        return m_running;
    }
}
