package modulus.calculator;

import java.util.Scanner; 

public class ModulusCalculator
{
    public static void main(String[] args)
    {
        double bigNumber; 
        double smallNumber; 
        double remainder; 
        int quotient;
        Scanner scanN = new Scanner(System.in); 
        
        System.out.println("Modulus Calculator");
        System.out.println("==================");
        System.out.println("Input big number:"); 
        bigNumber = scanN.nextInt();
        
        System.out.println("Input small number:"); 
        smallNumber = scanN.nextInt(); 
        
        quotient = (int)(bigNumber / smallNumber);         
        remainder = bigNumber - (smallNumber * quotient);
        
        System.out.println("Modulus is: "+ (int)remainder); 
    }
}
