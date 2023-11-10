/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import javax.swing.JFrame;

/**
 * My 2D platform physics implementation
 * @author Matt
 */
public class Game implements KeyListener
{
    public static Game game;
    
    boolean m_running;
    long m_lastTimeNanos;
    
    public final int WIDTH = 1920, HEIGHT = 1080;
    public Renderer renderer;
    public Rectangle box;
    public Polygon ground;
    public Polygon block;
    public Polygon block2;
    public Polygon[] polys;
    
    public boolean isScrolling =  false;
    public float yVelocity = -10;
    public float xVelocity = 0;
    public float scrollY = 0;
    public float scrollX = 0;
    public float camOffset = 280;
    
    //Input
    public boolean upKey = false;
    public boolean rightKey = false;
    public boolean leftKey = false;
    public boolean downKey = false;
    
    //Time message
    String msg;
    double time;
    
    //physics variables
    public float gravity = (float) 0.9;
    public float jumpHeight = (float) -17;
    public float runningSpeed = 3;
    public float friction = (float) 0.8;
    public float _slope;
    
    /**
     * @param args the command line arguments
     */
    public Game()
    {
        JFrame jframe = new JFrame();
        renderer = new Renderer();
	jframe.add(renderer);
	jframe.setTitle("Game");
	jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	jframe.setSize(WIDTH, HEIGHT);
	jframe.setResizable(false);
	jframe.setVisible(true);
        jframe.addKeyListener(this);
        box = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
        block = new Polygon(new int[] {WIDTH/2+100,WIDTH/2+180, WIDTH/2+180, WIDTH/2+100}
                , new int[] {HEIGHT/2+80 ,HEIGHT/2+80, HEIGHT/2+320, HEIGHT/2+320}, 4);
        block2 = new Polygon(new int[] {WIDTH/2-100,WIDTH/2-20, WIDTH/2-20, WIDTH/2-100}
                , new int[] {HEIGHT/2+160 ,HEIGHT/2+160, HEIGHT/2+180, HEIGHT/2+180}, 4);     
        ground = new Polygon(new int[] {WIDTH/2-390 ,WIDTH/2-300,WIDTH/2+280,WIDTH/2+280, WIDTH/2-300}
                , new int[] {HEIGHT/2+280 ,HEIGHT/2+320, HEIGHT/2+320, HEIGHT/2+300, HEIGHT/2+300}, 5);
        polys = new Polygon[] {ground, block,block2};
    }
    
    public void repaint(Graphics2D g2D)
    {
        //Smooths the graphics
        if(isScrolling)
        {
            AffineTransform at = new AffineTransform(1, 0., 0., 1, -10, camOffset);
            g2D.setTransform(at);
        }
        
        //timer
        g2D.setColor(Color.white);
        g2D.setFont(new Font("Arial", 1, 100));
        
        //Text (works better when not scrolling)
        if(!isScrolling)
        {
            g2D.drawString(msg, 10, (int) (150 +( 30*(Math.cos(2 * (time-1))))));
        }
        
        //character
        g2D.setColor(Color.red);
	g2D.fillRect(box.x, box.y, box.width, box.height);
        
        //platforms
        g2D.setColor(Color.blue);
        for (Polygon a1 : polys) {
            g2D.fillPolygon(a1);
        }
    }
    
