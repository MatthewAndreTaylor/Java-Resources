/*****************************************************************
 * Class: GameWorld
 * Programmer: Matthew Taylor
 * Date: April 17, 2021
 * Program: GameWorld.java
 * Description: Keeps track of a world the player will be using
 *****************************************************************/
package frogger;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

// GameWorld will inherit from JPanel
public class GameWorld extends JPanel
{
    private static final Color COLOR_PLAYER = new Color(0,255,51);
    private static final Color COLOR_GRASS = new Color(0,204,0);
    private static final Color COLOR_ROAD = new Color(102,102,102);
    private static final Color COLOR_RIVER = new Color(51,153,255);
    private static final Color COLOR_LOG = new Color(102,51,0);
    private static final Color COLOR_CAR_A = new Color(255, 165, 0);
    private static final Color COLOR_CAR_B = new Color(255, 0, 0);
    
    private static final int FINISH_ANIMATION_FRAMES = 30;
    
    // Fields
    private KeyInput input;
    private int score;
    private int highScore;
    private Font gameFont;
    private double gameTime;
    private GameObject player;
    
    // Level fields
    private SafeArea endGrass;
    private ObstacleArea river;
    private SafeArea middleGrass;
    private ObstacleArea road;
    private SafeArea startGrass;
    private int finishAnimationCount = 0;
    
    // Constructor
    public GameWorld()
    {
        // Set score to zero and cellSize to 40
        score = 0;
        // Size for a single cell in the world
        
        // KeyInput for gameplay
        input = new KeyInput();
        
        // Set game font
        gameFont = new Font("Arial", Font.PLAIN, 24);
        
        // Allowing for key input
        setFocusable(true);
        
        // Adding Key Listener to frame for user input
        addKeyListener(input);
        
        // GameObject for player
        player = new GameObject(9,18, 1, 1, COLOR_PLAYER, GameObject.getCellSize());
        
        // Create the level
        createLevel();
    }
    
   // Use method paintComponent of JPanel
    public void paintComponent(Graphics g) 
    {
        // Do painting
        super.paintComponent(g);

        // Draw all the shapes with repaint method
        repaint(g);
    }
    
    /**
     * Method Name: createLevel
     * Creates the level by adding area's and lanes
     */
    private void createLevel()
    {
        endGrass = new SafeArea(0, 2, 19, 3, COLOR_GRASS);
        
        // Setup the river obstacle lane
        river = new ObstacleArea(0, 5, 19, 4, COLOR_RIVER);
        river.addObstacleColor(COLOR_LOG);
        
        // Setup the river lanes
        river.addLane(3, -10);
        river.addLane(2, 7);
        river.addLane(4, -11);
        river.addLane(2, 9);
        
        middleGrass = new SafeArea(0, 9, 19, 3, COLOR_GRASS);
        
        // Setup the road obstacle lane
        road = new ObstacleArea(0, 12, 19, 5, COLOR_ROAD);
        road.addObstacleColor(COLOR_CAR_A);
        road.addObstacleColor(COLOR_CAR_B);
        
        // Empty lane
        road.addLane(0, 0);
        
        // Setup the road lanes
        road.addLane(2, -7);
        road.addLane(4, 11);
        road.addLane(3, 4);
        
        // Empty lane
        road.addLane(0, 0);
        startGrass = new SafeArea(0, 17, 19, 3, COLOR_GRASS);
    }
    
    /**
     * Method Name: paintLevel
     * Paint all the area's in the level
     * @param g - the graphics context
     */
    public void paintLevel(Graphics g)
    {
        endGrass.draw(g);
        river.draw(g);
        middleGrass.draw(g);
        road.draw(g);
        startGrass.draw(g);
        
        drawFinishLine(g);
    }

    /**
     * Method Name: paintLevel
     * Paint all the area's in the level
     * @param g - the graphics context
     */
    public void drawFinishLine(Graphics g)
    {
        // Animate finish line after crossing
        Color color = Color.WHITE;
        if (finishAnimationCount > 0)
        {
            // Change color based on game time
            if (Math.round(gameTime) % 2 == 1)
            {
                color = Color.YELLOW;
                finishAnimationCount--;
            }
            else
            {
                color = Color.WHITE;
            }
        }
                
        for(int i = 0; i < 38; i++)
        {
            // Grid based on i
            // Top row of rectangles
            if(i%2 == 1)
            {
                g.setColor(Color.BLACK);
            }
            else
            {
                g.setColor(color);
            }
            g.fillRect(i * 20, 40, 20, 20);
            // Bottom row of rectangles
            if(i%2 == 0)
            {
                g.setColor(Color.BLACK);
            }
            else
            {
                g.setColor(color);
            }
            g.fillRect(i*20, 60, 20, 20);
        }   
    }
    
