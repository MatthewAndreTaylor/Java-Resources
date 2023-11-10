/****************************************************************************
 * Program Name: Frogger
 * Programmer: Matthew Taylor
 * Date: April 17, 2021
 * Program: Frogger.java
 * Description: A program that plays a game frogger where you have to get to the other side
 *****************************************************************************/
package frogger;

import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Frogger 
{
    // Static fields these belong to the class
    private static final int DEFAULT_WIDTH = 757;
    private static final int DEFAULT_HEIGHT = 820;
    
    /**
     * Main entry point to the application.
     * @param args The command line arguments
     */
    public static void main(String[] args) 
    {    
        // Create a name panel for get a user's name
        TextInputPanel namePanel = new TextInputPanel("Enter Player Name:", "Player 0");
        namePanel.showPanel("New Game");
        
        // Use name panel to get text
        String name = namePanel.getText();
        
        // Check the user has input a name before starting
        if(namePanel.isValidText())
        {
            // Create score board
            ScoreBoard scoreBoard = new ScoreBoard();
            
            // Load previous highScore
            int highScore = scoreBoard.getPreviousHighScore(name);
            
            // Create a frame for the game first getting the user's name
            JFrame frame = new JFrame("Frogger - Player: " +  name);
        
            // Creating and adding game panel to the frame
            GameWorld gameWorld = new GameWorld();
            
            // Setting up the frame with the panel
            startGame(frame, gameWorld);

            // Play again loop
            boolean isNewGame;
            do
            {
                // reset it to false after they each game
                isNewGame = false;
                
                // Run the game
                runGame(gameWorld, frame, highScore, name);
                
                // Update high score after game is finished
                if(gameWorld.getScore() > highScore)
                {
                    highScore = gameWorld.getScore();
                }
                
                // Close game window
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                
                TextPanel gameOverPanel = new TextPanel("Your Score was: " + gameWorld.getScore() + "... Play Again?");
                
                // Game over play again option menu
                if(gameOverPanel.showOptions("Game Over", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                {
                    // Set that the user wants to play again
                    isNewGame = true;
                    
                    // Reset the game world and frame
                    gameWorld = new GameWorld();
                    frame = new JFrame("Frogger - Player Name: " +  name);
                    startGame(frame, gameWorld);
                    
                    // Set running true
                    runGame(gameWorld, frame , highScore, name);
                }
            }
            while(isNewGame);
            
            // Setup the score board
            scoreBoard.scoreBoardEntry(name, highScore);
            scoreBoard.createBoard();
            // Serializes the scores to file
            scoreBoard.writeScores();
            setupFrame(scoreBoard.getJFrame());
            scoreBoard.getJFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }

    /**
     * Method Name: startGame
     * Prepares essentials for game startup such as world/level and user interface frame to run inside.
     * @param jFrame - the frame that need to be started
     * @param world - the world my game is inside
     */
    public static void startGame(JFrame jFrame, GameWorld world)
    {
        // Adding the world panel to the frame.
        jFrame.add(world);
        
        // Setting up the frame user interface options.
        setupFrame(jFrame);
    }
    
    /**
     * Method Name: setupFrame
     * Setup a JFrame so it is ready for user
     * @param jFrame - the frame that need to be setup
     */
    public static void setupFrame(JFrame jFrame)
    {
        // Setting up size, behaviour and location of the frame
        jFrame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        
        // Showing the frame
        jFrame.setVisible(true);
    }
    
    /**
     * Method Name: runGame
     * Runs game for player
     */
    public static void runGame(GameWorld world, JFrame frame, int highScore, String name)
    {
        // Boolean for if the game should be running
        boolean running = true;
        
        // Get time the game was started used for game timer countdown
        long startTime = System.nanoTime();
        
        // The amout of time to delay the frame. This controls the overall game speed.
        int frameDelayTime =  45;
        
        // Time in milli's for buffer
        long lastUpdateTime = System.currentTimeMillis();
        
        // Setting the length of the countdown
        double timeLeft = 60;
        double elapsedTimeSeconds = timeLeft;

        // Game play loop, two timers one for countdown one for fps
        while(running && elapsedTimeSeconds > 1)
        {
            // Check if user has beaten their high score
            if(world.getScore() > highScore)
            {
                highScore = world.getScore();
            }
            
            // Subtract current time from start time
            long elapsedTimeNanos = startTime - System.nanoTime();

            // Covert time to seconds
            elapsedTimeSeconds = (double)elapsedTimeNanos / 1000000000 + timeLeft;
            
            // Frame buffer for steady performance on any device
            long time = System.currentTimeMillis();
            if (time - lastUpdateTime > 1000) 
            {
                // Reduce loop to get more fps
                lastUpdateTime = time;
            }
            // Check how many updates are neeeded
            int updatesNeeded = (int)(time - lastUpdateTime) / frameDelayTime;
            for (int i = 0; i < updatesNeeded; i++) 
            {
                // Updating the gameplay
                world.update(elapsedTimeSeconds, highScore);
                
                // Reset buffer
                lastUpdateTime += frameDelayTime;
            }

            // Check that player is alive
            running = world.isPlayerAlive();

            if(world.hasPlayerScored())
            {
                // If the player scores, reward them with more time
                timeLeft += 5;
            }
        }
    }
}
