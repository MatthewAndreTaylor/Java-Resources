/***************************************************************
 * Project: Rainbow Paint Store
 * Programmer: Matthew Taylor
 * Date: 2021/02/23
 * Program: RainbowPaintStore.java
 * Description: Program helps users calculate costs of different paints and prints a receipt 
 ***************************************************************/
package rainbowpaintstore;

import java.text.DecimalFormat;
import java.util.Scanner;

public class RainbowPaintStore {

    public static void main(String[] args)
    {  
        // Welcome message
        System.out.println("Welcome to the Rainbow Paint Store!");
        
        // Creating scanners for user inputs 
        Scanner scanN = new Scanner(System.in);
        Scanner scanS = new Scanner(System.in);
        
        // Creating a decimal formater for formating money, commas included
        DecimalFormat formatter = new DecimalFormat("$##,##,##,##,##,##,##,##0.00");
        
        // String for the users colour response
        String userColourChoice;
        
        // Integer for the number of cans the user would like to buy
        int numberOfCans;
        
        // Doubles for total costs
        double totalCostOfRedPaint = 0;
        double totalCostOfGreenPaint = 0;
        double totalCost;
        
        // Name of the colour for message
        String userColourName;
        
        // This loop will be used so the user can make multiple paint selections
        do
        {
            // Ask the customer for paint choice
            System.out.println("What colour paint would you like to purchase?"
                    + " - Please type a letter for the following options:");
            System.out.println("\t(R)ed\n\t(G)reen\n\t(Q)uit");
            
            // Get the user input I put the selection to upper case for string comparison
            userColourChoice = scanS.nextLine().toUpperCase();
            
            // Making sure it is a valid option (red, green, quit)
            while(!userColourChoice.equals("R") && !userColourChoice.equals("G") && !userColourChoice.equals("Q")) 
            {
                // Print message for error trapping and getting new input
                System.out.println("*****OOPS, that's not a valid letter, let's try again*****");
                System.out.println("What colour paint would you like to purchase?"
                    + " - Please type a letter for the following options:");
                System.out.println("\t(R)ed\n\t(G)reen\n\t(Q)uit");
                userColourChoice = scanS.nextLine().toUpperCase();
            }
            
            // I leave the loop if the user is finished
            if(userColourChoice.equals("Q"))
            {  
                break;
            }
            
            // Setting user colour name to their selection
            if(userColourChoice.equals("R"))
            {
                userColourName = "Red";
            }
            else
            {
                userColourName = "Green";
            }
            
            // Prompt for user 
            System.out.println("How many buckets of " + userColourName + " paint do you need?");
            
            // Error trap so the user can only buy integer amounts of paint cans
            if(scanN.hasNextInt())
            {
                // I get the users input 
                numberOfCans = scanN.nextInt();
                
                // Checking that the user buys an appropriate amount of cans (one can or more)
                if(numberOfCans > 0)
                {
                    // Add paint selection to totals based on type of paint
                    if(userColourChoice.equals("R"))
                    {
                        // Add to the total cost of red paint
                        totalCostOfRedPaint += costOfRedPaint(numberOfCans);
                    }
                    else
                    {
                        // Add to the total cost of green paint
                        totalCostOfGreenPaint += costOfGreenPaint(numberOfCans);
                    }
                }
                else
                {
                    System.out.println("*****OOPS, that's not a valid number, let's try again*****");
                    System.out.println("*****You must buy one or more cans******");
                }
            }
            else
            {
                // User did not type an integer
                System.out.println("*****OOPS, that's not a valid number, let's try again*****");
                
                // Skip over the line of bad input getting the new input
                scanN.nextLine();
            }
        }
        while(true);
        
        // Sum the total costs with tax
        totalCost = totalCostPlusTax(totalCostOfRedPaint, totalCostOfGreenPaint);
        
        // Printing out the bill with correct formatting and using methods
        System.out.println("\nPrinting receipt.......\n");
        storeName();
        System.out.format("%8s %29s", "\tRed paint" , (formatter.format(totalCostOfRedPaint) + "\n"));
        System.out.format("%8s %27s", "\tGreen paint" , (formatter.format(totalCostOfGreenPaint) + "\n"));
        System.out.format("%8s", "\t========================================\n");
        System.out.format("%8s %23s", "\tTOTAL (with tax)" , (formatter.format(totalCost) + "\n\n"));
        customerAppreaciation();
    }
    
    /**
     * Method Name: costOfRedPaint
     * Calculates the cost of red paint
     * @param numBucketsRed - The number of red buckets
     * @return The calculated cost of red paint
     */
    public static double costOfRedPaint(int numBucketsRed)
    {
        // For each bucket the user want to purchase multiply by the price of one bucket ($21.95)
        return (numBucketsRed*21.95);
    }
    
    /**
     * Method Name: costOfGreenPaint
     * Calculates the cost of green paint
     * @param numBucketsGreen - The number of green buckets
     * @return The calculated cost of green paint
     */
    public static double costOfGreenPaint(int numBucketsGreen)
    {
        // For each bucket the user want to purchase multiply by the price of one bucket ($19.95)
        return (numBucketsGreen*19.95 );
    }

    /**
     * Method Name: storeName
     * Prints the store name for billing
     */
    public static void storeName()
    {
        System.out.println("***** R A I N B O W    P A I N T    S T O R E *****\n");
    }
    
    /**
     * Method Name: totalCostPlusTax
     * Calculates the total cost with tax
     * @param totalRedPaint - The total cost of red paint purchased
     * @param totalGreenPaint - The total cost of green paint purchased
     * @return The calculated total cost with tax
     */
    public static double totalCostPlusTax(double totalRedPaint, double totalGreenPaint)
    {
        // Add the total costs and multiply by 1.08 for the 8% tax 
        return (totalRedPaint + totalGreenPaint) * 1.08;
    }
    
    /**
     * Method Name: customerAppreaciation
     * Prints a thank you message
     */
    public static void customerAppreaciation()
    {
        System.out.println("Thank you for shopping at Rainbow Paint Store.");
        System.out.println("We are here for all your painting needs.");
    }
}