    /**
     * Method Name: repaint
     * Repaints the content
     * @param g - the graphics context
     */
    public void repaint(Graphics g)
    {   
        // Paint the score and time
        g.setFont(gameFont);
        g.drawString("High Score: " + highScore+ "      Score: " + String.valueOf(score) + 
                "      Time Remaining: " + String.valueOf(gameTime).substring(0, 2), 100, 30);
        
        // Paint Level
        paintLevel(g);
        
        // Draw the player last since he needs to be on the final layer
        player.draw(g);
    }
    
    /**
     * Method Name: update
     * Updates the game play
     * @param highScore - a high score
     * @param time - the countdown time
     */
    public void update(double time,int highestScore)
    {
        gameTime = time;
        highScore = highestScore;
        repaint();
        movePlayer();
        
        animateObstacleArea(river);
        animateObstacleArea(road);
        
        // Move player if he is ontop an obstacle
        for(GameObject e : river.getObstacles())
        {
            // Checking that the player is on a log and not out of bounds
            if(player.hasCollided(e) && player.getTrueBounds().x > river.getBounds().x 
                    && player.getTrueBounds().x < river.getTrueBounds().width -40)
            {
                player.move(e.getSpeed(), 0);
            }
        }
    }
    
    /**
     * Method Name: update
     * Moves all the obstacles in an obstacle area
     * @param area - an obstacle area to animate
     */
    public void animateObstacleArea(ObstacleArea area)
    {
        // Move each obstacle for every lane in the area
        for(ObstacleLane e : area.getLanes())
        {
            e.moveObstacles();
        }
    }
    
    /**
     * Method Name: hasPlayerScored
     * Checks if the player has scored
     * @return if player is above the score zone
     */
    public boolean hasPlayerScored()
    {
        // Player reached the top, increase the score and reset position
        if(player.getBounds().y <= 1)
        {
            score++;
            finishAnimationCount = FINISH_ANIMATION_FRAMES;
            player.setBounds(9,18, 1, 1);
            return true;
        }
        return false;
    }
    
    /**
     * Method Name: isPlayerAlive
     * Checks if player is alive
     * @return the state of the player
     */
    public boolean isPlayerAlive()
    {
        // Checks that the player is on the water he needs to touch a log
        if(player.hasCollided(river) && !player.hasCollided(river.getObstacles()))
        {
            return false;
        }
        // Checks that the player is on the road he cant touch the cars
        if(player.hasCollided(road) && player.hasCollided(road.getObstacles()))
        {
            return false;
        }
        return true;
    }
    
    /**
     * Method Name: movePlayer
     * Uses input to move player
     */
    public void movePlayer()
    {
        // Making sure that input is only from one key and player is not going out of bounds
        if(input.getKeys()[0] && !input.getKeys()[2] && !input.getKeys()[3])
        {
            player.move(0,player.getSpeed());
            input.interrupt(0);
        }
        if(input.getKeys()[1] && !input.getKeys()[2] && !input.getKeys()[3] && player.getBounds().y < 19)
        {
            player.move(0,-player.getSpeed());
            input.interrupt(1);
        }
        if(input.getKeys()[2] && !input.getKeys()[0] && !input.getKeys()[1] && player.getBounds().x < 18)
        {
            player.move(player.getSpeed(),0);
            input.interrupt(2);
        }
        if(input.getKeys()[3] && !input.getKeys()[0] && !input.getKeys()[1] && player.getBounds().x > 0)
        {
            player.move(-player.getSpeed(),0);
            input.interrupt(3);
        }
        if(!input.getKeys()[0] && !input.getKeys()[1] && !input.getKeys()[2] && !input.getKeys()[3])
        {
            player.move(0,0);
        }
    }
    
    // Accessors and Mutators
    public KeyInput getKeyInput()
    {
        return input;
    }
    
    public void setKeyInput(KeyInput in)
    {
        input = in;
    }
    
    public int getScore()
    {
        return score;
    }
    
    public void setScore(int endScore)
    {
        score = endScore;
    }
    
    public int getHighScore()
    {
        return highScore;
    }
    
    public void setHighScore(int highestScore)
    {
        highScore = highestScore;
    }
    
    public void setFinishAnimationFrameCount(int frameCount)
    {
        finishAnimationCount = frameCount;
    }
    
    public int getFinishAnimationFrameCount()
    {
        return finishAnimationCount;
    }
    
    public Font getGameFont()
    {
        return gameFont;
    }
    
    public void setGameFont(Font font)
    {
        gameFont = font;
    }
}
