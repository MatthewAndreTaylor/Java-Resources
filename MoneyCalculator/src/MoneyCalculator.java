import java.util.Scanner;


public class MoneyCalculator
{

    public static void main(String[] args)
    {
        Scanner scanN = new Scanner(System.in);
                
        System.out.println("MoneyCalculator");
        System.out.println("===============");
        
        System.out.print("Enter Amount of Money: $");
        int amount = scanN.nextInt();
        System.out.println("This is made up of:");
        
        // 100's      
        System.out.format("%d $100 bills\n", amount / 100);
        amount = amount % 100;
        
        // 20's
        System.out.format("%d $20 bills\n", amount / 20);
        amount = amount % 20;
        
        // 10's
        System.out.format("%d $10 bills\n", amount / 10);
        amount = amount % 10;
        
        // 5's
        System.out.format("%d $5 bills\n", amount / 5);
        amount = amount % 5;
        
        // 2's
        System.out.format("%d toonies \n", amount / 2);
        amount = amount % 2;
        
        // 1's
        System.out.format("%d loonies\n", amount / 1);               
    }  
}
