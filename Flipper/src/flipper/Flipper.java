/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flipper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Waterloo 2019 s1
 * @author Matt
 */
public class Flipper {
    /**
     * @param args the command line arguments
     */
    public static int[] vertFlip(int[] mirrs)
    {
        int tempA = mirrs[0];
        int tempB = mirrs[1];
        mirrs[0] = mirrs[2];
        mirrs[1] = mirrs[3];
        mirrs[2] = tempA;
        mirrs[3] = tempB;
        return mirrs;
    }
    
    public static int[] horiFlip(int[] mirrs)
    {
        int tempA = mirrs[0];
        int tempB = mirrs[2];
        mirrs[0] = mirrs[1];
        mirrs[1] = tempA;
        mirrs[2] = mirrs[3];
        mirrs[3] = tempB;
        return mirrs;
    }
    
    public static void main(String[] args) 
    {
        String inFile = "C:\\Users\\Matt\\Downloads\\all_data (1)\\all_data\\s1_j4\\j4.16.in";
        BufferedReader reader = null;
        int[] mirrs = new int[] {1, 2 ,3 ,4};
        
        try
        {
            reader = new BufferedReader(new FileReader(inFile));
            String flipsMade = reader.readLine();
            for(int i = 0; i < flipsMade.length(); i++)
            {
                if(flipsMade.charAt(i) == 'V')
                {
                    mirrs = horiFlip(mirrs);
                }
                else
                {
                    mirrs = vertFlip(mirrs);
                }
            }
            System.out.println(mirrs[0]+" " + mirrs[1]);
            System.out.println(mirrs[2]+" " + mirrs[3]);
        }
        catch(Exception e)
        {
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
