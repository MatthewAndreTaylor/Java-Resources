/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escape;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author Matt
 */
class Cell
{
    public int r;
    public int c;
    public int v;
    public List<Cell> cells = new ArrayList<>();
}

class FactorPair
{
    public int r;
    public int c;
}

public class Escape {

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
            
            List<Cell> cells = new ArrayList<>();
            
            for (int row = 0; row < m; row++)
            {
               String sampleLine = reader.readLine();
               StringTokenizer st = new StringTokenizer(sampleLine, " ");
               for(int col = 0; col < n; col++)
               {
                   Cell cell = new Cell();
                   cell.r = row+1;
                   cell.c = col+1;
                   cell.v = Integer.parseInt(st.nextToken());
                   cells.add(cell);
                   
               }
            }
            
            List<Cell> cellPathToExit = new ArrayList<>();
            cellPathToExit.add(cells.get(0));
            List<FactorPair> pairs  = getFactors(cells.get(0).v, m, n);
            
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
                System.out.println(ioe.toString());    
            }
        }
    }
    
    public static void completeThePath(List<Cell> cellPathToExit, List<FactorPair> pairs, List<Cell> cells)
    {

    }
     
    // num is the Factorable number
    public static List<FactorPair> getFactors(int num, int m, int n)
    {
        List<FactorPair> pairs = new ArrayList<>();
        for (int i = 1; i * i <= num; i++) 
        {
            if (num % i == 0) 
            {
               FactorPair p0 = new FactorPair();
               p0.r = i;
               p0.c = num/i;
               if(p0.c <= n && p0.r <= m)
               {
                   pairs.add(p0);
               }
               if(p0.c != p0.r)
               {
                   FactorPair p1 = new FactorPair();
                   p1.c = i;
                   p1.r = num/i;
                   if(p1.c <= n && p1.r <= m)
                   {
                        pairs.add(p1);
                   }
               }
            }  
        }   
        return pairs;
    }
}
