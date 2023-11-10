/*************************************************************
 * Project: Area Calculator
 * Programmer: Matthew Taylor
 * Date: 2020/06/01
 * Program: AreaCalculator.java
 * Description: Program calculates area's of various shapes
 *************************************************************/
package areacalculator;

import java.util.Scanner;

public class AreaCalculator
{
    public static void main(String[] args) 
    {
        Scanner scanN = new Scanner(System.in);
        
        // Calculation Variables
        int userChoice;
        
        // Prints menu
        System.out.println("Area Calculator");
        System.out.println("===============");
        System.out.println("1. Triangle");
        System.out.println("2. Square");
        System.out.println("3. Rectangle");
        System.out.println("4. Circle");
        System.out.println("5. Quit");
        System.out.print("Enter your choice from the menu: ");
        
        // Handles user menu input
        while (true)
        {
            userChoice = scanN.nextInt();
            
            if (userChoice > 0 && userChoice < 6)
            {
                System.out.println("");
                break;
            }
            else
            {
                System.out.print("Please input a number between 1 & 5: ");
            }
        }

        // Handles user input for shapes dimension(s) for area calculation
        switch (userChoice)
        {
            // Triangle
            case (1) :
            {
                System.out.print("Enter the triangle base: ");
                double base = scanN.nextDouble();
                System.out.print("Enter the triangle height: ");
                double height = scanN.nextDouble();
                System.out.println("\nThe area is: " + 
                        calculateAreaTriangle(base, height));
                break;
            }    
            // Square
            case (2) :
            {
                System.out.print("Enter the square side length: ");
                double length = scanN.nextDouble();
                System.out.println("\nThe area is: " + 
                        calculateAreaSquare(length));
                break;
            }
            // Rectangle
            case (3) :
            {
                System.out.print("Enter the rectangle length: ");
                double length = scanN.nextDouble();
                System.out.print("Enter the rectangle width: ");
                double width = scanN.nextDouble();
                System.out.println("\nThe area is: " + 
                        calculateAreaRectangle(length, width));
                break;
            }
            // Circle
            case (4) :
            {
                System.out.print("Enter the circle radius: ");
                double radius = scanN.nextDouble();
                System.out.println("\nThe area is: " + 
                        calculateAreaCircle(radius));
                break;
            }
            // Quit
            case (5) :
            {
                System.out.println("Exiting program");
                break;
            }
        }
    }
    
    /**
     * Method Name: calculateAreaTriangle
     * Calculates the area of a rectangle
     * @param base - The base of triangle
     * @param height - The height of triangle
     * @return The calculated area of rectangle
     */
    public static double calculateAreaTriangle(double base, double height)
    {
        return 0.5 * base * height;
    }
    
    /**
     * Method Name: calculateAreaSquare
     * Calculates the area of a square
     * @param length - The squares side length
     * @return The calculated area of the square
     */
    public static double calculateAreaSquare(double length)
    {
        return length * length;
    }
    
    /**
     * Method Name: calculateAreaRectangle
     * Calculates the area of a rectangle
     * @param length - The length of rectangle
     * @param width - The width of rectangle
     * @return The calculated area of rectangle
     */
    public static double calculateAreaRectangle(double length, double width)
    {
        return length * width;
    }
    
    /**
     * Method Name: calculateAreaCircle
     * Calculates the area of a circle
     * @param radius - The circle radius
     * @return The calculated area of the circle
     */
    public static double calculateAreaCircle(double radius)
    {
        return Math.PI * (radius * radius);
    }
}
