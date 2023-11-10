/*****************************************************************
 * Class: ObstacleLane
 * Programmer: Matthew Taylor
 * Date: April 18, 2021
 * Program: ObstacleLane.java
 * Description: Keeps track of a single lane with obstacles
 *****************************************************************/
package frogger;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObstacleLane extends GameObject
{
    // Fields
    private ArrayList<GameObject> obstacles;
    private Random rand;
    private ArrayList<Color> palette;
    
    // Constants
    private static final int START_POSITION = -5;
    private static final int END_POSITION = 20;
    
    // Constructor
    public ObstacleLane(int x, int y, int width, int height, Color col)
    {
        super(x, y, width, height, col, 0);
        
        // Random for random colors and size
        rand = new Random();
        
        // To contain obstacles within the lane
        obstacles = new ArrayList<>();
    }
    
    public void createObstacles(int obstacleCount, int obstacleSpeed, ArrayList<Color> colors)
    {
        
        palette = colors;
        
        // Loop to create many obstacles
        for(int i = 0; i < obstacleCount; i++)
        {
            // Random i
            GameObject obstacle = new GameObject(getBounds().x + i *6, getBounds().y, rand.nextInt(5)+1, 1, palette.get(rand.nextInt(palette.size())), obstacleSpeed);
            obstacles.add(obstacle);
        }
    }
    
    public void draw(Graphics g)
    {
        for(GameObject e : obstacles)
        {
            e.draw(g);
        }
    }
    
    /**
     * Method Name: moveObstacles
     * Moves all the obstacles
     */
    public void moveObstacles()
    {   
        // Moving the logs
        for(GameObject e: obstacles)
        {
            // Move each log
            e.move(e.getSpeed(), 0);
            
            // Create new random logs, logs going
            if(e.getBounds().x > END_POSITION && e.getSpeed() > 0)
            {
                // Set the index of the log that is out of bounds to a new log with the same lane
                obstacles.set(obstacles.indexOf(e), new GameObject(START_POSITION, e.getBounds().y, rand.nextInt(5) + 1, 1, palette.get(rand.nextInt(palette.size())), e.getSpeed()));
            }
            // Logs going left
            if(e.getBounds().x < START_POSITION && e.getSpeed() < 0)
            {
                // Replacing for logs with inverse speed
                obstacles.set(obstacles.indexOf(e), new GameObject(END_POSITION, e.getBounds().y, rand.nextInt(5) + 1, 1, palette.get(rand.nextInt(palette.size())), e.getSpeed()));
            }
        }
    }
    
    public void setObstacles(ArrayList list)
    {
        obstacles = list;
    }
    
    public ArrayList getObstacles()
    {
        return obstacles;
    }
}
