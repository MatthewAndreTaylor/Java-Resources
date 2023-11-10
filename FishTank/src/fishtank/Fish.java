/*****************************************************************
 * Class: Fish
 * Programmer: Matthew Taylor
 * Date: March 30, 2021
 * Program: Fish.java
 * Description: Keeps track of a fish, including name, size, and if it is alive;
 *****************************************************************/
package fishtank;

import java.util.Random;

public class Fish 
{
    // Fields
    private String name;
    private int size;
    private boolean alive;
    
    // Argument Constructor
    public Fish(String fishName , int fishSize)
    {
        name = fishName;
        size = fishSize;
        alive = true;
    }
    
    // Accessors and Mutators
    public void setName(String str) 
    {
        name = str;
    }

    public void setSize(int num) 
    { 
        size = num;
    }

    public void setAlive(boolean isAlive) 
    {
        alive = isAlive;
    }
    
    public String getName() 
    { 
        return name;
    }

    public int getSize() 
    { 
        return size;
    }
    
    public boolean getAlive() 
    {
        return alive;
    }
    
    /**
     * Method Name: toString
     * Prints out fish details
     * @return String of fish description
     */
    public String toString() 
    {
        if(alive)
        {
            return name + " is size " + size + " and is alive";
        }
        else
        {
            return name + " is size " + size + " and is dead";
        }
    }
    
    /**
     * Method Name: fight
     * Fights two fish leaving one not alive
     * @param enemy - The enemy fish
     */
    public void fight(Fish enemy)
    {
        // This fish wins it is bigger
        if(size > enemy.size)
        {
            // Set the enemy not alive
            enemy.alive = false;
            
            // Print this fish's name 
            System.out.println(name + " has won the fight!");
            
            // Increasing this fish's size
            size++;
        }
        // The enemy wins it is bigger
        else if(enemy.size > size)
        {
            // Set this fish not alive
            alive = false;
            
            // Print the enemy fish's name 
            System.out.println(enemy.name + " has won the fight!");
            
            // Increasing enemy fish size
            enemy.size++;
        }
        // They are the same choose a random fish
        else
        {
            // Random between 1 and 0
            Random rand = new Random();
            // If the random is 1
            if(rand.nextBoolean())
            {
                // Set the enemy not alive
                enemy.alive = false;
            
                // Print this fish's name 
                System.out.println(name + " has won the fight!");
            
                // Increasing this fish's size
                size++;
            }
            //the random is 0
            else
            {
                // Set this fish not alive
                alive = false;
            
                // Print the enemy fish's name 
                System.out.println(enemy.name + " has won the fight!");
            
                // Increasing enemy fish size
                enemy.size++;
            }
        }
    }
}
