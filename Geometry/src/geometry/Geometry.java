/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometry;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author Matt
 */
public class Geometry {
    
    public static Geometry geometry;
    public Renderer renderer;
    public boolean m_running;
    public long m_lastTimeNanos;
    
    public final int WIDTH = 800, HEIGHT = 800;
    public boolean showLines = true;
    Rectangle[] boxes = new Rectangle[8];
    Color [] colors = new Color[8];
    int[][] colorPalette = 
    {
        {255, 0, 0},{0, 0, 255},{60, 179, 113},
        {238, 130, 238},{255, 165, 0},{106, 90, 205},
        {255,0,255},{255, 255, 0}
    };
    double speed = 1;
    int counter = 1;
    
    public void start()
    {
        m_running = true;
        m_lastTimeNanos = System.nanoTime();
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

    public Geometry()
    {
        JFrame jframe = new JFrame();
        renderer = new Renderer();
	jframe.add(renderer);
	jframe.setTitle("Hypocycloid");
	jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	jframe.setSize(WIDTH, HEIGHT);
	jframe.setResizable(false);
	
        JLabel label = new JLabel();
        JSlider sliderSpeed = new JSlider(1, 160, 40);
        JSlider sliderCounter = new JSlider(1, 8, 1);
        label.setOpaque(true);
        sliderSpeed.setMajorTickSpacing(20);
        sliderSpeed.setMinorTickSpacing(1);
        sliderSpeed.setPaintLabels(true);
        sliderCounter.setMajorTickSpacing(1);
        sliderCounter.setPaintLabels(true);
        sliderCounter.addChangeListener((ChangeEvent e) -> {
            JSlider source = (JSlider) e.getSource();
            counter = source.getValue();
        });
        sliderSpeed.addChangeListener((ChangeEvent e) -> {
            JSlider source = (JSlider) e.getSource();
            speed = source.getValue() * 0.025;
        });
        jframe.add(sliderCounter, BorderLayout.PAGE_END);
        jframe.add(sliderSpeed, BorderLayout.PAGE_START);
        jframe.add(label, BorderLayout.SOUTH);
        jframe.setVisible(true);
        
        for(int i = 0; i < boxes.length; i++)
        {
            Rectangle r = new Rectangle(380, 380, 40, 40);
            boxes[i] = r;
            colors[i] = new Color(colorPalette[i][0],colorPalette[i][1], colorPalette[i][2]);
        }
    }
    
    public void repaint(Graphics g)
    {
        if(showLines)
        {
            g.fillOval(100, 100, 600, 600);
            g.setColor(Color.WHITE);
            g.drawLine(100, 400, 700, 400);
            g.drawLine(400, 100, 400, 700);
            g.drawLine(611, 611, 189, 189);
            g.drawLine(611, 189, 189, 611);
        }
        for (int i = 0; i < counter; i++) 
        {
            g.setColor(colors[i]);
            g.fillOval(boxes[i].x, boxes[i].y, boxes[i].width , boxes[i].height);
        }
    }
    public void update(double time)
    {
        boxes[0].x = (int)(380 + ( 300*(Math.sin(speed * (time)))));
        boxes[1].y = (int)(380 + ( 300*(Math.cos(speed * (time)))));
        boxes[2].x = (int)(380 + ( 211*(Math.cos(-speed * (time -Math.toRadians( 45/speed ))))));
        boxes[2].y = (int)(380 + ( 211*(Math.cos(speed * (time-Math.toRadians( 45/speed ))))));
        boxes[3].x = (int)(380 - ( 211*(Math.cos(-speed * (time-Math.toRadians( 315/speed ))))));
        boxes[3].y = (int)(380 + ( 211*(Math.cos(speed * (time-Math.toRadians( 315/speed ))))));    
        boxes[4].x = (int)(380 - ( 285*(Math.cos(-speed * (time-Math.toRadians( -67.5/speed ))))));
        boxes[4].y = (int)(380 + ( 95*(Math.cos(speed * (time-Math.toRadians( -67.5/speed ))))));       
        boxes[5].x = (int)(380 + ( 95*(Math.cos(-speed * (time -Math.toRadians( 22.5/speed ))))));
        boxes[5].y = (int)(380 + ( 285*(Math.cos(speed * (time-Math.toRadians( 22.5/speed ))))));
        boxes[6].x = (int)(380 - ( 95*(Math.cos(-speed * (time -Math.toRadians( -22.5/speed ))))));
        boxes[6].y = (int)(380 + ( 285*(Math.cos(speed * (time-Math.toRadians( -22.5/speed ))))));
        boxes[7].x = (int)(380 + ( 285*(Math.cos(-speed * (time-Math.toRadians( 67.5/speed ))))));
        boxes[7].y = (int)(380 + ( 95*(Math.cos(speed * (time-Math.toRadians( 67.5/speed ))))));
        renderer.repaint();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        geometry = new Geometry();
        geometry.start();
        
        while(geometry.isRunning())
        {
            long currentTimeNanos = System.nanoTime();
            long elapsedTimeNanos = currentTimeNanos - geometry.getLastTime();
            double elapsedTimeInSecond = (double)elapsedTimeNanos / 1000000000;
            geometry.update(elapsedTimeInSecond); 
        }
    }
}
