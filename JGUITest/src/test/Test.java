/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Matt
 */
public class Test 
{
    // Instance of the class
    public static Test smpl;
    
    // Intializing frame
    JFrame frame;
    
    // Panel holds the game content
    public JPanel panel = new JPanel() 
    {
        // Special method for painting to panel
        public void paintComponent(Graphics g) 
        {
            // Do painting
            super.paintComponent(g);
            
            // Draw all the shapes with repaint method
            Test.smpl.repaint(g);
        }
    };
    
    // Keys gets users key events
    public KeyAdapter adapter =  new KeyAdapter() 
    {
        // Key pressed
        public void keyPressed(KeyEvent e)
        {
            if(e.getKeyCode() == KeyEvent.VK_SPACE)
                upKey = true;
        }
        
        // Key released
        public void keyReleased(KeyEvent e)
        {
            if(e.getKeyCode() == KeyEvent.VK_SPACE)
                upKey = false;
        }
    };
    
    // Rectangle for the player and player variables
    public Rectangle player;
    public static float yVelocity = -10;
    public static float gravity = (float) 0.9;
    
    // Boolean for user input
    public boolean upKey = false;
    
    // Rectangles for the obstacle and obstacle variables
    public Rectangle obstacle;
    public static float obstacleSpeed = (float) 4.5;
    
    // Rectangle for the ground
    public Rectangle ground;
    
    // Score
    public static int score;
    
    // Constructor
    public Test()
    {
        // Frame can hold content
        frame = new JFrame();
        
        // Only run while window is open
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Setting up the player with dimensions
        player = new Rectangle(30, 100, 20, 30);
        
        // Setting up the ground with dimensions
        ground = new Rectangle(0, 320, 400, 45);
        
        //
        obstacle = new Rectangle(700, 290, 20, 30);
        
        // Adding panel to frame so user can see the content
        frame.add(panel);
        
        // Adding Key Listener to frame for user input
        frame.addKeyListener(adapter);
        
        // Making frame visible to user and setting the size
        frame.setSize(400,900); 
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public void repaint(Graphics g)
    {
        // Paint the score
        g.drawString("Score: " + String.valueOf(score), 160, 40);
        
        // Paint the player and colour it red 
        g.setColor(Color.RED);
        g.fillRect(player.x, player.y, player.width, player.height);
        
        // Paint the ground and colour it green 
        g.setColor(Color.GREEN);
        g.fillRect(ground.x, ground.y, ground.width, ground.height);
                         
        // Paint all the obstacles and colour them blue
        g.setColor(Color.BLUE);
        g.fillRect(obstacle.x, obstacle.y, obstacle.width, obstacle.height);

    }
    
    public void move()
    {
        //Movement in y
        yVelocity += gravity;
        player.y += yVelocity;
        if(player.intersects(ground))
        {
            // Pushes the player out of the ground
            while(player.intersects(ground))
            {
                player.y += ((Math.abs(yVelocity)/yVelocity)* -1);
            }
            
            // Ground check before jump
            if(Math.abs(yVelocity)/yVelocity == 1 && upKey)
            {
                yVelocity = -17;
            }
            else
            {
                yVelocity = 0;
            }
        }
        
        obstacle.x -= obstacleSpeed;
        if(obstacle.x < -10)
        {
            // Icrementing score
            score++;
            
            // Speeding up and randomizing obstacle to increase difficulty
            obstacleSpeed += 0.1;
            obstacle.x = (int)(450 + Math.random()* 100);
        }
        
        if(player.intersects(obstacle))
        {
            JOptionPane.showMessageDialog(frame, "Game Over  :(  \nYour Score was: " + score);
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
    }
    
    public void update()
    {
        // Using panels repaint method
        panel.repaint();
        move();
    }
    
    public static void main(String[] args) 
    {
        // New instance of the class
        smpl = new Test();
        
        // Game loop
        while(true)
        {
            // Delay
            try {
                Thread.sleep(11);
            } 
            catch (InterruptedException ex){}
            // I call update to get the newest changes
            smpl.update();
        }
    }
}
