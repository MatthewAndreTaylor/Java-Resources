/*****************************************************************
 * Class: ScoreBoardEntry
 * Programmer: Matthew Taylor
 * Date: April 17, 2021
 * Program: ScoreBoardEntry.java
 * Description: Keeps track of each player's highest score with an entry
 *****************************************************************/
package frogger;

// implementing compable for sorting
public class ScoreBoardEntry implements Comparable<ScoreBoardEntry>
{
    // Fields
    private String name;
    private int score;
    
    // Constructor
    public ScoreBoardEntry(String userName, int value)
    {
        name = userName;
        score = value;
    }
    
    /**
     * Method Name: compareTo
     * Compares two score entries based on name alphabetically
     * @param other - Another score entry
     */
    public int compareTo(ScoreBoardEntry other)
    {
        return name.compareTo(other.name);
    }
    
    public String toString()
    {
        return "Username: " + name + " Score: " + String.valueOf(score);
    }
    
    // Accessors and Mutators
    public String getName()
    {
        return name;
    }
    
    public void setName(String userName)
    {
        name = userName;
    }
    
    public int getScore()
    {
        return score;
    }
    
    public void setScore(int value)
    {
        score = value;
    }
}
