/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicstest;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Matt
 */
public class Renderer extends JPanel
{
    @Override
    protected void paintComponent(Graphics g)
    {
        //Paint
        super.paintComponent(g);
        //Recurrisivly calls itself
        GraphicsTest.gt.repaint(g);
    }
}
