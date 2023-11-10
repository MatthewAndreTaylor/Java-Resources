/*****************************************************************
 * Class: GameObject
 * Programmer: Matthew Taylor
 * Date: April 17, 2021
 * Program: GameObject.java
 * Description: Keeps track of a game object that is used in the game
 *****************************************************************/
package frogger;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Matt
 */
public class GameObject 
{
    private static final int CELL_SIZE = 40;
    
    // Fields
    private Rectangle boundingBox;
    private Color color;
    private int speed;
    private int xVelocity;
    private int yVelocity;
    
    // Constructor
    public GameObject(int x, int y, int width, int height, Color col, int moveSpeed)
    {
        boundingBox = new Rectangle(x *CELL_SIZE, y * CELL_SIZE, width * CELL_SIZE, height * CELL_SIZE);
        color = col;
        speed = moveSpeed;
        xVelocity = 0;
        yVelocity = 0;
    }
    
    /**
     * Method Name: move
     * Moves the game object
     */
    public void move(int xV, int yV)
    {
        xVelocity = xV;
        yVelocity = yV;
        boundingBox.x += xVelocity;
        boundingBox.y -= yVelocity;
    }
    
    /**
     * Method Name: hasCollided
     * Checks if this game objects boundingBox has collided
     * @param gameObjectsList - a list of game objects
     * @return if the an element of the list and this game objects bounding boxes have collided
     */
    public boolean hasCollided(ArrayList<GameObject> gameObjectsList)
    {
        for (GameObject e : gameObjectsList) 
        {
            if (boundingBox.intersects(e.boundingBox)) 
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Method Name: hasCollided
     * Checks if this game objects boundingBox has collided
     * @param gameObject - the first GameObject
     * @return if this game object has collided
     */
    public boolean hasCollided(GameObject gameObject)
    {
        return boundingBox.intersects(gameObject.boundingBox);
    }
    
    /**
     * Method Name: draw
     * Draws the game object
     * @param g - the graphics
     */
    public void draw(Graphics g)
    {
        g.setColor(color);
        g.fillRect(boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
    }
    
    // Accessors and Mutators
    public Rectangle getBounds()
    {
        return new Rectangle(boundingBox.x/CELL_SIZE, boundingBox.y/CELL_SIZE, boundingBox.width/CELL_SIZE, boundingBox.height/CELL_SIZE);
    }
    
    public Rectangle getTrueBounds()
    {
        return new Rectangle(boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
    }
    
    public void setBounds(Rectangle bounds)
    {
        boundingBox = bounds;
    }
    
    public void setBounds(int x, int y, int width, int height)
    {
        boundingBox = new Rectangle(x *CELL_SIZE, y * CELL_SIZE, width * CELL_SIZE, height * CELL_SIZE);
    }
    
    public Color getColor()
    {
        return color;
    }
    
    public void setColor(Color col)
    {
        color = col;
    }
    
    public int getSpeed()
    {
        return speed;
    }
    
    public void setSpeed(int travelSpeed)
    {
        speed = travelSpeed;
    }
    
    public int getXVelocity()
    {
        return xVelocity;
    }
    
    public int getYVelocity()
    {
        return yVelocity;
    }
    
    public void setXVelocity(int xV)
    {
        xVelocity = xV;
    }
    
    public void setYVelocity(int yV)
    {
        yVelocity = yV;
    }
    
    public  static int getCellSize()
    {
        return CELL_SIZE;
    }
}
