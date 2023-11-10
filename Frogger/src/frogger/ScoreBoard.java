/*****************************************************************
 * Class: ScoreBoard
 * Programmer: Matthew Taylor
 * Date: April 17, 2021
 * Program: ScoreBoard.java
 * Description: Keeps track of a score board to hold different entries and display them
 *****************************************************************/
package frogger;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ScoreBoard
{
    // Fields
    private JFrame board;
    private File myFile;
    private Scanner scoreReader;
    private PrintWriter printer;
    private ScoreBoardEntry [] scores;
    
    // Constructor
    public ScoreBoard()
    {
        // Set file to the scores filke where I keep the scores
        myFile = new File("scores.txt");
        
        // Set scores array
        scores = new ScoreBoardEntry[1];
        
        // Read past scores
        readScores();
    }
    
    /**
     * Method Name: searchAction
     * Search for a scoreboard entry
     */
    public void searchAction()
    {
        // Text panel for searching
            TextInputPanel numberSearch = new TextInputPanel("Enter a Player Name to Search:", "Player 0");
            numberSearch.showPanel("Search");
            
            // Sort names before a binary search
            quickSortNames(scores);
            
            // Allow user to do binary search
            // Using binary search in this situation because each name is unique
            if(numberSearch.isValidText())
            {
                int position = binarySearch(scores, numberSearch.getText());
                if(position != -1)
                {
                    // Found a user
                    JOptionPane.showMessageDialog(null, numberSearch.getText() + " has a high score of: " + scores[position].getScore());
                }
                else
                {
                    // Couldn't find a user
                    JOptionPane.showMessageDialog(null, "Sorry no one has gotten that score");
                }
            }
    }
    
    /**
     * Method Name: scoreBoardEntry
     * Sets or updates a scoreboard entry
     */
    public void scoreBoardEntry(String playerName, int playerHighScore)
    {
        //Check is the player is already on the board
        if(hasPlayedGame(playerName) != -1)
        {
            // Choose the larger of their new best, their old best and update it
            scores[hasPlayedGame(playerName)].setScore(Math.max(playerHighScore, getPreviousHighScore(playerName)));
        }
        else
        {
            // Adding a new high score entry for a brand new player
            scores = Arrays.copyOf(scores, scores.length+1);
            scores[scores.length-1] = new ScoreBoardEntry(playerName, playerHighScore);
        }
        
        // Sort the arrays after the new entry
        selectionSort(scores);
    }
    
    /**
     * Method Name: getPreviousHighScore
     * Gets a players last high score
     * @param name - player name to check
     * @return an integer representing the players last high score
     */
    public int getPreviousHighScore(String name)
    {
        if(hasPlayedGame(name) != -1)
        {
            // Found a previous high score
            return scores[hasPlayedGame(name)].getScore();
        }
        else
        {
            // They have no previous high score
            return 0;
        }
    }
    
    /**
     * Method Name: hasPlayedGame
     * Checks if a player has played the game
     * @param name - player name to check
     * @return an integer representing the players location in the array
     */
    public int hasPlayedGame(String name)
    {
        for(int i = 0; i < scores.length; i++)
        {
            // Check for any name matchest in the array of score entries 
            if(scores[i].getName().equals(name))
            {
                return i;
            }
        }
        // No entry found
        return -1;
    }
    
    /**
     * Method Name: selectionSort
     * Sorts a given array based on peoples scores
     * @param entries - The high scores entries to sort with
     */
    public void selectionSort(ScoreBoardEntry[] entries)
    {
        // Declare variables
        int i , j;
        int indexToSwap;    // The index that will be swapped
        ScoreBoardEntry tempEntry;   // Temporary variable for an entry
        
        for(i = 0; i < entries.length-1; i++)
        {
            // Initializae to the index of the first of the unsorted cells
            indexToSwap = i;            
            
            // Initialize temp's to the value in the first unsorted cell 
            tempEntry = entries[indexToSwap];
            
            // Search through the unsorted values
            for (j=1+i; j<entries.length; j++)    
            {
                // Sorting by which score is higher using compare to
                if (tempEntry.getScore() > scores[j].getScore())
                {
                    indexToSwap = j;
                    tempEntry = entries[indexToSwap];
                }// End of if statement
            }// End of inner for loop
            
            // Swap the value at the front of the unsorted cells    
            entries[indexToSwap] = entries[i];
            entries[i] = tempEntry;
        }// End of outer for loop
    }// End of selectionSort
    
    /**
     * Method Name: quickSortNames
     * Sorts a given array based on peoples names
     * @param entries - The high scores entries to sort with
     */
    public void quickSortNames(ScoreBoardEntry[] entries)
    {
        // Sort alphabetically using java's quicksort
        // Arrays sort uses the comparable I implemented on ScoreBoardEntry
        Arrays.sort(entries);
        
    }
    
    /**
     * Method Name: binarySearch
     * Searches for a specific name with binary search
     * @param target - The target name
     * @param entries - The high score entries to search through
     * @return index of the element or -1 if not found
     */
    public int binarySearch(ScoreBoardEntry[] entries, String target)
    {
        int midPointer; // Middle of the array
        int leftPointer  = 0; // Left
        int rightPointer = scores.length - 1; // Right
        while(leftPointer <= rightPointer)
        {
            // Move the mid pointer
            midPointer = (leftPointer + rightPointer)/2;
            // Check 
            if(entries[midPointer].getName().equals(target))
            {
                return midPointer;
            }
            else if(entries[midPointer].getName().compareTo(target) > 0)
            {
                // Move right pointer
                rightPointer = midPointer - 1;
            }
            else
            {
                // Move left pointer
                leftPointer = midPointer + 1;
            }
        }
        // No position found
        return -1;
    }
    
    public void createBoard()
    {
        // Create board
        board = new JFrame("Score Board");
        
        // Arrays for JTable contruction
        String[] columnNames = {"Name","High Score"};
        String[][] data = new String[scores.length][2];
        
        // Place data in the arrays
        for(int i = 0; i < data.length; i++)
        {
            data[data.length -i -1][0] = scores[i].getName();
            data[data.length -i -1][1] = String.valueOf(scores[i].getScore());
        }
        
        // Add data to table, add table to scroller
        JTable table = new JTable(data,columnNames);        
        JScrollPane scroller = new JScrollPane(table); 
        
        // Adding the table to the board
        board.add(scroller);
        
        // Making sure user can only view not edit others high scores
        table.setEnabled(false);
        
        // Button
        JButton button = new JButton("Search");
        
        // Trigger event when clicked
        button.addActionListener(new ActionListener()
        {
            // Button was clicked
            public void actionPerformed(ActionEvent e) 
            {
                searchAction();
            }
        });
        // Add button to frame at bottom
        board.add(button,BorderLayout.SOUTH);
    }
    
    /**
     * Method Name: writeScores
     * Writes scores to a file
     */
    public void writeScores()
    {
        // Try catch for file io exeptions
        try
        {
            printer = new PrintWriter(myFile);
        }
        catch(IOException exp)
        {
            System.out.println(exp);
        }
        
        // Write out the new high scores back to the text file 
        // I do not want to append just write them all
        for (ScoreBoardEntry score : scores) 
        {
            printer.println(score.getName());
            printer.println(score.getScore());
        }
        // Finished writing closing after usage
        printer.close();
    }
    
    /**
     * Method Name: readScores
     * Reads scores from a file
     */
    public void readScores()
    {
        // Try catch for file io exeptions
        try
        {
            scoreReader = new Scanner(myFile);
        }
        catch(IOException exp)
        {
            System.out.println(exp);
        }
        
        // Counter for number of entries
        int counter = 0;
        while(scoreReader.hasNextLine())
        {
            // Make the array longer
            scores = Arrays.copyOf(scores, counter+1);
            
            // Change the position at the countr to be a new entry
            scores[counter] = new ScoreBoardEntry(scoreReader.nextLine(), Integer.parseInt(scoreReader.nextLine()));
            
            // Increment
            counter++;
        }
        
        // Finished reading closing after usage
        scoreReader.close();
    }
    
    // Accessors and Mutators
    public JFrame getJFrame()
    {
        return board;
    }
    
    public void setJFrame(JFrame jFrame)
    {
        board = jFrame;
    }
    
    public ScoreBoardEntry [] getEntries()
    {
        return scores;
    }
    
    public void setEntries(ScoreBoardEntry [] entries)
    {
        scores = entries;
    }
}
