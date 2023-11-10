/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escaperoom;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author Matt
 */
class FactorPair
{
    public int r;
    public int c;
}

public class EscapeRoom 
{

    /**
     * @param args the command line arguments
     */    
    public static void main(String[] args) 
    {
        String inFile = "C:\\Users\\Matt\\Downloads\\all_data\\all_data\\junior_data\\j5_s2\\j5.sample.in";
        String outFile = "C:\\Users\\Matt\\Downloads\\all_data\\all_data\\junior_data\\j5_s2\\j5.01.02.out";
        
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new FileReader(inFile));
            String numberRows = reader.readLine();
            int m = Integer.parseInt(numberRows);
            String numberColumns = reader.readLine();
            int n = Integer.parseInt(numberColumns);
            
            //System.out.println("(m,n):(" + m + "," + n +")" );
            int cells[][] = new int[m][n];
            
            // Fill the array with cell values
            for (int row = 0; row < m; row++)
            {
               String sampleLine = reader.readLine();
               StringTokenizer st = new StringTokenizer(sampleLine, " ");
               for(int col = 0; col < n; col++)
               {
                   int value = Integer.parseInt(st.nextToken());
                   cells[row][col] = value;
               }
            }
            // Lookup map for if we have hit a cell location before and compute the factors.
            Map<FactorPair, Boolean> cellsTraversed = new HashMap<>();
            Queue<FactorPair> queue = new LinkedList<>();
        }
        catch(IOException | NumberFormatException e)
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
                System.out.println(ioe.toString());    
            }
        }
    }
    

}
