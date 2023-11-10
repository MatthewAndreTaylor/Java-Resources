/*******************************************
 * Project: LoopyMath question 5.
 * Programmer: Matthew Taylor
 * Date: 2020/05/04
 * Program: DivideByTwo.java
 *******************************************/
package dividebytwo;

import java.util.Scanner;

public class DivideByTwo
{
    public static void main(String[] args)
    {
        // Input scanner
        Scanner scanN = new Scanner(System.in);
        
        // Variables
        int counter;
        int dividable;
        
        // User chooses which number they would like to divide
        System.out.print("Input a number: ");
        int userNumber = scanN.nextInt();
        
        // Sets dividable equal to user number
        dividable = userNumber;
        
        // Loop counts the number of times looped and divide by 2
        for ( counter = 0; dividable >= 1; counter++)
        {
            System.out.println(dividable);
            dividable /= 2;
        }
        
        // Prints final message
        System.out.println("I can divide " + userNumber + " by 2... "
                + (counter -1) + " times before it equals 1!");
    }
}
