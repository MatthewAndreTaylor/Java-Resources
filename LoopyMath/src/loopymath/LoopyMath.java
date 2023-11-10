/*******************************************
 * Project: LoopyMath
 * Programmer: Matthew Taylor
 * Date: 2020/04/20
 * Program: LoopyMath.java
 *******************************************/
package loopymath;

import java.util.Scanner;

public class LoopyMath
{
    public static void main(String[] args)
    {
        // 1. Factorial
        /*
        Scanner scanN = new Scanner(System.in);
        int userNumber;
        int product =1 ;
        
        System.out.print("Please type a number: ");
        userNumber = scanN.nextInt();
        
        System.out.print(userNumber + "! = " );
        
        for (int i = userNumber; i > 1; i--)
        {
            System.out.print(i + "*"); 
            product *= i;
            
        }
        System.out.print("1 = " + product + "\n");
*/
        
        // 2. Prime number check
        /*
        Scanner scanN = new Scanner(System.in);

        System.out.println("What number do you want to check is prime?");
        int userNumber= scanN.nextInt();
        
        boolean isPrime = true;

        for (int i = 2; i < userNumber; i++)
        {
            int remainder = userNumber % i;
            
            if (remainder == 0 || i == userNumber)
            {
                isPrime = false;
                break;
            }
        }
*/
        
        // 3. Quadratic Formula
        /*
        Scanner scanN = new Scanner(System.in);
        Scanner scanS = new Scanner(System.in);
        
        double firstRoot;
        double secondRoot;
        double determinant;
        String restart;
        boolean start;
                
        System.out.println("This is the form of a quadratic equasion");
        System.out.println("========================================");
        System.out.println("ax^2 + bx + c = 0");
        
        do
        {
            // Input values
            start = true;
            System.out.println("What number should \"a\" be: ");
            double a = scanN.nextDouble();
        
            System.out.println("What number should \"b\" be: ");
            double b = scanN.nextDouble();
        
            System.out.println("What number should \"c\" be: ");
            double c = scanN.nextDouble();
            
            // Math
            determinant = (b*b)-(4*a*c);
            determinant = Math.sqrt(determinant);         
            firstRoot = (-b + determinant) / 2*a;
            secondRoot = (-b - determinant) / 2*a;
            
            System.out.println("first root is (" + firstRoot + ",0)");
            System.out.println("second root is (" + secondRoot + ",0)");
            
            // Restart 
            System.out.println("Do you want to restart [Y] or [N]");
            restart = scanS.nextLine();
            if (restart.charAt(0) == ('N') || restart.charAt(0) == ('n'))
            {
                start = false;
            }
        }
        while (start);
        
*/
        // 4. Fibbonacci Sequence
        /*
        Scanner scanN = new Scanner(System.in);
        
        int firstTerm = 0;
        int secondTerm = 1;
        int nextTerm;
        
        System.out.print("How long would you like the sequence to be: ");
        int sequenceLength = scanN.nextInt();
        
        System.out.println(firstTerm);
        System.out.println(secondTerm);
        for (int i = 0; i < sequenceLength; i++)
        {
            nextTerm = firstTerm + secondTerm;
            firstTerm = secondTerm;
            secondTerm = nextTerm;
            System.out.println(nextTerm);
        }
*/
        
        
    } 
}
