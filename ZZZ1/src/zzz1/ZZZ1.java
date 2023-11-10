package zzz1;

import java.text.DecimalFormat;
import java.util.*;

public class ZZZ1 
{
    public static void main(String[] args) 
    {
        double totalArea = 0;
        Scanner scan = new Scanner(System.in);
        int shapeCount = scan.nextInt();
        int [] hori = new int [shapeCount];
        int [] vert = new int [shapeCount+1];
        for (int i = 0; i < shapeCount +1; i++) 
        {
            vert[i]= scan.nextInt();
        }
        for (int i = 0; i < shapeCount; i++) 
        {
            hori[i]= scan.nextInt();
        }
        for (int i = 0; i < shapeCount; i++) 
        {
            double triArea = Math.abs((double)(vert[i] - (double)vert[i+1])*(double)hori[i])/2.0;
            double rectArea = Math.min((double)vert[i], (double)vert[i+1])*(double)hori[i];
            totalArea += triArea + rectArea;
        }
        System.out.println((totalArea));
    }
}
