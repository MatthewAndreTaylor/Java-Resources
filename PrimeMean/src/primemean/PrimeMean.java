/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primemean;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Waterloo s2
 * @author Matt
 */
public class PrimeMean {

    public static boolean isPrime(int n)
    { 
        //check for prime compisites
        if(n == 0 || n ==1)
        {
            return false;
        }
        for(int j = 2; j < (int)Math.sqrt(n)+1; j++)
        {
            if(n%j == 0)
            {
                return false;
            }
        }
        return true;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        String inFile = "C:\\Users\\Matt\\Downloads\\all_data (1)\\all_data\\s2\\s2.2-13.in";
        BufferedReader reader = null;
            
        try
        {
            reader = new BufferedReader(new FileReader(inFile));
            String numberLines = reader.readLine();
            int lineCount = Integer.parseInt(numberLines);
            int nums[] = new int[lineCount];
            
            for(int i = 0; i < lineCount; i++)
            {
                String sampleLine = reader.readLine();
                nums[i] = Integer.parseInt(sampleLine);
            }
            for(int n: nums)
            {
                for(int i = 0; i < 1000000; i++)
                {
                    if(isPrime(i))
                    {
                        int p = (n*2) - i;
                        if(isPrime(p))
                        {
                            System.out.println(i + " " + p);
                            break;
                        }
                    }
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
        finally
        {
            try
            {
                reader.close();
            }
            catch(IOException ioe)
            {
                
            }
        }    
    }
    
}
