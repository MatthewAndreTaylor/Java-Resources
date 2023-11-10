/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rainbowpaintstoreold;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 *
 * @author Matt
 */
public class RainbowPaintStoreOld {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
            
        
        //Creating a scanner for user inputs
        Scanner scanN = new Scanner(System.in);
        Scanner scanS = new Scanner(System.in);
        
        //Creating a decimal formater for formating money, commas included
        DecimalFormat formatter = new DecimalFormat("$##,##,##,##,##,##,##0.00");
        
        
        //Boolean variable to see if the user if finished shopping
        boolean isFinnishedShopping = false;
        
        //String for the users colour response
        String userColourChoice;
        
        //Integer for the number of cans the user would like to buy
        int numberOfCans;
        
        //Doubles for total costs
        double totalCostOfRedPaint = 0;
        double totalCostOfGreenPaint = 0;
        double totalCost = 0;
        
        //This loop will be used so the user can make multiple paint selections
        do
        {
            //Ask the customer for paint choice
            System.out.println("What type of paint would you "
                    + "like to purchase (red or green)?");
            
            //Get the user input I put the selection to lower case for string comparison
            userColourChoice = scanS.nextLine().toLowerCase();
            
            //Making sure it is a valid option since the store only has red and green
            while(!userColourChoice.equals("red") && !userColourChoice.equals("green")) 
            {
                //Print message for error trapping and getting new input
                System.out.println("Sorry, we only have red or green");
                userColourChoice = scanS.nextLine().toLowerCase();
            }
             
            //Prompt the user on how much paint they need
            System.out.println("How many cans of paint do you need?");
            
            //I get the users input 
            numberOfCans = scanN.nextInt();
            
            //Checking that the user buys an appropriate amount of cans (one can or more)
            while(numberOfCans < 1)
            {
                //Print message for error trapping and getting new input
                System.out.println("Sorry, but you can only buy one or more cans");
                numberOfCans = scanN.nextInt();
            }
            
            //Add paint selection to totals based on type of paint
            if(userColourChoice.equals("red"))
            {
                //Add to the total cost of red paint
                totalCostOfRedPaint += costOfRedPaint(numberOfCans);
            }
            else
            {
                //Add to the total cost of green paint
                totalCostOfGreenPaint += costOfGreenPaint(numberOfCans);
            }
            
            //Ask user if they want to continue
            System.out.println("Do you want to continue shopping? (yes or no)");
            
            //Get user input I put the selection to lower case for string comparison
            String customerWantsToContinue = scanS.nextLine().toLowerCase();
            
            //The user has decided to stop
            if(customerWantsToContinue.equals("no"))
            {
                isFinnishedShopping = true;
            }
            
            //Error trap for only yes and no responses
            while(!customerWantsToContinue.equals("no") && !customerWantsToContinue.equals("yes"))
            {
                System.out.println("Sorry you can only answer yes or no");
                if(customerWantsToContinue.equals("no"))
                {
                    isFinnishedShopping = true;
                }
            }
        }
        while(!isFinnishedShopping);
        
        //Sum the total costs with tax using method 
        totalCost = totalCostPlusTax(totalCostOfRedPaint, totalCostOfGreenPaint);
        
        //Printing out the bill with correct formatting
        storeName();
        System.out.println("Total cost of red paint: " + formatter.format(totalCostOfRedPaint));
        System.out.println("Total cost of red paint: " + formatter.format(totalCostOfGreenPaint));
        System.out.println("Your total with tax is: " + formatter.format(totalCost));
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
        //For each bucket the user want to purchase 
        //multiply by the price of one bucket ($21.95)
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
        //For each bucket the user want to purchase 
        //multiply by the price of one bucket ($19.95)
        return (numBucketsGreen*19.95 );
    }

    /**
     * Method Name: storeName
     * Prints the store name for billing
     */
    public static void storeName()
    {
        System.out.println("Matthew's Bright Paints");
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
        //Add the total costs and multiply by 1.08 for the 8% tax 
        return (totalRedPaint + totalGreenPaint) * 1.08;
    }
    
    /**
     * Method Name: customerAppreaciation
     * Prints a thank you message
     */
    public static void customerAppreaciation()
    {
        System.out.println("Thank you for shopping at Matthew's Bright Paints");
    }
}
