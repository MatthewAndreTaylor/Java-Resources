/*******************************************
 * Project: LoopyMath question 4.
 * Programmer: Matthew Taylor
 * Date: 2020/04/27
 * Program: FibbonacciSequence.java
 *******************************************/
package fibbonaccisequence;

import java.util.Scanner;

public class FibbonacciSequence
{
    public static void main(String[] args)
    {
        // Creates Scanner
        Scanner scanN = new Scanner(System.in);
        
        // Term Variables
        int firstTerm = 0;
        int secondTerm = 1;
        int nextTerm;
        
        // Choose length of sequence ( question asks for the first 12 terms)
        System.out.print("How long would you like the sequence to be: ");
        int sequenceLength = scanN.nextInt();
        
        // Prints first terms
        System.out.print(firstTerm + ", ");
        System.out.print(secondTerm + ", ");
        
        // Loops the math for the next terms in the sequence
        for (int i = 0; i < sequenceLength -2; i++)
        {
            nextTerm = firstTerm + secondTerm;
            firstTerm = secondTerm;
            secondTerm = nextTerm;
            System.out.print(nextTerm + ", ");
        }
        System.out.println("...");
    }    
}
 