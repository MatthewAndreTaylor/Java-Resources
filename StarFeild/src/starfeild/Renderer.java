/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starfeild;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class Renderer extends JPanel
{
    @Override
    protected void paintComponent(Graphics g)
    {
        Graphics2D g2D = (Graphics2D)g;
        RenderingHints rh = new RenderingHints(
        RenderingHints.KEY_TEXT_ANTIALIASING,
        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2D.setRenderingHints(rh);
        super.paintComponent(g);
        StarFeild.feild.repaint(g2D);   
    }	
}