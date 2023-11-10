
import java.util.Scanner;

import java.text.*;

public class PiggyBank
{

    public static void main(String[] args)
    {
        // Create scanners
        Scanner scanN = new Scanner(System.in);
        Scanner scanS = new Scanner(System.in);

        // Create decimal formatters
        DecimalFormat decFor = new DecimalFormat("$0.00");

        // Table variables
        int numToonies, numLoonies, numQuarters, numDimes, numNickels, numPennies, numWeeks;
        String surName, lastName;

        // Title
        System.out.println("Piggy Bank Calculator");
        System.out.println("======================================================");

        // Prompts user for name     
        System.out.print("Please type your first name:");
        surName = scanS.nextLine();
        System.out.print("Please type your last name:");
        lastName = scanS.nextLine();

        // Prompts user for # of coins       
        System.out.print("Please type the number of Toonies you have saved:");
        numToonies = scanN.nextInt();
        System.out.print("Please typethe number of Loonies you have saved:");
        numLoonies = scanN.nextInt();
        System.out.print("Please type the number of Quarters you have saved:");
        numQuarters = scanN.nextInt();
        System.out.print("Please type the number of Dimes you have saved:");
        numDimes = scanN.nextInt();
        System.out.print("Please type the number of Nickels you have saved:");
        numNickels = scanN.nextInt();
        System.out.print("Please type the number of Pennies you have saved:");
        numPennies = scanN.nextInt();
        System.out.print("Please type the number of weeks you saved coins for:");
        numWeeks = scanN.nextInt();

        // Math to calculate costs, totals, and averages
        double valueToonies = numToonies * 2.00;
        double valueLoonies = numLoonies * 1.00;
        double valueQuarters = numQuarters * 0.25;
        double valueDimes = numDimes * 0.10;
        double valueNickels = numNickels * 0.05;
        double valuePennies = numPennies * 0.01;
        double grandTotal = valueToonies + valueLoonies + valueQuarters + valueDimes + valueNickels + valuePennies;
        double averageSavings = grandTotal / numWeeks;
        double annualSavings = averageSavings * 52;

        // Prints out table
        System.out.format("\n%-10s %-2s %-11s %-2s %-15s", " Coin type", " |", 
                " # of coins", " |", " Total value of coins");
        System.out.println("\n====================================================");
        System.out.format("%-10s %-2s %-11s %-2s %-15s", " Toonies", " |", 
                numToonies, " |", decFor.format(valueToonies));
        System.out.format("\n%-10s %-2s %-11s %-2s %-15s", " Loonies", " |", 
                numLoonies, " |", decFor.format(valueLoonies));
        System.out.format("\n%-10s %-2s %-11s %-2s %-15s", " Quarters", " |", 
                numQuarters, " |", decFor.format(valueQuarters));
        System.out.format("\n%-10s %-2s %-11s %-2s %-15s", " Dimes", " |", 
                numDimes, " |", decFor.format(valueDimes));
        System.out.format("\n%-10s %-2s %-11s %-2s %-15s", " Nickels", " |", 
                numNickels, " |", decFor.format(valueNickels));
        System.out.format("\n%-10s %-2s %-11s %-2s %-15s", " Pennies", " |", 
                numPennies, " |", decFor.format(valuePennies));
        System.out.println("\n-----------------------------------");
        System.out.println("Grand Total\t\t     " + decFor.format(grandTotal));
        System.out.println("Average weekly savings:       " + decFor.format(averageSavings));

        // Final statement
        System.out.println("\n" + surName.charAt(0) + "." + lastName + 
                ", at this rate you can save " + decFor.format(annualSavings) + 
                " in one year.");
    }
}
