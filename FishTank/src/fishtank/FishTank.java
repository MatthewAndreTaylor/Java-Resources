/******************************************************************
 * Program Name: FishTank
 * Programmer: Matthew Taylor
 * Date: March 30, 2021
 * Program: FishTank.java
 * Description: A program that adds fish to a bowl and fights them
 *******************************************************************/
package fishtank;

import java.util.Random;
import java.util.Scanner;

public class FishTank 
{
    public static void main(String[] args) 
    {
        //Creating a random number generator
        Random rand = new Random();
        
        // First random number
        int random1 = rand.nextInt(5);
        
        // Second random number
        int random2;
        
        // Error trapping so the second random does not equal the first random
        do
        {
            random2 = rand.nextInt(5);
        }
        while(random2 == random1);
        
        // Create an array of fishes for the fishes in the bowl
        Fish [] fishes = new Fish [5];
        for(int i = 0; i < 5; i++)
        {
            // Use getData to get the data of a fish and put it in the array
            fishes[i] = getData(i+1);
        }
        
        // Displaying fish in the bowl
        displayFishBowl(fishes);
        
        // Printing out the names of the two fish that are fighting
        System.out.println("\noh no! " + fishes[random1].getName() + " is fighting " + fishes[random2].getName());
        
        // Fight the two fishes
        fishes[random1].fight(fishes[random2]);
        
        // Displaying fish in the bowl
        displayFishBowl(fishes);
    }
    
    /**
     * Method Name: displayFishBowl
     * Creates an object based on user input values for the field
     * @param num - The number of the fish
     * @return A fish object with user specified inputs
     */
    public static void displayFishBowl(Fish [] fishes)
    {
        // Print out message
        System.out.println("\nYour tank is filled with the following fish:");
        
        // Displaying the fish in the bowl to the user
        for(Fish e: fishes)
        {
            // Prints out the fish with toString method
            System.out.println(e.toString());
        }
    }// End of displayFishBowl method
    
    /**
     * Method Name: getData
     * Creates an object based on user input values for the field
     * @param num - The number of the fish
     * @return A fish object with user specified inputs
     */
    public static Fish getData(int num)
    {
        // Variables and Scanners
        String name;
        int size = 0;
        Scanner scanN = new Scanner(System.in);
        Scanner scanS = new Scanner(System.in);
        
        // Prompt user with info to set the fishes name
        System.out.println("What is the name of fish #" + num);
        // Get user input
        name = scanS.nextLine();
        
        // Error trapping for values between 1 and 10
        do
        {
            // Prompt user with info to set the fishes size
            System.out.println("What is the size of " + name + "? (1 - 10)");
            
            // Get user input checking it is an integer
            if(scanN.hasNextInt())
            {
                size = scanN.nextInt();
            }
            scanN.nextLine();
        }
        // Checking the size is between 1 and 10
        while(size > 10 || size < 1);
        
        // Returns the fish
        return new Fish(name,size);
        
    }// End of getData method
}