    public static void main(String[] args) 
    {
        game = new Game();
        game.start();
                
        while(game.isRunning())
        {
            long currentTimeNanos = System.nanoTime();
            long elapsedTimeNanos = currentTimeNanos - game.getLastTime();
            double elapsedTimeInSecond = (double)elapsedTimeNanos / 1000000000;
            
            game.update(elapsedTimeInSecond);
            try {
                Thread.sleep(10);
            } 
            catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     
    /**
     * Starts the game running.
     */
    public void start()
    {
        m_running = true;
        m_lastTimeNanos = System.nanoTime();
    }
    
    /**
     * Stops the game from running
     */
    public void stop()
    {
        m_running = false;
    }
    
    /**
     * Pauses the game
     */
    public void pause()
    {
        m_running = false;
    }
    
    /**
     * Pauses the game
     * @return 
     */
    public boolean isRunning()
    {
        return m_running;
    }
    
    public long getLastTime()
    {
        return m_lastTimeNanos;
    }
    
    public void update(double elapsedTime)
    {
        msg = "Time:" + elapsedTime;
        time = elapsedTime;
        //Log vaiables for testing physics
        //System.out.println(box.x + "  "+ box.y + "   " + scrollX + "   " + scrollY);
        //Logger.getLogger(Game.class.getName()).log(Level.INFO, msg);
        physics(jumpHeight, gravity, runningSpeed, friction, _slope);
        for (Polygon a1 : polys)
        {
            a1.translate((int) scrollX, (int) scrollY);
        }
        renderer.repaint();
        
        //Respawn for non scrolling
        if(box.y > 2000)
        {
            yVelocity = -3;
            box.y = HEIGHT / 2 - 10;
            box.x = WIDTH / 2 - 10;
        }
    }
    
    public boolean isCollision(Polygon[] a, Rectangle b)
    {
        for (Polygon a1 : a) {
            if (a1.intersects(b)) {
                return true;
            }
        }
        return false;
    }
    
    public void physics(float jumpHeight, float gravity, float runningSpeed, float friction, float slope)
    {
        //Movement in y
        yVelocity += gravity;
        box.y += yVelocity;
        if(isCollision(polys, box))
        {
            while(isCollision(polys, box))
            {
                box.y += ((Math.abs(yVelocity)/yVelocity)* -1);
            }
            
            if(Math.abs(yVelocity)/yVelocity == 1 && upKey)
            {
                yVelocity = jumpHeight;
            }
            else
            {
                yVelocity = 0;
            }
        }
        
        //Movement across surfaces
        if(leftKey)
        {
            xVelocity -= runningSpeed;
        }
        if(rightKey)
        {
            xVelocity += runningSpeed;
        }
        xVelocity = (int)(xVelocity * friction);
        box.x = (int) (xVelocity + box.getX());
        
        //Slope and wall collision
        _slope = 0;
        _slope++;
        if(isCollision(polys, box))
        {
            box.y -= _slope;
            while(isCollision(polys, box))
            {
                box.x += ((Math.abs(xVelocity)/xVelocity)* -1);
            }
        }
        
        if(isScrolling)
        {
            scrollX = (box.x - WIDTH / 2)*-1;
            scrollY = (box.y - HEIGHT / 2)*-1;
            box.x = WIDTH / 2;
            box.y = HEIGHT / 2;
        }
        else
        {
            scrollX = 0;
            scrollY = 0;
        }
    }
    
    //Input management
    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
        {  
            upKey = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_W)
        {  
            upKey = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_A)
        {  
            leftKey = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_D)
        {  
            rightKey = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_P)
        {
            isScrolling = !isScrolling;
            //resets transforms of geometry when switching veiwing modes
            box = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
            block = new Polygon(new int[] {WIDTH/2+100,WIDTH/2+180, WIDTH/2+180, WIDTH/2+100}, new int[] {HEIGHT/2+80 ,HEIGHT/2+80, HEIGHT/2+320, HEIGHT/2+320}, 4);
            block2 = new Polygon(new int[] {WIDTH/2-100,WIDTH/2-20, WIDTH/2-20, WIDTH/2-100}, new int[] {HEIGHT/2+190 ,HEIGHT/2+190, HEIGHT/2+210, HEIGHT/2+210}, 4);     
            ground = new Polygon(new int[] {WIDTH/2-390 ,WIDTH/2-300,WIDTH/2+280,WIDTH/2+280, WIDTH/2-300}, new int[] {HEIGHT/2+280 ,HEIGHT/2+320, HEIGHT/2+320, HEIGHT/2+300, HEIGHT/2+300}, 5);
            polys = new Polygon[] {ground, block, block2};
        }
    }
    public void keyTyped(KeyEvent e){}
    @Override
    public void keyReleased(KeyEvent e)
    {
        if(e.getKeyCode() ==  KeyEvent.VK_SPACE)
        {  
            upKey = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_W)
        {  
            upKey = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_A)
        {  
            leftKey = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_D)
        {  
            rightKey = false;
        }
    }
}