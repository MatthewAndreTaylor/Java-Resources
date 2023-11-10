/*****************************************************************
 * Class: ObstacleArea
 * Programmer: Matthew Taylor
 * Date: April 18, 2021
 * Program: ObstacleArea.java
 * Description: Keeps track of a container of lanes with obstacles
 *****************************************************************/
package frogger;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class ObstacleArea extends GameObject
{
    ArrayList <ObstacleLane> lanes;
    ArrayList <Color> obstacleColors;
    int yOffset;
    
    public ObstacleArea(int x, int y, int width, int height, Color backRoundColor)
    {
        super(x, y, width, height, backRoundColor, 0);
        lanes = new ArrayList<>();
        obstacleColors = new ArrayList<>();
        yOffset = 0;
    }
    
    /**
     * Method Name: addObstacleColor
     * Adds a color to the different colors the Obstacle Area can use
     */
    public void addObstacleColor(Color obstacleColor)
    {
        obstacleColors.add(obstacleColor);
    }
    
    /**
     * Method Name: addObstacleColor
     * Adds a lane to the list of lanes in the area
     */
    public void addLane(int obstacleCount, int obstacleSpeed)
    {
        ObstacleLane lane = new ObstacleLane(getBounds().x, getBounds().y + yOffset, getBounds().width, getBounds().height, getColor());
        lanes.add(lane);
        lane.createObstacles(obstacleCount, obstacleSpeed, obstacleColors);
        yOffset ++;
    }
    
    // Draws the area
    public void draw(Graphics g)
    {
        super.draw(g);
        
        for(ObstacleLane e : lanes)
        {
            e.draw(g);
        }
    }
    
    /**
     * Method Name: getObstacles
     * Gets all obstacles in the area
     * @return an array list of all obstacles in the area
     */
    public ArrayList<GameObject> getObstacles()
    {
        ArrayList<GameObject> allObstacles = new ArrayList<>();
        for(ObstacleLane e: lanes)
        {
            allObstacles.addAll(e.getObstacles());
        }
        return allObstacles;
    }
    
    // Accessors and Mutators
    public int getYOfset()
    {
        return yOffset;
    }
    
    public void setYOffset(int offset)
    {
        yOffset = offset;
    }
    
    public ArrayList<ObstacleLane> getLanes()
    {
        return lanes;
    }
}
