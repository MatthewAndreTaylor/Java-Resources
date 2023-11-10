/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boids;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author Matt
 */
public class Boids extends JPanel 
{
    public static Boids boids;
    Flock flock;
    public static final int w = 1920;
    public static final int h = 1080;
    
    //Time
    boolean m_running;
    long m_lastTimeNanos;
    
    //slider counter
    public static int sliderCounter = 20;
    
    public Boids() {
        setPreferredSize(new Dimension(w, h));
        setBackground(Color.lightGray);
 
        spawnFlock();
    }
 
    private void spawnFlock() {
        flock = Flock.spawn(400, 300, 500); 
    }
 
    @Override
    public void paintComponent(Graphics gg) {
        super.paintComponent(gg);
        Graphics2D g = (Graphics2D) gg;
//        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//                RenderingHints.VALUE_ANTIALIAS_ON);
        flock.run(g, w, h);
    }
 
    public static void main(String[] args) {
        boids = new Boids();
        boids.start();
        
        SwingUtilities.invokeLater(() -> {
            JFrame jframe = new JFrame();
            jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jframe.setTitle("Boids");
            jframe.setResizable(false);
            jframe.add(boids, BorderLayout.CENTER);
            jframe.pack();
            jframe.setLocationRelativeTo(null);
            JSlider slider = new JSlider(0, 60, sliderCounter);
            slider.setMajorTickSpacing(5);
            slider.setPaintLabels(true);
            slider.addChangeListener((ChangeEvent e) -> {
            JSlider source = (JSlider) e.getSource();
            sliderCounter = source.getValue();
        });
            jframe.add(slider, BorderLayout.PAGE_END);
            jframe.setVisible(true);
        });
        
        double frames = 0;
        while(boids.isRunning())
        {
            long currentTimeNanos = System.nanoTime();
            long elapsedTimeNanos = currentTimeNanos - boids.getLastTime();
            double elapsedTimeInSecond = (double)elapsedTimeNanos / 1000000000;
            
            frames++;
            
            //System.out.println((frames / elapsedTimeNanos * 1000000));

            boids.update(elapsedTimeInSecond);
        }
    }
    
    public static int centerPull()
    {
        return sliderCounter;
    }
    
    public void start()
    {
        m_running = true;
        m_lastTimeNanos = System.nanoTime();
    }
    
    public boolean isRunning()
    {
        return m_running;
    }
    
    public long getLastTime()
    {
        return m_lastTimeNanos;
    }
    
    public void update(double time)
    {
        repaint();
    }
}
 

