/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Examples.hypocycloid;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javacontext.JContext;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author matth
 */
public class Hypocycloid extends JContext{

    static Color [] colors = 
    {
        new Color(255, 0, 0),new Color(0, 0, 255),new Color(60, 179, 113),
        new Color(238, 130, 238),new Color(255, 165, 0),new Color(106, 90, 205),
        new Color(255,0,255),new Color(255, 255, 0)
    };
    
    Rectangle[] boxes = new Rectangle[8];
    
    double speed = 1;
    int counter = 1;
    
    private Hypocycloid() {
        super("Hypocycloid", 800, 800);
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
        
        for(int i = 0; i < boxes.length; i++)
        {
            boxes[i] = new Rectangle(380, 380, 40, 40);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Hypocycloid hypocycloid = new Hypocycloid();
        hypocycloid.init();
    }

    @Override
    public void repaint(Graphics2D g2D)
    {
        g2D.fillOval(100, 100, 600, 600);
        g2D.setColor(Color.WHITE);
        g2D.drawLine(100, 400, 700, 400);
        g2D.drawLine(400, 100, 400, 700);
        g2D.drawLine(611, 611, 189, 189);
        g2D.drawLine(611, 189, 189, 611);
        for (int i = 0; i < counter; i++) 
        {
            g2D.setColor(colors[i]);
            g2D.fillOval(boxes[i].x, boxes[i].y, boxes[i].width , boxes[i].height);
        }
    }
    
    @Override
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
        super.update(time);
    }
}
