/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicstest;

import java.awt.Graphics;
import javax.swing.JFrame;

/**
 *
 * @author Matt
 */
public class GraphicsTest {
    public static GraphicsTest gt;
    public Renderer renderer;
    public static boolean m_running;
    public long m_lastTimeNanos;
    
    public GraphicsTest()
    {
        JFrame frame = new JFrame();
        renderer = new Renderer();
        frame.add(renderer);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public boolean isRunning()
    {
        return m_running;
    }
    public void start()
    {
        m_running = true;
        m_lastTimeNanos = System.nanoTime();
    } 
    public long getLastTime()
    {
        return m_lastTimeNanos;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        gt = new GraphicsTest();
        gt.start();
        while(gt.isRunning())
        {
            long elapsedTime = System.nanoTime() - gt.getLastTime();
            double elapsedTimeSeconds = (double)elapsedTime/1000000000;
            //System.out.println(elapsedTimeSeconds);
            gt.update();
        }
    }
    
    public void repaint(Graphics g)
    {
        g.drawOval(0, 0, 100, 100);
    }
    
    public void update()
    {
        renderer.repaint();
    }
}
