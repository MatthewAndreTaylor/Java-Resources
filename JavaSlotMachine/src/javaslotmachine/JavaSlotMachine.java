/***********************************************************
 * Project: Slot Machine
 * Programmer: Matthew Taylor
 * Date: 2020/04/28
 * Program: SlotMachine.java
 * Description: User can gamble money in this slot machine
 ***********************************************************/
package javaslotmachine;

import java.text.DecimalFormat;
import java.util.Scanner;

public class JavaSlotMachine
{
    public static void main(String[] args)
    {
        // Creates Scanner and Decimal Formater
        Scanner scanN = new Scanner(System.in);
        Scanner scanS = new Scanner(System.in);
        DecimalFormat moneyDisplayFmt = new DecimalFormat("$0.00");
        
        // Slot machine variables
        boolean playAgain;
        boolean didWin;
        boolean useWinnings = false;
        int slot0, slot1, slot2;
        double dollarsEntered = 0;
        double dollarsSpent = 0;
        double dollarsWon = 0;
        String playAgainInputStr;
        
        // Prints Title and Banner
        System.out.println("SLOT MACHINE");
        System.out.println("=================");
        
        do
        {
            
            // Validate user amount wagered > 0
            boolean isValidAmount = false;
            while(!isValidAmount)
            {
                System.out.print("How much money do you want to enter $");
                dollarsEntered = scanN.nextDouble();
                
                
                if (dollarsEntered > 0)
                {
                    break;
                }
                else
                {
                    System.out.println("Please put amount greater than " + 
                            moneyDisplayFmt.format(0) + " into slot machine.");
                }
            }
            
            // If user wants to use winnings
            if (useWinnings)
            {
               dollarsWon -= dollarsEntered;
            }
            else
            {
                // Keep a total of the amount gambled
                dollarsSpent += dollarsEntered;    
            }

            // Sets the continue variables to false
            didWin = false;
            playAgain = false;
            
            // Compute Results
            slot0 = (int)(Math.random()*5);
            slot1 = (int)(Math.random()*5);
            slot2 = (int)(Math.random()*5);
            
            //Print out the random slot images
            System.out.println("---------------------------------------");
            String[] slotImages =  {
                "Cherries",
                "Oranges",
                "Plums",
                "Bells",
                "Melons",
                "Bars"
            };
            System.out.println(slotImages[slot0]);
            System.out.println(slotImages[slot1]);
            System.out.println(slotImages[slot2]);
                       
           // Check slot conditions
           if ( slot0 != slot1 && slot0 != slot2 && slot1 != slot2)
           {
               // None are the same
               System.out.println("\nYou have won " + moneyDisplayFmt.format(0));
               didWin = false;
           }
           else if (slot0 == slot1 && slot0 == slot2)
           {
               // All are the same
               double winnings = dollarsEntered * 3;
               System.out.println("\nCongratulations, you have won triple " + moneyDisplayFmt.format(winnings));
               didWin = true;
               dollarsWon += winnings;
           }
           else if ( (slot0 == slot1 || slot0 == slot2 || slot1 == slot2))
           {
               // Two are the same
               double winnings = dollarsEntered * 2;
               System.out.println("\nCongratulations, you have won double " + moneyDisplayFmt.format(winnings));
               didWin = true;
               dollarsWon += winnings;
           } 
           
            // Did the user win or lose.
            if (didWin)
            {
                // You won. Ask to play again with winnings
                System.out.print("Do you want to play again with winnings [Y/N] : ");
                useWinnings = true;
            }
            else
            {
              // You lost. Ask if play again
              System.out.print("Do you want to play again [Y/N] : ");
            }
            playAgainInputStr = scanS.nextLine();
            
            // Check if the user input y or Y to play again.
            if ( playAgainInputStr.toUpperCase().charAt(0) == 'Y')
            {
                playAgain = true;
            }
        }
        while (playAgain);

        // Print out the total gambling and ammount won
        System.out.println("Results");
        System.out.println("--------------------");
        System.out.println("Total amount spent: " + moneyDisplayFmt.format(dollarsSpent));
        System.out.println("Total amount won: " + moneyDisplayFmt.format(dollarsWon));        
    }
}
