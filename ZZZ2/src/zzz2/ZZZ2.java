package zzz2;

import java.util.*;
public class ZZZ2 {

    
    
    public static void main(String[] args) 
    {
        long then = System.currentTimeMillis();
        Scanner scan = new Scanner(System.in);
        int total = 0;
        int rowCount = scan.nextInt();
        int colCount = scan.nextInt();
        int strokeCount = scan.nextInt();
        boolean[][] grid = new boolean[rowCount][colCount];
        for (int i = 0; i < strokeCount; i++) 
        {
           char strokeType = scan.next().charAt(0);
           int index = scan.nextInt()-1;
           if(strokeType == 'C')
           {
               for(int r = 0; r < rowCount; r++)
               {
                   grid[r][index] = !grid[r][index];
                   if(grid[r][index])
                   {
                       total++;
                   }
                   else
                   {
                       total--;
                   }
               }
           }
           else
           {
               for(int c = 0; c < colCount; c++)
               {
                   grid[index][c] = !grid[index][c];
                   if(grid[index][c])
                   {
                       total++;
                   }
                   else
                   {
                       total--;
                   }
               }
           }
        }
        System.out.println(total);
        long duration = System.currentTimeMillis() - then;
        System.out.println(duration);
    }
}
