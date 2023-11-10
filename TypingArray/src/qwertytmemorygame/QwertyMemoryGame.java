/***********************************************************
 * Project: Qwerty Memory Game (Culminating Assignment)
 * Programmer: Matthew Taylor
 * Date: 2020/06/18
 * Program: QwertyMemoryGame.java
 * Description: Plays against yourself to memorize letters.
 ***********************************************************/
package qwertytmemorygame;

import java.util.Scanner;

public class QwertyMemoryGame
{
    // Set of values to memorize
    static final char[] VALUE_SET = { 'q', 'w', 'e', 'r', 't', 'y'};
 
    /**
     * Method Name: main
     * Main game entry point
     * @param args The command line arguments.
     */
    public static void main(String[] args)
    {
        // Scanner for user input
        Scanner scanS = new Scanner(System.in);
        // Game level variables
        boolean chosePlayAgain;
        int highestLevel = 0;

        do
        {
           // Current level variables
           boolean didLevelUp = true;
           int currentLevel = 1;
           
           // Set to false each round.
           chosePlayAgain = false;
           
           // Game banner
           System.out.println("QWERTY MEMORY GAME");
           System.out.println("===================");
           System.out.println("Type the letters q, w, e, r, t, y  in the same"
                   + " order shown:");
           do
           {    
               // Run a ready count down
               startCountDown();
               
               // Creates a set of random numbers for the user to see
               char[] randomItems = new char[currentLevel];
               for (int i = 0; i < currentLevel; i++)
               {
                   // Get a random number
                   int randomItem = (int)(Math.random() * VALUE_SET.length);
                   // Shows the random number to the user
                   System.out.print((i + 1) + ". " + VALUE_SET[randomItem]);
                   // Assign the random number to the array of randomItems
                   randomItems[i] = VALUE_SET[randomItem];
                   // Delay the shown message for 2 seconds before clearing
                   delay(2000);
                   // Clear the last number to memorize using backspaces
                   System.out.println("\b\b\b\b");
               }
               
               // Ask the user to type in the numbers on one line
               System.out.print("Type the letters you saw in order and press ENTER: ");

               // Read the line of which has all the number the user repeated
               String userInput = scanS.nextLine();
               int answerCount = userInput.length();
               if (answerCount != currentLevel)
               {
                   // Break since they have input the wrong number of characters.
                   break;
               }

               // Check if the numbers the user entered match the numbers asked
               for (int i = 0; i < currentLevel; i++)
               {
                   if (randomItems[i] != Character.toLowerCase(userInput.charAt(i)))
                   {
                       didLevelUp = false;
                       break;
                   }
               } 
               // Check if user was sucessful and increase current level
               if (didLevelUp)
               {
                   System.out.println("LEVEL UP");
                   currentLevel++;
               }
           }
           while (didLevelUp);    

           // The user did not level up and made a mistake
           System.out.println("\nOoops you made a mistake.\n");

           // Compete the number of completed levels.
           int completedLevel = currentLevel - 1;
           System.out.println("You completed up to level: " + completedLevel);
           // Check if the user beat the highest level.
           if (completedLevel > highestLevel)
           {
               // If they beat the hight level. Then save it and show message
               highestLevel = completedLevel;
               System.out.println("You now have the highest level!!!!");
           }
           else
           {
               // If they did not beat the highest level
               System.out.println("Try again to beat the highest level: " + 
                       highestLevel);
           }
           // Ask the user to play again 
           System.out.print("\nDo you want to play again [Y/N]: ");
           // Take the first character and see if play again is yes
           char playAgain = scanS.nextLine().charAt(0);
           if (Character.toLowerCase(playAgain) == 'y')
           {
               chosePlayAgain = true;
               System.out.println("\n\n");
           }
        } 
        while (chosePlayAgain);
    }

    /**
     * MethodName: delay
     * Delays the program for a number of milliseconds
     * @param milliseconds - time in milliseconds
     */
    public static void delay(long milliseconds)
    {
        try
        {
            // Wait for n milliseconds
            Thread.sleep(milliseconds);
        } 
        catch (InterruptedException ex) 
        {
            // We dont need to worry about being interrupted
        }
    }

    /**
     * MethodName: startCountDown
     * Creates a count down to alert the user to get ready to memorize
     */
    public static void startCountDown()
    {
        System.out.print("READY...");
        delay(1500);
        System.out.println("\b\b\b\b\b\b\b\b\b");
        System.out.print("SET...");
        delay(1500);
        System.out.println("\b\b\b\b\b\b");
    }
}
