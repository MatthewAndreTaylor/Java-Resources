/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flowers;

import java.util.Scanner;

/**
 *
 * @author Matt
 */
public class Flowers {

    /**
     * @param args the command line arguments
     */
    public static int size;
    
    public static boolean isOriginalLayout(int[][] vals)
    {
        for(int i = 0; i < size-1; i++)
        {
            if(vals[0][i] > vals[0][i+1])
            {
                return false;
            }
            if(vals[i][0] > vals[i+1][0])
            {
                return false;
            }
        }
        return true;
    }
    
    public static int[][] rotateClockWise(int[][] vals) {
 int size = vals.length;
 int[][] ret = new int[size][size];

 for (int i = 0; i < size; ++i) 
  for (int j = 0; j < size; ++j) 
   ret[i][j] = vals[size - j - 1][i];

 return ret;
}
    
    public static void main(String[] args) 
    {
        Scanner s =new Scanner(System.in);
        size = s.nextInt();
        int [][] flowers = new int [size][size];
        for(int y = 0; y < size; y++)
        {
            for(int x = 0; x < size; x++)
            {
                flowers[y][x] = s.nextInt();
            }
        }
        while(!isOriginalLayout(flowers))
        {
            flowers = rotateClockWise(flowers);
        }
        for(int y = 0; y < size; y++)
        {
            for(int x = 0; x < size; x++)
            {
                System.out.print(flowers[y][x] + " ");
            }
            System.out.println("");
        }
    }
    
}
