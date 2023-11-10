/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javacontext;

import java.awt.Graphics2D;
import javax.swing.JFrame;

/**
 *
 * @author matth
 */
public abstract class JContext extends MonoAction implements Drawable {
    
    private final Renderer renderer;
    public final JFrame jframe = new JFrame();
    
    public JContext(String name, int width, int height)
    {
        renderer = new Renderer(this);
	jframe.add(renderer);
	jframe.setTitle(name);
	jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	jframe.setSize(width, height);
	jframe.setResizable(false);
    }
    
    public void init(){
        Thread thread = new Thread(this);
        thread.start();
        jframe.setVisible(true);
    }
    
    @Override
    public abstract void repaint(Graphics2D g2D);

    @Override
    public void update(double elapsedTime)
    {
        this.renderer.repaint();
    }
    
    public Renderer getRenderer(){
        return this.renderer;
    }
}
