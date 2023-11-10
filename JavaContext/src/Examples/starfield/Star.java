/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Examples.starfield;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;

/**
 * @author Matt
 */
public class Star 
{
    float x;
    float y;
    float z;
    float maxRadius;
    static Random rand = new Random();
    float space = 700;
    int color;
    
    public Star()
    {
        color = rand.nextInt(8);
        x = (rand.nextFloat()*2 -1) * space;
        y = (rand.nextFloat()*2 -1) * space;
        z = rand.nextFloat()*1000;
        maxRadius = rand.nextInt(5) + 13;
    }
    
    public void draw(Graphics2D g)
    {
        float sx = mapfloat(x/z, 0, 1, 0, space);
        float sy = mapfloat(y/z, 0, 1, 0, space);
        
        float radius = mapfloat(z, 0, space, maxRadius, 0);
        g.fill(new Ellipse2D.Float(sx, sy, radius, radius));
    }
    
    public void updatePos()
    {
        z =  z- 1;
        if(z < 1)
        {
            z = 1000;
            x = (rand.nextFloat()*2 -1) * space;
            y = (rand.nextFloat()*2 -1) * space;
        }
    }
    
    public float mapfloat(float x, float in_min, float in_max, float out_min, float out_max)
    {
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }
}