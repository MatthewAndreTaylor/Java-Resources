/*******************************************
 * Project: ExitingEarly
 * Programmer: Matthew Taylor
 * Date: 2020/04/20
 * Program: ExitingEarly.java
 *******************************************/
package exitingearly;

import java.util.Scanner;

public class ExitingEarly
{
    public static void main(String[] args)
    {
        // 1.
        /*
        Scanner scanS = new Scanner(System.in);
        String name;
        
        System.out.print("Please type your name: ");
        name = scanS.nextLine();
        
        for (int i = 0; i < name.length(); i++)
        {
            if( name.charAt(i) == ('a') || name.charAt(i) == ('A'))
                continue;
            System.out.print(name.charAt(i));
            
        }   
        System.out.println("\nName with no a's");
        */
        
        // 2.
        /*
        Scanner scanS = new Scanner(System.in);
        String word;
        int count = 0;
        
        System.out.print("Please type a word: ");
        word = scanS.nextLine();
        
        do
        {
            if( word.charAt(count) != ('e'))
            {
                System.out.println(word.charAt(count));
                count++;
            }
            else
            {
                System.out.println("\"Egads We found an e!!\"");
                break;
            }
        } while (count < word.length());
        */
    }
}
